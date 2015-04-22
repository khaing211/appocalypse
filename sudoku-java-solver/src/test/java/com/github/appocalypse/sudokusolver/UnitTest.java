package com.github.appocalypse.sudokusolver;

import com.google.common.collect.ImmutableList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * test for Unit interface
 */
public interface UnitTest {
    default void testGroupByRow(Unit unit) {
        final ImmutableList<RowUnit> rows = unit.groupByRow();
        for (int r = 0; r < rows.size(); r++) {
            final RowUnit row = rows.get(r);
            final ImmutableList<Cell> cells = row.cells();
            for (int c = 0; c < cells.size(); c++) {
                final Cell cell = cells.get(c);
                // single row cannot have duplicate columns
                for (int c1 = c + 1; c1 < cells.size(); c1++) {
                    assertNotEquals(cells.get(c1).getC(), cell.getC());
                }

                assertEquals(cell.getR(), row.getR());
            }
        }
    }

    default void testGroupByCol(Unit unit) {
        final ImmutableList<ColUnit> cols = unit.groupByCol();
        for (int c = 0; c < cols.size(); c++) {
            final ColUnit col = cols.get(c);
            final ImmutableList<Cell> cells = col.cells();

            for (int r = 0; r < cells.size(); r++) {
                final Cell cell = cells.get(r);
                // single column cannot have duplicate row
                for (int r1 = r + 1; r1 < cells.size(); r1++) {
                    assertNotEquals(cells.get(r1).getR(), cell.getR());
                }

                assertEquals(cell.getC(), col.getC());
            }
        }
    }

    default void testGroupByBox(Unit unit) {
        final ImmutableList<BoxUnit> boxes = unit.groupByBox();
        for (int b = 0; b < boxes.size(); b++) {
            final BoxUnit box = boxes.get(b);
            final ImmutableList<Cell> cells = box.cells();

            for (int c = 0; c < cells.size(); c++) {
                final Cell cell = cells.get(c);

                assertEquals(box.getBoxR(), cell.getBoxR());
                assertEquals(box.getBoxC(), cell.getBoxC());
            }
        }
    }
}
