package com.github.appocalypse.sudokusolver;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class PointingPairOrTripleSolveStrategyTest {

    private final PointingPairOrTripleSolveStrategy strategy = new PointingPairOrTripleSolveStrategy();

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
