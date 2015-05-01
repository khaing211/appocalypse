package com.github.appocalypse.sudokusolver;

import com.google.common.collect.ImmutableList;

/**
 * Hidden Triples will be disguised by other candidates on those cells so
 * we have to prise them out by ensuing the Triple applies to at least on unit.
 */
public class HiddenTripleSolveStrategy implements SudokuSolveStrategy {
    @Override
    public boolean update(final SudokuBoard board) {
        return HiddenSolveStrategy.update(board, Utils.CHOOSE_3_BIT_SET, 3, new int[] {2, 3});
    }
}
