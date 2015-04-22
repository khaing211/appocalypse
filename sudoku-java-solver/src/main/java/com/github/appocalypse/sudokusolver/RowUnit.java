package com.github.appocalypse.sudokusolver;

import com.google.common.collect.ImmutableList;

/**
 * A unit of a single row i.e. all cells belong a single cell
 */
public class RowUnit implements Unit {
    private final int r;
    private final ImmutableList<Cell> cells;

    // nocheck constructor
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
        return ImmutableList.of(this);
    }

    @Override
    public ImmutableList<ColUnit> groupByCol() {
        switch (cells.size()) {
            case 0: return ImmutableList.of();
            case 1: return ImmutableList.of(new ColUnit(cells.get(0).getC(), cells));
        }

        final ImmutableList.Builder<ColUnit> cols = ImmutableList.builder();
        for (int i = 0; i < cells.size(); i++) {
            cols.add(new ColUnit(cells.get(i).getC(), cells.subList(i, i+1)));
        }

        return cols.build();
    }

    public int getR() {
        return r;
    }
}
