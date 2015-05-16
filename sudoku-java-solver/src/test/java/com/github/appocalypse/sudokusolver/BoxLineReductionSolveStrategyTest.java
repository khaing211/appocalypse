package com.github.appocalypse.sudokusolver;

import org.junit.Test;

public class BoxLineReductionSolveStrategyTest {

    private final BoxLineReductionSolveStrategy strategy = new BoxLineReductionSolveStrategy();

    @Test
    public void testBoxLineReductionPair0() {
        SudokuSolveStratetgyTestUtils.assertUpdate("boxline_reduction_pair_0.txt", strategy);
    }

    @Test
    public void testBoxLineReductionPair1() {
        SudokuSolveStratetgyTestUtils.assertUpdate("boxline_reduction_pair_1.txt", strategy);
    }

    @Test
    public void testBoxLineReductionTriple0() {
        SudokuSolveStratetgyTestUtils.assertUpdate("boxline_reduction_triple_0.txt", strategy);
    }
}
