package com.github.appocalypse.sudokusolver;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SudokuBoardTest implements UnitTest {
    @Test
    public void testGroupByRow() {
        final SudokuBoard board = new SudokuBoard();
        testGroupByRow(board);
    }

    @Test
    public void testGroupByCol() {
        final SudokuBoard board = new SudokuBoard();
        testGroupByCol(board);
    }

    @Test
    public void testGroupByBox() {
        final SudokuBoard board = new SudokuBoard();
        testGroupByBox(board);
    }
}
