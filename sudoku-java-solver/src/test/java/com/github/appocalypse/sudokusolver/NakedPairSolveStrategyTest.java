package com.github.appocalypse.sudokusolver;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class NakedPairSolveStrategyTest {
    private final NakedPairSolveStrategy strategy = new NakedPairSolveStrategy();

    @Test
    public void testNakedPair0() {
        SudokuBoard board = SolveStratetgyTest.getBoard("naked_pair_0.txt");
        assertTrue(strategy.update(board));
    }

    @Test
    public void testNakedPair1() {
        SudokuBoard board = SolveStratetgyTest.getBoard("naked_pair_1.txt");
        assertTrue(strategy.update(board));
    }
}
