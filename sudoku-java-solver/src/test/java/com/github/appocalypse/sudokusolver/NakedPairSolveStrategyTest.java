package com.github.appocalypse.sudokusolver;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class NakedPairSolveStrategyTest {
    private final NakedPairSolveStrategy strategy = new NakedPairSolveStrategy();

    @Test
    public void testNakedPair0() {
        SudokuSolveStratetgyTestUtils.assertUpdate("naked_pair_0.txt", strategy);
    }

    @Test
    public void testNakedPair1() {
        SudokuSolveStratetgyTestUtils.assertUpdate("naked_pair_1.txt", strategy);
    }
}
