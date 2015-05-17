package com.github.appocalypse.sudokusolver.strategy;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class PointingPairSolveStrategyTest {

    private final PointingPairSolveStrategy strategy = new PointingPairSolveStrategy();

    @Test
    public void testPointingPair0() {
        SudokuSolveStratetgyTestUtils.assertUpdate("pointing_pair_0.txt", strategy);
    }

    @Test
    public void testPointingPair1() {
        SudokuSolveStratetgyTestUtils.assertUpdate("pointing_pair_1.txt", strategy);
    }

    @Test
    public void testPointingTriple0() {
        SudokuSolveStratetgyTestUtils.assertUpdate("pointing_triple_0.txt", strategy);
    }
}
