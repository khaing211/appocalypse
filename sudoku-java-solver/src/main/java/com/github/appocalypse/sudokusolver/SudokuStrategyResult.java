package com.github.appocalypse.sudokusolver;

public class SudokuStrategyResult {
    private final int r, c, n;

    public SudokuStrategyResult(int r, int c, int n) {
        this.r = r;
        this.c = c;
        this.n = n;
    }

    public void apply(SudokuBoard board) {
        board.setNumber(r, c, n);
    }
}
