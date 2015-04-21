package com.github.appocalypse.sudokusolver;

import com.google.common.collect.ImmutableList;

public class ColUnit implements Unit {
    private final int c;
    private final ImmutableList<Cell> cells;

    public ColUnit(int c, ImmutableList<Cell> cells) {
        this.c = c;
        this.cells = cells;
    }

    public int getC() {
        return c;
    }

    @Override
    public ImmutableList<Cell> cells() {
        return cells;
    }

    @Override
    public ImmutableList<RowUnit> groupByRow() {
        return null;
    }

    @Override
    public ImmutableList<ColUnit> groupByCol() {
        return null;
    }

    @Override
    public ImmutableList<BoxUnit> groupByBox() {
        return null;
    }
}
