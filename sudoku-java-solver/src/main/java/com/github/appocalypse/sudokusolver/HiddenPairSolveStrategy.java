package com.github.appocalypse.sudokusolver;

import com.google.common.collect.ImmutableList;

import java.util.HashMap;
import java.util.Map;

/**
 * A Hidden Pair is a set of two cells of a common unit containing a same set of two candidates that
 * other cells in the unit has in candidate set
 *
 * It is clear up all other candidate(s) beside the two candidates in the two cells.
 */
public class HiddenPairSolveStrategy implements SudokuSolveStrategy {
    @Override
    public boolean update(final SudokuBoard board) {
        final ImmutableList<Unit> units = board.getAllUnits();
        final Cell[] cellSet = new Cell[2];

        boolean hasUpdate = false;

        for (final Unit unit : units) {
            final Map<Integer, Long> candidateSetToCount = new HashMap<>();
            final ImmutableList<Cell> cells = unit.cells();
            for (final int candidateSet2 : Utils.CHOOSE_2_BIT_SET) {
                int i = 0;
                boolean skip = false;

                for (Cell cell : cells) {
                    if (!cell.hasHiddenPair() &&
                        cell.isNotFilled() &&
                        (cell.getCandidateSet() & candidateSet2) == candidateSet2) {
                        if (i < 2) {
                            cellSet[i++] = cell;
                        } else {
                            skip = true;
                            break;
                        }
                    }
                }

                if (!skip && i == 2) {
                    // clear every other possible for 2 cells
                    final int candidateSetToUnset = ((~candidateSet2) & Utils.ALL);
                    hasUpdate = hasUpdate || board.unsetCandidate(cellSet[0].getR(), cellSet[0].getC(), candidateSetToUnset);
                    hasUpdate = hasUpdate || board.unsetCandidate(cellSet[1].getR(), cellSet[1].getC(), candidateSetToUnset);

                    board.markHiddenPair(cellSet[0], cellSet[1]);
                }
            }

        }

        return hasUpdate;
    }
}
