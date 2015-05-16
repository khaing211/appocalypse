package com.github.appocalypse.sudokusolver;

import com.google.common.collect.ImmutableList;

import java.util.Arrays;

/**
 * A Pair or Triple in a box - if they are aligned on a row, n can be removed from the rest of the row.
 *
 * A Pair or Triple in a box - if they are aligned on a column, n can be removed from the rest of the column.
 */
public class PointingPairOrTripleSolveStrategy implements SudokuSolveStrategy {
    @Override
    public boolean update(final SudokuBoard board) {
        final ImmutableList<BoxUnit> boxUnits = board.groupByBox();

        final Cell[] foundCells = new Cell[3];
        int foundCellIndex = 0;

        boolean hasUpdate = false;

        for (int i = 0; i < boxUnits.size(); i++) {
            final ImmutableList<Cell> boxUnitCells = boxUnits.get(i).cells();

            for (int n = 1; n <= 9; n++) {

                // reset cell count per potential candidate
                Arrays.fill(foundCells, null);
                foundCellIndex = 0;

                for (int j = 0; j < boxUnitCells.size(); j++) {
                    final Cell boxUnitCell = boxUnitCells.get(j);

                    // if the number is already fill for the box
                    if (boxUnitCell.isFilled() && boxUnitCell.getN() == n) {
                        break;
                    }

                    if (boxUnitCell.isCandidate(n)) {
                        // found more than 3 cells having same candidate
                        if (foundCellIndex == foundCells.length) {
                            // keep the count over 3 i.e. 4
                            foundCellIndex++;
                            break;
                        } else {
                            foundCells[foundCellIndex++] = boxUnitCell;
                        }
                    }
                }

                if (2 <= foundCellIndex &&  foundCellIndex <= 3) {
                    if (Utils.isInSameRow(foundCells)) {

                        final int r = foundCells[0].getR();
                        final int[] ignoreCol = new int[foundCellIndex];

                        for (int j = 0; j < foundCellIndex; j++) {
                            ignoreCol[j] = foundCells[j].getC();
                        }

                        for (int c = 0; c < 9; c++) {
                            if (Utils.isNotIn(ignoreCol, c)) {
                                hasUpdate = board.unsetSingleCandidate(r, c, n) || hasUpdate;
                            }
                        }

                    } else if (Utils.isInSameCol(foundCells)) {

                        final int c = foundCells[0].getC();
                        final int[] ignoreRow = new int[foundCellIndex];

                        for (int j = 0; j < foundCellIndex; j++) {
                            ignoreRow[j] = foundCells[j].getR();
                        }

                        for (int r = 0; r < 9; r++) {
                            if (Utils.isNotIn(ignoreRow, r)) {
                                hasUpdate = board.unsetSingleCandidate(r, c, n) || hasUpdate;
                            }
                        }
                    }
                }
            }
        }

        return hasUpdate;
    }
}
