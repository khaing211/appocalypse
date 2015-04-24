package com.github.appocalypse.sudokusolver;

import com.google.common.collect.ImmutableList;

/**
 * Hidden Quad will be disguised by other candidates on those cells so
 * we have to prise them out by ensuing the Quad applies to at least on unit.
 */
public class HiddenQuadSolveStrategy implements SudokuSolveStrategy {
    @Override
    public boolean update(final SudokuBoard board) {
        final ImmutableList<Unit> units = board.getAllUnits();
        final int[] sizes = new int[] {2, 3, 4};
        final Cell[] cellSet = new Cell[4];

        boolean hasUpdate = false;

        for (final Unit unit : units) {
            final ImmutableList<Cell> cells = unit.cells();
            for (final int candidateSet4 : Utils.CHOOSE_4_BIT_SET) {
                int i = 0;
                boolean skip = false;

                for (Cell cell : cells) {
                    if (!cell.hasHiddenQuad() &&
                        cell.isNotFilled() &&
                        Utils.isIn(sizes, Utils.size(cell.getCandidateSet() & candidateSet4))) {
                        if (i < 4) {
                            cellSet[i++] = cell;
                        } else {
                            skip = true;
                            break;
                        }
                    }
                }

                if (!skip && i == 4) {
                    final int candidateSetToUnset = ((~candidateSet4) & Utils.ALL);
                    hasUpdate = hasUpdate || board.unsetCandidate(cellSet[0].getR(), cellSet[0].getC(), candidateSetToUnset);
                    hasUpdate = hasUpdate || board.unsetCandidate(cellSet[1].getR(), cellSet[1].getC(), candidateSetToUnset);
                    hasUpdate = hasUpdate || board.unsetCandidate(cellSet[2].getR(), cellSet[2].getC(), candidateSetToUnset);
                    hasUpdate = hasUpdate || board.unsetCandidate(cellSet[3].getR(), cellSet[3].getC(), candidateSetToUnset);

                    board.markHiddenQuad(cellSet[0], cellSet[1], cellSet[2], cellSet[3]);
                }
            }
        }

        return hasUpdate;
    }
}
