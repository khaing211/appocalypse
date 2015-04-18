package com.github.appocalypse.sudokusolver;

import java.util.Optional;

public interface SudokuSolveStrategy {
    Optional<SudokuStrategyResult> solve(SudokuBoard board);
}
