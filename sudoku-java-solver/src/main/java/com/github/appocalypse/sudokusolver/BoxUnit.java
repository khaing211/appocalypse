package com.github.appocalypse.sudokusolver;

import com.google.common.collect.ImmutableList;

public class BoxUnit implements Unit {
    private final int boxR;
    private final int boxC;
    private final ImmutableList<Cell> cells;

    public BoxUnit(int boxR, int boxC, ImmutableList<Cell> cells) {
        this.boxR = boxR;
        this.boxC = boxC;
        this.cells = cells;
    }

    public int getBoxR() {
        return boxR;
    }

    public int getBoxC() {
        return boxC;
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
