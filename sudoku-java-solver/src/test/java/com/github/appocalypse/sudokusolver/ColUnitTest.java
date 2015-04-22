package com.github.appocalypse.sudokusolver;

import org.junit.Test;

public class ColUnitTest implements UnitTest {
    @Test
    public void testGroupByBox() {
        ColUnit colUnit = new SudokuBoard().groupByCol().get(3);
        testGroupByBox(colUnit);
    }

    @Test
    public void testGroupByCol() {
        ColUnit colUnit = new SudokuBoard().groupByCol().get(3);
        testGroupByCol(colUnit);
    }

    @Test
    public void testGroupByRow() {
        ColUnit colUnit = new SudokuBoard().groupByCol().get(3);
        testGroupByRow(colUnit);
    }
}
