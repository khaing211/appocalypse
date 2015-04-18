package com.github.appocalypse.sudokusolver;

import java.util.Optional;

/**
 * NakedSingle is to eliminate cells with only one last remaining candidate.
 */
public class NakedSingleSolveStrategy implements SudokuSolveStrategy {
    @Override
    public Optional<SudokuStrategyResult> solve(SudokuBoard board) {
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (!board.isFilled(r, c)) {
                    final int curPossibleCount = board.countPossibles(r, c);
                    if (curPossibleCount == 1) {
                        final int[] possibles = board.getPossibles(r, c);
                        return Optional.of(new SudokuStrategyResult(r, c, possibles[0]));
                    }
                }
            }
        }

        return Optional.empty();
    }
}
