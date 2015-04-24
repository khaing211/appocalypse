package com.github.appocalypse.sudokusolver;

import com.google.common.collect.ImmutableList;

/**
 * A Naked Triple is slightly more complicated because it does not always imply three numbers each in three cells.
 *
 * Any group of three cells in the same unit that contain IN TOTAL three candidates is a Naked Triple
 *
 * When this happens, the three candidates can be removed from all other cells in the same unit.
 */
public class NakedTripleSolveStrategy implements SudokuSolveStrategy {
    @Override
    public boolean update(final SudokuBoard board) {
        final ImmutableList<Unit> units = board.getAllUnits();
        final int[] sizes = new int[] {2, 3};

        boolean hasUpdate = false;

        for (final Unit unit : units) {
            final ImmutableList<Cell> cells = unit.cells();

            for (final int[] set : Utils.CHOOSE_3_SET) {

                final Cell c0 = cells.get(set[0]);
                final Cell c1 = cells.get(set[1]);
                final Cell c2 = cells.get(set[2]);
                final int combineBitSet = c0.getPossibleSet() & c1.getPossibleSet() & c2.getPossibleSet();

                if (!(c0.hasNakedTriple() || c1.hasNakedTriple() || c2.hasNakedTriple()) &&
                    Utils.isIn(sizes, c0.getPossibleSet()) &&
                    Utils.isIn(sizes, c1.getPossibleSet()) &&
                    Utils.isIn(sizes, c2.getPossibleSet()) &&
                    Utils.size(combineBitSet) == 3) {

                    board.markNakedTriple(c0, c1, c2);

                    if (Utils.isInSameRow(c0, c1, c2)) {
                        hasUpdate = hasUpdate || eliminatePossibleFromRow(board, combineBitSet, c0, c1, c2);
                    }

                    if (Utils.isInSameCol(c0, c1, c2)) {
                        hasUpdate = hasUpdate || eliminatePossibleFromColumn(board, combineBitSet, c0, c1, c2);
                    }

                    if (Utils.isInSameBox(c0, c1, c2)) {
                        hasUpdate = hasUpdate || eliminatePossibleFromBox(board, combineBitSet, c0, c1, c2);
                    }

                }

            }
        }

        return hasUpdate;
    }

    private boolean eliminatePossibleFromRow(final SudokuBoard board, final int combineBitSet,
                                             final Cell c0, final Cell c1, final Cell c2) {
        boolean hasUpdate = false;
        for (int i = 0; i < 9; i++) {
            final Cell cell = board.getCell(c0.getR(), i);
            if (cell.isNotFilled() && Utils.isNot(cell, c0, c1, c2)) {
                hasUpdate = hasUpdate || board.unsetPossible(c0.getR(), i, combineBitSet);
            }
        }

        return hasUpdate;
    }

    private boolean eliminatePossibleFromColumn(final SudokuBoard board, final int combineBitSet,
                                                final Cell c0, final Cell c1, final Cell c2) {
        boolean hasUpdate = false;
        for (int i = 0; i < 9; i++) {
            final Cell cell = board.getCell(i, c0.getC());
            if (cell.isNotFilled() && Utils.isNot(cell, c0, c1, c2)) {
                hasUpdate = hasUpdate || board.unsetPossible(i, c0.getC(), combineBitSet);
            }
        }

        return hasUpdate;
    }

    private boolean eliminatePossibleFromBox(final SudokuBoard board, final int combineBitSet,
                                             final Cell c0, final Cell c1, final Cell c2) {
        boolean hasUpdate = false;
        final int baseR = c0.getBaseR();
        final int baseC = c0.getBaseC();
        for (int smallR = 0; smallR < 3; smallR++) {
            for (int smallC = 0; smallC < 3; smallC++) {
                final int r = baseR + smallR;
                final int c = baseC + smallC;
                final Cell cell = board.getCell(r, c);
                if (cell.isNotFilled() && Utils.isNot(cell, c0, c1, c2)) {
                    hasUpdate = hasUpdate || board.unsetPossible(r, c, combineBitSet);
                }
            }
        }

        return hasUpdate;
    }
}
