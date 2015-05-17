package com.github.appocalypse.sudokusolver.strategy;

import com.github.appocalypse.sudokusolver.SudokuBoard;
import com.github.appocalypse.sudokusolver.SudokuSolveStrategy;
import com.github.appocalypse.sudokusolver.Utils;

/**
 * Any group of four cells in the same unit that contain IN TOTAL four candidates is a Naked Quad
 *
 * When this happens, the four candidates can be removed from all other cells in the same unit.
 */
public class NakedQuadSolveStrategy implements SudokuSolveStrategy {
    @Override
    public boolean update(SudokuBoard board) {
        return NakedSolveStrategy.update(board, Utils.CHOOSE_4_SET, new int[]{2,3,4}, 4);
    }
}
