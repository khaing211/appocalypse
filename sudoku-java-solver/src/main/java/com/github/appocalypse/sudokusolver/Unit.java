package com.github.appocalypse.sudokusolver;

import com.google.common.collect.ImmutableList;

import java.util.stream.Collector;

/**
 * Unit is a group of unordered cells, could be RowUnit, ColUnit, or BoxUnit
 */
public interface Unit {
    /**
     * @return cells are in no particular order
     */
    ImmutableList<Cell> cells();

    default ImmutableList<ImmutableList<Cell>> groupByRow() {
        return ImmutableList.of();
    }

    default ImmutableList<ImmutableList<Cell>> groupByCol() {
        return ImmutableList.of();
    }


    default ImmutableList<ImmutableList<Cell>> groupByBox() {
        return ImmutableList.of();
    }
}
