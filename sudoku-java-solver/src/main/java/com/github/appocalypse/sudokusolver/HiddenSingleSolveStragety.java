package com.github.appocalypse.sudokusolver;

import com.google.common.collect.ImmutableList;

/**
 * HiddenSingle is to eliminate cells that has a candidate X that can't go anywhere else
 * in (exclusive) either row, column or box i.e. it must go here in the cell
 */
public class HiddenSingleSolveStragety implements SudokuSolveStrategy {

    @Override
    public boolean update(SudokuBoard board) {
        // snapshot of the unit

        final ImmutableList<Unit> units = board.getAllUnits();

        boolean hasUpdate = false;

        for (Unit unit : units) {
            hasUpdate = hasUpdate || checkUnitAndFill(unit, board);
        }

        return hasUpdate;
    }

    private boolean checkUnitAndFill(final Unit unit, final SudokuBoard board) {
        final ImmutableList<Cell> cells = unit.cells();

        for (int i = 0; i < cells.size(); i++) {
            final Cell cell = cells.get(i);
            if (cell.isNotFilled()) {
                int differentCandidateSet = cell.getCandidateSet();

                for (int j = 0; j < cells.size(); j++) {
                    final Cell neighborCell = cells.get(j);
                    if (i != j && neighborCell.isNotFilled()) {
                        differentCandidateSet = Utils.difference(differentCandidateSet, neighborCell.getCandidateSet());
                    }
                }

                if (Utils.size(differentCandidateSet) == 1) {
                    board.fill(cell.getR(), cell.getC(), Utils.getNumber(differentCandidateSet));
                    return true;
                }
            }
        }

        return false;
    }
}
