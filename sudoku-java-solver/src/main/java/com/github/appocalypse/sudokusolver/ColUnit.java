package com.github.appocalypse.sudokusolver;

import com.google.common.collect.ImmutableList;

/**
 * A unit of a single columns i.e. all cells belong to a single column
 */
public class ColUnit implements Unit {
    private final int c;
    private final ImmutableList<Cell> cells;

    // nocheck constructor
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
        switch (cells.size()) {
            case 0: return ImmutableList.of();
            case 1: return ImmutableList.of(new RowUnit(cells.get(0).getR(), cells));
        }

        final ImmutableList.Builder<RowUnit> rows = ImmutableList.builder();
        for (int i = 0; i < cells.size(); i++) {
            rows.add(new RowUnit(cells.get(i).getR(), cells.subList(i, i+1)));
        }

        return rows.build();
    }

    @Override
    public ImmutableList<ColUnit> groupByCol() {
        return ImmutableList.of(this);
    }
}
