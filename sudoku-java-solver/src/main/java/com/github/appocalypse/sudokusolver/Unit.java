package com.github.appocalypse.sudokusolver;

import com.google.common.collect.ImmutableList;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collector;

/**
 * Unit is a group of unordered cells, could be RowUnit, ColUnit, or BoxUnit
 */
public interface Unit {
    /**
     * @return cells are in no particular order
     */
    ImmutableList<Cell> cells();

    /**
     * Default implementation assume no particular order of the cell()
     */
    default ImmutableList<BoxUnit> groupByBox() {
        final ImmutableList<Cell> cells = cells();

        switch (cells.size()) {
            case 0: return ImmutableList.of();
            case 1: return ImmutableList.of(new BoxUnit(cells.get(0).getBoxR(), cells.get(0).getBoxC(), cells));
        }

        final Map<String, ImmutableList.Builder<Cell>> boxesMap = new HashMap<String, ImmutableList.Builder<Cell>>(9);
        for (Cell cell : cells) {
            boxesMap.compute(String.format("%dx%d", cell.getBoxR(), cell.getBoxC()), (k, v) -> {
                if (v == null) {
                    return ImmutableList.<Cell>builder().add(cell);
                } else {
                    return v.add(cell);
                }
            });
        }

        final ImmutableList.Builder<BoxUnit> boxes = ImmutableList.builder();
        for (ImmutableList.Builder<Cell> boxCellBuilder : boxesMap.values()) {
            ImmutableList<Cell> boxCells = boxCellBuilder.build();
            boxes.add(new BoxUnit(boxCells.get(0).getBoxR(), boxCells.get(0).getBoxC(), boxCells));
        }

        return boxes.build();
    }

    /**
     * Default implementation assume no particular order of the cell()
     */
    default ImmutableList<RowUnit> groupByRow() {
        final ImmutableList<Cell> cells = cells();

        switch (cells.size()) {
            case 0: return ImmutableList.of();
            case 1: return ImmutableList.of(new RowUnit(cells.get(0).getR(), cells));
        }

        final Cell[] cellsArray = cells.toArray(new Cell[cells.size()]);
        // save a bit memory on array for O(1) (max 81 elements) sort
        Arrays.sort(cellsArray, Comparator.comparingInt(Cell::getR));

        final ImmutableList.Builder<RowUnit> rows = ImmutableList.builder();

        int i = 0;
        int j = i+1;
        while (j < cells.size()) {
            if (cells.get(i).getR() != cells.get(j).getR()) {
                rows.add(new RowUnit(cells.get(i).getR(), cells.subList(i, j)));
                i = j;
            }
            j++;
        }

        rows.add(new RowUnit(cells.get(i).getR(), cells.subList(i, j)));
        return rows.build();
    }

    /**
     * Default implementation assume no particular order of the cell()
     */
    default ImmutableList<ColUnit> groupByCol() {
        final ImmutableList<Cell> cells = cells();

        switch (cells.size()) {
            case 0: return ImmutableList.of();
            case 1: return ImmutableList.of(new ColUnit(cells.get(0).getC(), cells));
        }

        final Cell[] cellsArray = cells.toArray(new Cell[cells.size()]);

        // save a bit memory on array for O(1) (max 81 elements) sort
        Arrays.sort(cellsArray, Comparator.comparingInt(Cell::getC));

        final ImmutableList.Builder<ColUnit> cols = ImmutableList.builder();

        int i = 0;
        int j = i+1;
        while (j < cells.size()) {
            if (cells.get(i).getC() != cells.get(j).getC()) {
                cols.add(new ColUnit(cells.get(i).getC(), cells.subList(i, j)));
                i = j;
            }
            j++;
        }

        cols.add(new ColUnit(cells.get(i).getC(), cells.subList(i, j)));
        return cols.build();
    }
}
