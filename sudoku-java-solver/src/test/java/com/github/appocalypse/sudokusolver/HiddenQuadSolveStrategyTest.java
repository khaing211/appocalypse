package com.github.appocalypse.sudokusolver;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class HiddenQuadSolveStrategyTest {
    private final HiddenQuadSolveStrategy strategy = new HiddenQuadSolveStrategy();

    @Test
    public void testHiddenQuad0() {
        SudokuBoard board = SolveStratetgyTest.getBoard("hidden_quad_0.txt");
        assertTrue(strategy.update(board));
    }

    @Test
    public void testHiddenQuad1() {
        SudokuBoard board = SolveStratetgyTest.getBoard("hidden_quad_1.txt");
        assertTrue(strategy.update(board));
    }
}
