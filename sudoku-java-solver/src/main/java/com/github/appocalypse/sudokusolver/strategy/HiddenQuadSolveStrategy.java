package com.github.appocalypse.sudokusolver.strategy;

import com.github.appocalypse.sudokusolver.SudokuBoard;
import com.github.appocalypse.sudokusolver.SudokuSolveStrategy;
import com.github.appocalypse.sudokusolver.Utils;

/**
 * Hidden Quad will be disguised by other candidates on those cells so
 * we have to prise them out by ensuing the Quad applies to at least on unit.
 */
public class HiddenQuadSolveStrategy implements SudokuSolveStrategy {
    @Override
    public boolean update(final SudokuBoard board) {
        return HiddenSolveStrategy.update(board, Utils.CHOOSE_4_BIT_SET, 4, new int[] {2, 3, 4});
    }
}
