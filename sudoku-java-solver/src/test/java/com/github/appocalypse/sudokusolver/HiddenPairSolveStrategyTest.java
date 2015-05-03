package com.github.appocalypse.sudokusolver;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class HiddenPairSolveStrategyTest {

    private final HiddenPairSolveStrategy strategy = new HiddenPairSolveStrategy();

    @Test
    public void testHiddenPair0() {
        SudokuBoard board = SolveStratetgyTest.getBoard("hidden_pair_0.txt");
        assertTrue(strategy.update(board));
    }

    @Test
    public void testHiddenPair1() {
        SudokuBoard board = SolveStratetgyTest.getBoard("hidden_pair_1.txt");
        assertTrue(strategy.update(board));
    }
}
