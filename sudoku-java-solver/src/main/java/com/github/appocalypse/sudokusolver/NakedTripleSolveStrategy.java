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

                final int combineCandidateSet = c0.getCandidateSet() & c1.getCandidateSet() & c2.getCandidateSet();

                if (!(c0.hasNakedTriple() || c1.hasNakedTriple() || c2.hasNakedTriple()) &&
                    Utils.isIn(sizes, c0.getCandidateSet()) &&
                    Utils.isIn(sizes, c1.getCandidateSet()) &&
                    Utils.isIn(sizes, c2.getCandidateSet()) &&
                    Utils.size(combineCandidateSet) == 3) {

                    board.markNakedTriple(c0, c1, c2);

                    if (Utils.isInSameRow(c0, c1, c2)) {
                        hasUpdate = hasUpdate || NakedSolveStrategy.eliminateCandidateFromRow(board, combineCandidateSet,
                                c0.getR(), c0, c1, c2);
                    }

                    if (Utils.isInSameCol(c0, c1, c2)) {
                        hasUpdate = hasUpdate || NakedSolveStrategy.eliminateCandidateFromCol(board, combineCandidateSet,
                                c0.getC(), c0, c1, c2);
                    }

                    if (Utils.isInSameBox(c0, c1, c2)) {
                        hasUpdate = hasUpdate || NakedSolveStrategy.eliminateCandidateFromBox(board, combineCandidateSet,
                                c0.getBaseR(), c0.getBaseC(), c0, c1, c2);
                    }

                }

            }
        }

        return hasUpdate;
    }
}
