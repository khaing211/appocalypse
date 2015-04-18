package com.github.appocalypse.sudokusolver;

import java.util.Optional;

/**
 * NakedSingle is to eliminate cells with only one last remaining candidate.
 */
public class NakedSingleSolveStrategy implements SudokuSolveStrategy {
    @Override
    public boolean update(SudokuBoard board) {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (!board.isFilled(r, c)) {
                    if (board.countPossibles(r, c) == 1) {
                        final int[] possibles = board.getPossibles(r, c);
                        board.setNumber(r, c, possibles[0]);
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
