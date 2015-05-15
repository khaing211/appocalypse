package com.github.appocalypse.sudokusolver;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class NakedTripleSolveStrategyTest {
    private final NakedTripleSolveStrategy strategy = new NakedTripleSolveStrategy();

    @Test
    public void testNakedTriples0() {
        SudokuBoard board = SolveStratetgyTest.getBoard("naked_triples_0.txt");
        assertTrue(strategy.update(board));
    }

    @Test
    public void testNakedTriples1() {
        SudokuBoard board = SolveStratetgyTest.getBoard("naked_triples_1.txt");
        SudokuBoard testBoard = new SudokuBoard(board);
        assertTrue("\n" + board.sideBySide(testBoard), strategy.update(testBoard));
    }
}
