package com.github.appocalypse.sudokusolver;

import java.util.Optional;

public interface SudokuSolveStrategy {
    /**
     * @param board to update candidates or fill out the cell
     * @return true if strategy has update the board
     */
    boolean update(SudokuBoard board);
}
