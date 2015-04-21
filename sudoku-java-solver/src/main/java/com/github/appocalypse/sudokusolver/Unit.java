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

    ImmutableList<RowUnit> groupByRow();

    ImmutableList<ColUnit> groupByCol();

    ImmutableList<BoxUnit> groupByBox();
}
