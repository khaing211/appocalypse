package com.github.appocalypse.sudokusolver;

import java.util.Optional;

public class NakedPairSolveStrategy implements SudokuSolveStrategy {
    @Override
    public Optional<SudokuStrategyResult> solve(SudokuBoard board) {
        final short[][] nakedPairPossible = board.getCopyPossible();



        return Optional.empty();
    }
}
