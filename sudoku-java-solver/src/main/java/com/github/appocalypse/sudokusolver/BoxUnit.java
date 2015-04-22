package com.github.appocalypse.sudokusolver;

import com.google.common.collect.ImmutableList;

/**
 * A unit of a single box i.e. cell belong to same box i.e.
 * forall cell.getBoxR() are same and forall cell.getBoxC() are same.
 */
public class BoxUnit implements Unit {
    private final int boxR;
    private final int boxC;
    private final ImmutableList<Cell> cells;

    // nocheck constructor
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
    public ImmutableList<BoxUnit> groupByBox() {
        return ImmutableList.of(this);
    }
}
