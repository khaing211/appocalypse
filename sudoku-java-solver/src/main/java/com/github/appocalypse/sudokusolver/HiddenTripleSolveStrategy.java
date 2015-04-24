package com.github.appocalypse.sudokusolver;

import com.google.common.collect.ImmutableList;

/**
 * Hidden Triples will be disguised by other candidates on those cells so
 * we have to prise them out by ensuing the Triple applies to at least on unit.
 */
public class HiddenTripleSolveStrategy implements SudokuSolveStrategy {
    @Override
    public boolean update(final SudokuBoard board) {
        final ImmutableList<Unit> units = board.getAllUnits();
        final int[] sizes = new int[] {2, 3};
        final Cell[] cellSet = new Cell[3];

        boolean hasUpdate = false;

        for (final Unit unit : units) {
            final ImmutableList<Cell> cells = unit.cells();
            for (final int candidateSet3 : Utils.CHOOSE_3_BIT_SET) {
                int i = 0;
                boolean skip = false;

                for (Cell cell : cells) {
                    if (!cell.hasHiddenTriple() &&
                        cell.isNotFilled() &&
                        Utils.isIn(sizes, Utils.size(cell.getCandidateSet() & candidateSet3))) {
                        if (i < 3) {
                            cellSet[i++] = cell;
                        } else {
                            skip = true;
                            break;
                        }
                    }
                }

                if (!skip && i == 3) {
                    final int candidateSetToUnset = ((~candidateSet3) & Utils.ALL);
                    hasUpdate = hasUpdate || board.unsetCandidate(cellSet[0].getR(), cellSet[0].getC(), candidateSetToUnset);
                    hasUpdate = hasUpdate || board.unsetCandidate(cellSet[1].getR(), cellSet[1].getC(), candidateSetToUnset);
                    hasUpdate = hasUpdate || board.unsetCandidate(cellSet[2].getR(), cellSet[2].getC(), candidateSetToUnset);

                    board.markHiddenTriple(cellSet[0], cellSet[1], cellSet[2]);
                }
            }
        }

        return hasUpdate;
    }
}
