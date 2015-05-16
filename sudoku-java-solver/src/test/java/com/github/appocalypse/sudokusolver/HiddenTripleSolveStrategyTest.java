package com.github.appocalypse.sudokusolver;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class HiddenTripleSolveStrategyTest {
    private final HiddenTripleSolveStrategy strategy = new HiddenTripleSolveStrategy();

    @Test
    public void testHiddenTriples0() {
        SudokuSolveStratetgyTestUtils.assertUpdate("hidden_triples_0.txt", strategy);
    }
}
