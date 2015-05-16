package com.github.appocalypse.sudokusolver;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class HiddenPairSolveStrategyTest {

    private final HiddenPairSolveStrategy strategy = new HiddenPairSolveStrategy();

    @Test
    public void testHiddenPair0() {
        SudokuSolveStratetgyTestUtils.assertUpdate("hidden_pair_0.txt", strategy);
    }

    @Test
    public void testHiddenPair1() {
        SudokuSolveStratetgyTestUtils.assertUpdate("hidden_pair_1.txt", strategy);
    }
}
