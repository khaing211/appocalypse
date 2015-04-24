package com.github.appocalypse.sudokusolver;

import com.google.common.collect.ImmutableList;

/**
 * A Naked Pair is a set of two candidate numbers sited in two cells that belong to at least one unit in common.
 * That is they reside in the same row, box or column.
 *
 * It is clear that the solution will contain those values in those two cells and all other candidates with
 * those numbers can be removed from whatever unit(s) they have in common.
 */
public class NakedPairSolveStrategy implements SudokuSolveStrategy {
    @Override
    public boolean update(final SudokuBoard board) {
        final ImmutableList<Unit> units = board.getAllUnits();

        boolean hasUpdate = false;

        for (final Unit unit : units) {

            final ImmutableList<Cell> cells = unit.cells();

            for (final int[] set : Utils.CHOOSE_2_SET) {

                final Cell c0 = cells.get(set[0]);
                final Cell c1 = cells.get(set[1]);

                if (!(c0.hasNakedPair() || c1.hasNakedPair()) &&
                    c0.getPossibleSetSize() == 2 &&
                    c1.getPossibleSetSize() == 2 &&
                    c0.getPossibleSet() == c1.getPossibleSet()) {

                    board.markNakedPair(c0, c1);

                    if (Utils.isInSameRow(c0, c1)) {
                        hasUpdate = hasUpdate || eliminatePossibleFromRow(board, c0, c1);
                    }

                    if (Utils.isInSameCol(c0, c1)) {
                        hasUpdate = hasUpdate || eliminatePossibleFromColumn(board, c0, c1);
                    }

                    if (Utils.isInSameBox(c0, c1)) {
                        hasUpdate = hasUpdate || eliminatePossibleFromBox(board, c0, c1);
                    }
                }
            }
        }

        return hasUpdate;
    }

    private boolean eliminatePossibleFromRow(final SudokuBoard board, final Cell c0, final Cell c1) {
        boolean hasUpdate = false;
        for (int i = 0; i < 9; i++) {
            final Cell cell = board.getCell(c0.getR(), i);
            if (cell.isNotFilled() && Utils.isNot(cell, c0, c1)) {
                hasUpdate = hasUpdate || board.unsetPossible(c0.getR(), i, c0.getPossibleSet());
            }
        }

        return hasUpdate;
    }

    private boolean eliminatePossibleFromColumn(final SudokuBoard board, final Cell c0, final Cell c1) {
        boolean hasUpdate = false;
        for (int i = 0; i < 9; i++) {
            final Cell cell = board.getCell(i, c0.getC());
            if (cell.isNotFilled() && Utils.isNot(cell, c0, c1)) {
                hasUpdate = hasUpdate || board.unsetPossible(i, c0.getC(), c0.getPossibleSet());
            }
        }

        return hasUpdate;
    }

    private boolean eliminatePossibleFromBox(final SudokuBoard board, final Cell c0, final Cell c1) {
        boolean hasUpdate = false;
        final int baseR = c0.getBaseR();
        final int baseC = c0.getBaseC();
        for (int smallR = 0; smallR < 3; smallR++) {
            for (int smallC = 0; smallC < 3; smallC++) {
                final int r = baseR + smallR;
                final int c = baseC + smallC;
                final Cell cell = board.getCell(r, c);
                if (cell.isNotFilled() && Utils.isNot(cell, c0, c1)) {
                    hasUpdate = hasUpdate || board.unsetPossible(r, c, c0.getPossibleSet());
                }
            }
        }

        return hasUpdate;
    }
}
