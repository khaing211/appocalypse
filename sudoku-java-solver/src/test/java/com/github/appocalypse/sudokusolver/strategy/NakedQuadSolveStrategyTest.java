package com.github.appocalypse.sudokusolver.strategy;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class NakedQuadSolveStrategyTest {
    private final NakedQuadSolveStrategy strategy = new NakedQuadSolveStrategy();

    @Test
    public void testNakedQuad0() {
        SudokuSolveStratetgyTestUtils.assertUpdate("naked_quad_0.txt", strategy);
    }
}
