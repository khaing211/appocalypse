package com.github.appocalypse.sudokusolver;

import com.google.common.collect.ImmutableList;

/**
 * A Naked Pair is a set of two candidate numbers sited in two cells that belong to at least one unit in common.
 * That is they reside in the same row, box or column.
 *
 * It is clear that the solution will contain those values in those two cells and all other candidates with
 * those numbers can be removed from whatever unit(s) they have in common.
 */
public class NakedPairSolveStrategy implements SudokuSolveStrategy {
    @Override
    public boolean update(final SudokuBoard board) {
        return NakedSolveStrategy.update(board, Utils.CHOOSE_2_SET, new int[]{2}, 2);
    }
}
