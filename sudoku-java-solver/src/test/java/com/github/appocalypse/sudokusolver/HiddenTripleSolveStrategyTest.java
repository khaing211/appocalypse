package com.github.appocalypse.sudokusolver;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class HiddenTripleSolveStrategyTest {
    private final HiddenTripleSolveStrategy strategy = new HiddenTripleSolveStrategy();

    @Test
    public void testHiddenTriples0() {
        SudokuBoard board = SolveStratetgyTest.getBoard("hidden_triples_0.txt");
        assertTrue(strategy.update(board));
    }
}
