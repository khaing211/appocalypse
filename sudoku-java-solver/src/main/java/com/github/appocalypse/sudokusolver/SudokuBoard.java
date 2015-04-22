package com.github.appocalypse.sudokusolver;

import com.google.common.collect.ImmutableList;

import java.util.Arrays;
import java.util.stream.IntStream;

public class SudokuBoard implements Unit {
    private final Cell[] cells = new Cell[Utils.MAX_NUM_UNSOLVED_CELL];

    private int numUnsolvedCell;

    public SudokuBoard() {
        this.numUnsolvedCell = Utils.MAX_NUM_UNSOLVED_CELL;

        IntStream.range(0, Utils.MAX_NUM_UNSOLVED_CELL)
                .forEach(i -> cells[i] = new Cell(i / 9, i % 9));
    }

    public SudokuBoard(SudokuBoard board) {
        this.numUnsolvedCell = board.numUnsolvedCell;
        IntStream.range(0, Utils.MAX_NUM_UNSOLVED_CELL)
                .forEach(i -> cells[i] = board.cells[i]);
    }

    // k = linear index of cells
    private int getK(int r, int c) {
        return r*9 + c;
    }

    private Cell getCell(int r, int c) {
        final int k = getK(r, c);
        return cells[k];
    }

    public int getNumUnsolvedCell() {
        return numUnsolvedCell;
    }

    public void fill(int r, int c, int n) {
        Utils.isValidIndex(r, c);
        Utils.isValidNumber(n);

        final int k = getK(r, c);
        final Cell cell = getCell(r, c);

        if (cell.isNotFilled() && cell.isPossible(n)) {
            cells[k] = new Cell(cell.getR(), cell.getC(), n);
            update(r, c, n);
            numUnsolvedCell--;
        } else if (cell.getN() == n) {
            throw new IllegalArgumentException("n value " + n + " is already set for r " + r + " c " + c);
        } else {
            throw new IllegalArgumentException("impossible n value " + n + " for r " + r + " c " + c);
        }
    }

    public boolean isFilled(int r, int c) {
        Utils.isValidIndex(r, c);
        return getCell(r, c).isFilled();
    }

    private void update(final int r, final int c, final int n) {
        int k;
        Cell cell;
        final int baseR = (r / 3) * 3;
        final int baseC = (c / 3) * 3;

        for (int i = 0; i < 9; i++) {
            if (i != c) {
                k = getK(r, i);
                cell = cells[k];
                cells[k] = new Cell(r, i, Utils.unset(cell.getPossibleSet(), n), cell.getN());
            }

            if (i != r) {
                k = getK(i, c);
                cell = cells[k];
                cells[k] = new Cell(i, c, Utils.unset(cell.getPossibleSet(), n), cell.getN());
            }

            final int curR = baseR + i/3;
            final int curC = baseC + i%3;
            if (!(curR == r && curC == c)) {
                k = getK(curR, curC);
                cell = cells[k];
                cells[k] = new Cell(curR, curC, Utils.unset(cell.getPossibleSet(), n), cell.getN());
            }
        }
    }

    public boolean unsetPossible(int r, int c, int set) {
        Utils.isValidIndex(r, c);
        final int k = getK(r, c);
        final Cell cell = cells[k];
        final int test = (cell.getPossibleSet() & set);
        final int mask = ((~set) & Utils.ALL);
        final int newPossibleSet = cell.getPossibleSet() & mask;
        cells[k] = new Cell(r, c, newPossibleSet, cell.getN(), cell.hasNakedPair(), cell.hasNakedTriple(), cell.hasNakedQuad(),
            cell.hasHiddenPair(), cell.hasHiddenTriple(), cell.hasHiddenQuad());
        return test != 0;
    }

    public int getPossibleSet(int r, int c) {
        Utils.isValidIndex(r, c);
        return getCell(r, c).getPossibleSet();
    }

    public int[] getPossibles(int r, int c) {
        Utils.isValidIndex(r, c);
        return getCell(r, c).getPossibles();
    }

    public int getPossibleSetSize(int r, int c) {
        Utils.isValidIndex(r, c);
        return getCell(r, c).getPossibleSetSize();
    }

    public String board(final char empty) {
        // 81 char + 9 newline = 90 char capacity
        final StringBuilder builder = new StringBuilder(90);
        IntStream.range(0, Utils.MAX_NUM_UNSOLVED_CELL)
            .forEachOrdered(k -> {
                if (k != 0 && k % 9 == 0) {
                    builder.append("\n");
                }

                final Cell cell = cells[k];
                if (cell.isFilled()) {
                    builder.append(Character.forDigit(cell.getN(), 10));
                } else {
                    builder.append(empty);
                }
            });

        builder.append("\n");

        return builder.toString();
    }

    private static final String HORIZONTAL_LINE = "+++++++++++++++++++++++++++++++++++++";

    public String niceBoard() {
        return sideBySide(null);
    }

    /**
     * this board print on the left
     * Board is to the right
     *
     * @param board nullable
     */
    public String sideBySide(final SudokuBoard board) {
        final StringBuilder builder = new StringBuilder(6400);
        // 4 space in between
        final String SPACE_IN_BETWEEN = "     ";

        for (int r = 0; r < 9; r++) {
            builder.append(HORIZONTAL_LINE);
            if (board != null) {
                builder.append(SPACE_IN_BETWEEN);
                builder.append(HORIZONTAL_LINE);
            }
            builder.append('\n');

            for (int i = 0; i < 3; i++) {

                builder.append('|');

                for (int c = 0; c < 9; c++) {
                    final Cell cell = getCell(r, c);

                    for (int j = 0; j < 3; j++) {
                        if (cell.isNotFilled()) {
                            final int n = i * 3 + j + 1;
                            if (cell.isPossible(n)) {
                                builder.append(Character.forDigit(n, 10));
                            } else {
                                builder.append(' ');
                            }
                        } else if (cell.isFilled() && i == 1 && j == 1) {
                            builder.append(Character.forDigit(cell.getN(), 10));
                        } else {
                            builder.append(' ');
                        }
                    }

                    builder.append('|');
                }

                if (board != null) {
                    builder.append(SPACE_IN_BETWEEN);
                    builder.append('|');

                    for (int c = 0; c < 9; c++) {
                        final Cell cell = board.getCell(r, c);

                        for (int j = 0; j < 3; j++) {
                            if (cell.isNotFilled()) {
                                final int n = i * 3 + j + 1;
                                if (cell.isPossible(n)) {
                                    builder.append(Character.forDigit(n, 10));
                                } else {
                                    builder.append(' ');
                                }
                            } else if (cell.isFilled() && i == 1 && j == 1) {
                                builder.append(Character.forDigit(cell.getN(), 10));
                            } else {
                                builder.append(' ');
                            }
                        }

                        builder.append('|');
                    }
                }

                builder.append('\n');
            }
        }

        builder.append(HORIZONTAL_LINE);
        if (board != null) {
            builder.append(SPACE_IN_BETWEEN);
            builder.append(HORIZONTAL_LINE);
        }
        builder.append('\n');


        return builder.toString();
    }

    @Override
    public String toString() {
        return niceBoard();
    }

    @Override
    public ImmutableList<Cell> cells() {
        return ImmutableList.copyOf(cells);
    }

    @Override
    public ImmutableList<RowUnit> groupByRow() {
        final ImmutableList.Builder<RowUnit> builder = ImmutableList.builder();
        final ImmutableList<Cell> cells = cells();
        for (int i = 0; i < 9; i++) {
            builder.add(new RowUnit(i, cells.subList(i * 9, (i+1) * 9)));
        }

        return builder.build();
    }

    @Override
    public ImmutableList<ColUnit> groupByCol() {
        // create array arrange by col by row
        final Cell[] cellsArray = new Cell[Utils.MAX_NUM_UNSOLVED_CELL];
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                cellsArray[getK(r, c)] = this.cells[getK(c, r)];
            }
        }

        final ImmutableList<Cell> cells  = ImmutableList.copyOf(cellsArray);
        final ImmutableList.Builder<ColUnit> builder = ImmutableList.builder();
        for (int i = 0; i < 9; i++) {
            builder.add(new ColUnit(i, cells.subList(i * 9, (i+1) * 9)));
        }

        return builder.build();
    }

    @Override
    public ImmutableList<BoxUnit> groupByBox() {
        // create array arrange by box
        final Cell[] cellsArray = new Cell[Utils.MAX_NUM_UNSOLVED_CELL];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int r = (i/3)*3 + (j/3);
                int c = (i%3)*3 + (j%3);
                cellsArray[getK(i, j)] = this.cells[getK(r, c)];
            }
        }

        final ImmutableList<Cell> cells  = ImmutableList.copyOf(cellsArray);

        final ImmutableList.Builder<BoxUnit> builder = ImmutableList.builder();
        for (int i = 0; i < 9; i++) {
            builder.add(new BoxUnit(i / 3, i % 3, cells.subList(i * 9, (i+1) * 9)));
        }

        return builder.build();
    }
}
