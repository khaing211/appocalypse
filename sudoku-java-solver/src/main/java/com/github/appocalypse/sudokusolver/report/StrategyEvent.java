package com.github.appocalypse.sudokusolver.report;

import com.github.appocalypse.sudokusolver.SudokuBoard;

public class StrategyEvent {
    private final String strategy;
    private final SudokuBoard oldBoard;
    private final SudokuBoard newBoard;

    public StrategyEvent(String strategy, SudokuBoard oldBoard, SudokuBoard newBoard) {
        this.strategy = strategy;
        this.oldBoard = oldBoard;
        this.newBoard = newBoard;
    }

    public SudokuBoard getOldBoard() {
        return oldBoard;
    }

    public SudokuBoard getNewBoard() {
        return newBoard;
    }

    public String getStrategy() {
        return strategy;
    }

    @Override
    public String toString() {
        return "StrategyEvent[" +
                strategy + "]\n" +
                oldBoard.sideBySide(newBoard);
    }
}
