package com.github.appocalypse.sudokusolver;

import java.util.Arrays;
import java.util.stream.IntStream;

public class SudokuBoard {
    private final Cell[] cells = new Cell[Utils.MAX_NUM_UNSOLVED_CELL];

    private int numUnsolvedCell;

    public SudokuBoard() {
        this.numUnsolvedCell = Utils.MAX_NUM_UNSOLVED_CELL;

        IntStream.range(0, Utils.MAX_NUM_UNSOLVED_CELL)
                .forEach(i -> cells[i] = new Cell(i / 9, i % 9));
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

    public void printPossible() {
        IntStream.range(0, 9)
                .forEachOrdered(r -> IntStream.range(0, 9)
                        .forEachOrdered(c -> printPossible(r, c)));
    }

    public void printPossible(int r, int c) {
        Utils.isValidIndex(r, c);
        System.out.println("[" + r + "," + c + "," + getCell(r, c).getPossibleSet() + "]");
    }

    public void printNumberPossible() {
        IntStream.range(0, 9)
                .forEachOrdered(r -> IntStream.range(0, 9)
                        .forEachOrdered(c -> printNumberPossible(r, c)));
    }

    public void printUnfilledCellPossible() {
        IntStream.range(0, Utils.MAX_NUM_UNSOLVED_CELL)
            .forEach(k -> {
                final Cell cell = cells[k];
                if (cell.isNotFilled()) {
                    System.out.println("[" + cell.getR()
                            + "," + cell.getC() + "]"
                            + Arrays.toString(cell.getPossibles()));
                }
            });
    }

    public void printNumberPossible(int r, int c) {
        Utils.isValidIndex(r, c);
        System.out.println("[" + r + "," + c + "]" + Arrays.toString(getCell(r,c).getPossibles()));
    }

    public void printBoard() {
        System.out.println(board('+'));
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
}
