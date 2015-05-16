package com.github.appocalypse.sudokusolver;

import com.google.common.collect.ImmutableList;

import java.util.HashMap;
import java.util.Map;

/**
 * A Hidden Pair is a set of two cells of a common unit containing a same set of two candidates that
 * other cells in the unit has in candidate set
 *
 * It is clear up all other candidate(s) beside the two candidates in the two cells.
 */
public class HiddenPairSolveStrategy implements SudokuSolveStrategy {
    @Override
    public boolean update(final SudokuBoard board) {
        return HiddenSolveStrategy.update(board, Utils.CHOOSE_2_BIT_SET, 2, new int[] {2});
    }
}
