package com.github.appocalypse.sudokusolver;

import com.google.common.collect.ImmutableList;

/**
 * NakedSingle is to eliminate cells with only one last remaining candidate.
 */
public class NakedSingleSolveStrategy implements SudokuSolveStrategy {
    @Override
    public boolean update(SudokuBoard board) {
        // snapshot of the cells
        final ImmutableList<Cell> cells = board.cells();

        boolean hasUpdate = false;
        for (Cell cell : cells) {
            if (cell.isNotFilled() && cell.getCandidateSetSize() == 1) {
                board.fill(cell.getR(), cell.getC(), cell.getCandidates()[0]);
                hasUpdate = hasUpdate || true;
            }
        }

        return hasUpdate;
    }
}
