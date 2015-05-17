package com.github.appocalypse.sudokusolver.strategy;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class NakedTripleSolveStrategyTest {
    private final NakedTripleSolveStrategy strategy = new NakedTripleSolveStrategy();

    @Test
    public void testNakedTriples0() {
        SudokuSolveStratetgyTestUtils.assertUpdate("naked_triples_0.txt", strategy);
    }

    @Test
    public void testNakedTriples1() {
        SudokuSolveStratetgyTestUtils.assertUpdate("naked_triples_1.txt", strategy);
    }
}
