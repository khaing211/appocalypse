package com.github.appocalypse.sudokusolver;

import com.google.common.collect.ImmutableList;

/**
 * Any group of four cells in the same unit that contain IN TOTAL four candidates is a Naked Quad
 *
 * When this happens, the four candidates can be removed from all other cells in the same unit.
 */
public class NakedQuadSolveStrategy implements SudokuSolveStrategy {
    @Override
    public boolean update(SudokuBoard board) {
        final ImmutableList<Unit> units = board.getAllUnits();
        final int[] sizes = new int[] {2, 3, 4};

        boolean hasUpdate = false;

        for (final Unit unit : units) {
            final ImmutableList<Cell> cells = unit.cells();

            for (final int[] set : Utils.CHOOSE_4_SET) {

                final Cell c0 = cells.get(set[0]);
                final Cell c1 = cells.get(set[1]);
                final Cell c2 = cells.get(set[2]);
                final Cell c3 = cells.get(set[3]);

                final int combineCandidateSet = c0.getCandidateSet()
                        & c1.getCandidateSet()
                        & c2.getCandidateSet()
                        & c3.getCandidateSet();

                if (!(c0.hasNakedQuad() || c1.hasNakedQuad() || c2.hasNakedQuad() || c3.hasNakedQuad()) &&
                        Utils.isIn(sizes, c0.getCandidateSetSize()) &&
                        Utils.isIn(sizes, c1.getCandidateSetSize()) &&
                        Utils.isIn(sizes, c2.getCandidateSetSize()) &&
                        Utils.isIn(sizes, c3.getCandidateSetSize()) &&
                        Utils.size(combineCandidateSet) == 4) {

                    board.markNakedQuad(c0, c1, c2, c3);

                    if (Utils.isInSameRow(c0, c1, c2, c3)) {
                        hasUpdate = hasUpdate || NakedSolveStrategy.eliminateCandidateFromRow(board, combineCandidateSet,
                                c0.getR(), c0, c1, c2, c3);
                    }

                    if (Utils.isInSameCol(c0, c1, c2, c3)) {
                        hasUpdate = hasUpdate || NakedSolveStrategy.eliminateCandidateFromCol(board, combineCandidateSet,
                                c0.getC(), c0, c1, c2, c3);
                    }

                    if (Utils.isInSameBox(c0, c1, c2, c3)) {
                        hasUpdate = hasUpdate || NakedSolveStrategy.eliminateCandidateFromBox(board, combineCandidateSet,
                                c0.getBaseR(), c0.getBaseC(), c0, c1, c2, c3);
                    }
                }
            }
        }

        return hasUpdate;
    }
}
