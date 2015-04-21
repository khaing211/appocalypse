package com.github.appocalypse.sudokusolver;

import com.google.common.collect.ImmutableList;

public class RowUnit implements Unit {
    private final int r;
    private final ImmutableList<Cell> cells;

    public RowUnit(int r, ImmutableList<Cell> cells) {
        this.r = r;
        this.cells = cells;
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

    public int getR() {
        return r;
    }
}
