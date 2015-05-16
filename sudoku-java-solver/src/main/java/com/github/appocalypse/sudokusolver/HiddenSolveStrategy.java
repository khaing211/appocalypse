package com.github.appocalypse.sudokusolver;

import com.google.common.collect.ImmutableList;

import java.util.List;

public interface HiddenSolveStrategy {
    static boolean hasHiddenMark(Cell cell, int candidateSetSize) {
        switch (candidateSetSize) {
            case 2: return cell.hasHiddenPair();
            case 3: return cell.hasHiddenTriple();
            case 4: return cell.hasHiddenQuad();
            default: return false;
        }
    }

    static boolean update(final SudokuBoard board, final List<Integer> candidateSets, final int candidateSetSize, final int[] sizes) {
        final ImmutableList<Unit> units = board.getAllUnits();

        final Cell[] cellSet = new Cell[candidateSetSize];

        for (final Unit unit : units) {

            final ImmutableList<Cell> cells = unit.cells();

            for (final int candidateSet : candidateSets) {
                int numCellHasCandidateSet = 0;

                for (Cell cell : cells) {
                    if (!hasHiddenMark(cell, candidateSetSize) &&
                        cell.isNotFilled() &&
                        Utils.isIn(sizes, Utils.size(cell.getCandidateSet() & candidateSet))) {

                        if (numCellHasCandidateSet < candidateSetSize) {
                            cellSet[numCellHasCandidateSet] = cell;
                        }

                        numCellHasCandidateSet++;

                        if (numCellHasCandidateSet > candidateSetSize) {
                            break;
                        }
                    }
                }

                if (numCellHasCandidateSet == candidateSetSize) {
                    final int candidateSetToUnset = ((~candidateSet) & Utils.ALL);

                    boolean hasUpdate = false;

                    for (int j = 0; j < candidateSetSize; j++) {
                        hasUpdate = board.unsetCandidate(cellSet[j].getR(), cellSet[j].getC(), candidateSetToUnset) || hasUpdate;
                    }

                    if (hasUpdate) {
                        board.markHidden(cellSet);

                        return true;
                    }
                }
            }

        }

        return false;
    }
}
