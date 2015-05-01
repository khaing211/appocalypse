package com.github.appocalypse.sudokusolver;

import com.google.common.collect.ImmutableList;

/**
 * NakedSingle is to eliminate cells with only one last remaining candidate.
 */
public class NakedSingleSolveStrategy implements SudokuSolveStrategy {
    @Override
    public boolean update(SudokuBoard board) {
        final ImmutableList<Cell> cells = board.cells();

        for (Cell cell : cells) {
            if (cell.isNotFilled() && cell.getCandidateSetSize() == 1) {
                board.fill(cell.getR(), cell.getC(), cell.getCandidates()[0]);
                return true;
            }
        }

        return false;
    }
}
