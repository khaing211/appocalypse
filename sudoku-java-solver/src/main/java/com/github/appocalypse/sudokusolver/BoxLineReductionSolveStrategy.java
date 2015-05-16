package com.github.appocalypse.sudokusolver;

import com.google.common.collect.ImmutableList;

import java.util.Arrays;

public class BoxLineReductionSolveStrategy implements SudokuSolveStrategy {
    @Override
    public boolean update(final SudokuBoard board) {
        if (reduceRow(board)) return true;
        else return reduceCol(board);
    }


    private boolean reduceRow(final SudokuBoard board) {
        final ImmutableList<RowUnit> rowUnits = board.groupByRow();

        final Cell[] foundCells = new Cell[3];
        int foundCellIndex = 0;

        for (int i = 0; i < rowUnits.size(); i++) {
            final ImmutableList<Cell> rowUnitCells = rowUnits.get(i).cells();

            for (int n = 1; n <= 9; n++) {

                // reset cell count per potential candidate
                Arrays.fill(foundCells, null);
                foundCellIndex = 0;

                for (int j = 0; j < rowUnitCells.size(); j++) {
                    final Cell rowUnitCell = rowUnitCells.get(j);

                    // if the number is already fill for the box
                    if (rowUnitCell.isFilled() && rowUnitCell.getN() == n) {
                        break;
                    }

                    if (rowUnitCell.isCandidate(n)) {
                        // found more than 3 cells having same candidate
                        if (foundCellIndex == foundCells.length) {
                            // keep the count over 3 i.e. 4
                            foundCellIndex++;
                            break;
                        } else {
                            foundCells[foundCellIndex++] = rowUnitCell;
                        }
                    }
                }


                if (2 <= foundCellIndex &&  foundCellIndex <= 3) {
                    boolean hasUpdate = false;

                    if (Utils.isInSameBox(foundCells)) {

                        final int baseR = foundCells[0].getBaseR();
                        final int baseC = foundCells[0].getBaseC();

                        for (int offsetR = 0; offsetR < 3; offsetR++) {
                            for (int offsetC = 0; offsetC < 3; offsetC++) {
                                final int r = baseR + offsetR;
                                final int c = baseC + offsetC;

                                if (Utils.isNot(board.getCell(r, c), foundCells)) {
                                    hasUpdate = board.unsetSingleCandidate(r, c, n) || hasUpdate;
                                }
                            }
                        }
                    }

                    if (hasUpdate) {
                        return true;
                    }

                }
            }

        }

        return false;
    }

    private boolean reduceCol(final SudokuBoard board) {
        final ImmutableList<ColUnit> colUnits = board.groupByCol();

        final Cell[] foundCells = new Cell[3];
        int foundCellIndex = 0;

        for (int i = 0; i < colUnits.size(); i++) {
            final ImmutableList<Cell> colUnitCells = colUnits.get(i).cells();

            for (int n = 1; n <= 9; n++) {

                // reset cell count per potential candidate
                Arrays.fill(foundCells, null);
                foundCellIndex = 0;

                for (int j = 0; j < colUnitCells.size(); j++) {
                    final Cell colUnitCell = colUnitCells.get(j);

                    // if the number is already fill for the box
                    if (colUnitCell.isFilled() && colUnitCell.getN() == n) {
                        break;
                    }

                    if (colUnitCell.isCandidate(n)) {
                        // found more than 3 cells having same candidate
                        if (foundCellIndex == foundCells.length) {
                            // keep the count over 3 i.e. 4
                            foundCellIndex++;
                            break;
                        } else {
                            foundCells[foundCellIndex++] = colUnitCell;
                        }
                    }
                }


                if (2 <= foundCellIndex &&  foundCellIndex <= 3) {
                    boolean hasUpdate = false;

                    if (Utils.isInSameBox(foundCells)) {

                        final int baseR = foundCells[0].getBaseR();
                        final int baseC = foundCells[0].getBaseC();

                        for (int offsetR = 0; offsetR < 3; offsetR++) {
                            for (int offsetC = 0; offsetC < 3; offsetC++) {
                                final int r = baseR + offsetR;
                                final int c = baseC + offsetC;

                                if (Utils.isNot(board.getCell(r, c), foundCells)) {
                                    hasUpdate = board.unsetSingleCandidate(r, c, n) || hasUpdate;
                                }
                            }
                        }
                    }

                    if (hasUpdate) {
                        return true;
                    }
                }
            }

        }

        return false;
    }
}
