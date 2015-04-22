package com.github.appocalypse.sudokusolver;

import org.junit.Test;

public class RowUnitTest implements UnitTest {
    @Test
    public void testGroupByBox() {
        RowUnit rowUnit = new SudokuBoard().groupByRow().get(3);
        testGroupByBox(rowUnit);
    }

    @Test
    public void testGroupByCol() {
        RowUnit rowUnit = new SudokuBoard().groupByRow().get(3);
        testGroupByCol(rowUnit);
    }

    @Test
    public void testGroupByRow() {
        RowUnit rowUnit = new SudokuBoard().groupByRow().get(3);
        testGroupByRow(rowUnit);
    }
}
