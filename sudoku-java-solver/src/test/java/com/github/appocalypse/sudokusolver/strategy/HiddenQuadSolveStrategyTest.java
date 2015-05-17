package com.github.appocalypse.sudokusolver.strategy;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class HiddenQuadSolveStrategyTest {
    private final HiddenQuadSolveStrategy strategy = new HiddenQuadSolveStrategy();

    @Test
    public void testHiddenQuad0() {
        SudokuSolveStratetgyTestUtils.assertUpdate("hidden_quad_0.txt", strategy);
    }

    @Test
    public void testHiddenQuad1() {
        SudokuSolveStratetgyTestUtils.assertUpdate("hidden_quad_1.txt", strategy);
    }
}
