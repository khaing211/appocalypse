package com.github.appocalypse.sudokusolver;

import java.util.Optional;

public interface SudokuSolveStrategy {
    /**
     * @param board to update possibles or fill out the cell
     * @return true if strategy has update the board
     */
    boolean update(SudokuBoard board);
}
