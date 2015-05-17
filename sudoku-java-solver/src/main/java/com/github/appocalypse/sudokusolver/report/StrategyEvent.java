package com.github.appocalypse.sudokusolver.report;

import com.github.appocalypse.sudokusolver.SudokuBoard;

public class StrategyEvent {
    private final SudokuBoard oldBoard;
    private final SudokuBoard newBoard;

    public StrategyEvent(SudokuBoard oldBoard, SudokuBoard newBoard) {
        this.oldBoard = oldBoard;
        this.newBoard = newBoard;
    }

    public SudokuBoard getOldBoard() {
        return oldBoard;
    }

    public SudokuBoard getNewBoard() {
        return newBoard;
    }
}
