package com.github.appocalypse.sudokusolver.strategy;

import com.github.appocalypse.sudokusolver.SudokuBoard;
import com.github.appocalypse.sudokusolver.SudokuSolveStrategy;
import com.github.appocalypse.sudokusolver.Utils;

/**
 * A Naked Triple is slightly more complicated because it does not always imply three numbers each in three cells.
 *
 * Any group of three cells in the same unit that contain IN TOTAL three candidates is a Naked Triple
 *
 * When this happens, the three candidates can be removed from all other cells in the same unit.
 */
public class NakedTripleSolveStrategy implements SudokuSolveStrategy {
    @Override
    public boolean update(final SudokuBoard board) {
        return NakedSolveStrategy.update(board, Utils.CHOOSE_3_SET, new int[]{2,3}, 3);
    }
}
