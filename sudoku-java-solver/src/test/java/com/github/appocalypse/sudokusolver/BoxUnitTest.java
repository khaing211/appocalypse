package com.github.appocalypse.sudokusolver;

import org.junit.Test;

public class BoxUnitTest implements UnitTest {
    @Test
    public void testGroupByRow() {
        BoxUnit boxUnit = new SudokuBoard().groupByBox().get(0);
        testGroupByRow(boxUnit);
    }

    @Test
    public void testGroupByCol() {
        BoxUnit boxUnit = new SudokuBoard().groupByBox().get(0);
        testGroupByCol(boxUnit);
    }

    @Test
    public void testGroupByBox() {
        BoxUnit boxUnit = new SudokuBoard().groupByBox().get(0);
        testGroupByBox(boxUnit);
    }
}
