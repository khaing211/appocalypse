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
                    c0.getCandidateSetSize() == 2 &&
                    c1.getCandidateSetSize() == 2 &&
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
}
