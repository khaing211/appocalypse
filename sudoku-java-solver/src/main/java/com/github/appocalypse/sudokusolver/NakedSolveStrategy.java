package com.github.appocalypse.sudokusolver;

import com.google.common.collect.ImmutableList;

import java.util.List;

public interface NakedSolveStrategy {

    static boolean hasNakedPair(Cell[] chooseCells) {
        for (int i = 0; i < chooseCells.length; i++) {
            if (chooseCells[i].hasNakedPair()) return true;
        }
        return false;
    }

    /**
     * @return true if all chooseCells  has one of the sizes
     */
    static boolean hasSize(Cell[] chooseCells, int[] sizes) {
        for (int i = 0; i < chooseCells.length; i++) {
            if (!Utils.isIn(sizes, chooseCells[i].getCandidateSetSize())) return false;
        }
        return true;
    }

    default boolean update(final SudokuBoard board, final List<int[]> chooseSets) {
        final ImmutableList<Unit> units = board.getAllUnits();

        boolean hasUpdate = false;

        for (final Unit unit : units) {

            final ImmutableList<Cell> cells = unit.cells();

            for (final int[] set : chooseSets) {

                final Cell[] chooseCells = new Cell[set.length];
                for (int i = 0; i < set.length; i++) chooseCells[i] = cells.get(set[i]);

                Cell c0 = chooseCells[0];
                Cell c1 = chooseCells[1];

                if (!hasNakedPair(chooseCells) &&
                    hasSize(chooseCells, new int[]{2}) &&
                    c0.getCandidateSet() == c1.getCandidateSet()) {

                    board.markNakedPair(c0, c1);

                    if (Utils.isInSameRow(c0, c1)) {
                        hasUpdate = hasUpdate || NakedSolveStrategy.eliminateCandidateFromRow(board,
                                c0.getCandidateSet(), c0.getR(), c0, c1);
                    }

                    if (Utils.isInSameCol(c0, c1)) {
                        hasUpdate = hasUpdate || NakedSolveStrategy.eliminateCandidateFromCol(board,
                                c0.getCandidateSet(), c0.getC(), c0, c1);
                    }

                    if (Utils.isInSameBox(c0, c1)) {
                        hasUpdate = hasUpdate || NakedSolveStrategy.eliminateCandidateFromBox(board,
                                c0.getCandidateSet(), c0.getBaseR(), c0.getBaseC(), c0, c1);
                    }
                }
            }
        }

        return hasUpdate;
    }

    static boolean eliminateCandidateFromRow(final SudokuBoard board, final int candidateSet, final int r,
                                             final Cell ... cells) {
        boolean hasUpdate = false;
        for (int i = 0; i < 9; i++) {
            final Cell cell = board.getCell(r, i);
            if (cell.isNotFilled() && Utils.isNot(cell, cells)) {
                hasUpdate = hasUpdate || board.unsetCandidate(r, i, candidateSet);
            }
        }

        return hasUpdate;
    }

    static boolean eliminateCandidateFromCol(final SudokuBoard board, final int candidateSet, final int c,
                                             final Cell ... cells) {
        boolean hasUpdate = false;
        for (int i = 0; i < 9; i++) {
            final Cell cell = board.getCell(i, c);
            if (cell.isNotFilled() && Utils.isNot(cell, cells)) {
                hasUpdate = hasUpdate || board.unsetCandidate(i, c, candidateSet);
            }
        }

        return hasUpdate;
    }

    static boolean eliminateCandidateFromBox(final SudokuBoard board, final int candidateSet,
                                             final int baseR, final int baseC, final Cell ... cells) {
        boolean hasUpdate = false;
        for (int smallR = 0; smallR < 3; smallR++) {
            for (int smallC = 0; smallC < 3; smallC++) {
                final int r = baseR + smallR;
                final int c = baseC + smallC;
                final Cell cell = board.getCell(r, c);
                if (cell.isNotFilled() && Utils.isNot(cell, cells)) {
                    hasUpdate = hasUpdate || board.unsetCandidate(r, c, candidateSet);
                }
            }
        }

        return hasUpdate;
    }
}
