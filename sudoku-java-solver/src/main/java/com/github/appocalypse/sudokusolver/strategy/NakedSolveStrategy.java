package com.github.appocalypse.sudokusolver.strategy;

import java.util.List;

import com.github.appocalypse.sudokusolver.Cell;
import com.github.appocalypse.sudokusolver.SudokuBoard;
import com.github.appocalypse.sudokusolver.Unit;
import com.github.appocalypse.sudokusolver.Utils;
import com.google.common.collect.ImmutableList;

public interface NakedSolveStrategy {

    /**
     * @param chooseCells
     * @return true if either cells has naked marked
     */
    static boolean anyHasNakedMark(final Cell[] chooseCells) {
        for (int i = 0; i < chooseCells.length; i++) {
            switch (chooseCells.length) {
                case 2: if (chooseCells[i].hasNakedPair()) {
                  return true;
                } break;
                case 3: if (chooseCells[i].hasNakedTriple()) {
                  return true;
                } break;
                case 4: if (chooseCells[i].hasNakedQuad()) {
                  return true;
                } break;
            }
        }
        return false;
    }

    /**
     * @return true if all chooseCells  has one of the sizes
     */
    static boolean hasSize(final Cell[] chooseCells, final int[] sizes) {
        for (int i = 0; i < chooseCells.length; i++) {
            if (Utils.isNotIn(sizes, chooseCells[i].getCandidateSetSize())) {
              return false;
            }
        }
        return true;
    }

    static boolean anyIsFilled(final Cell[] chooseCells) {
        for (int i = 0; i < chooseCells.length; i++) {
            if (chooseCells[i].isFilled()) {
              return true;
            }
        }
        return false;
    }

    static boolean update(final SudokuBoard board, final List<int[]> chooseSets, final int[] sizes, final int combineSize) {
        final ImmutableList<Unit> units = board.getAllUnits();

        for (final Unit unit : units) {

            final ImmutableList<Cell> cells = unit.cells();

            for (final int[] set : chooseSets) {

                boolean hasUpdate = false;

                int combineCandidateSet = 0;
                final Cell[] chooseCells = new Cell[set.length];
                for (int i = 0; i < set.length; i++) {
                    chooseCells[i] = cells.get(set[i]);
                    combineCandidateSet |= chooseCells[i].getCandidateSet();
                }

                final Cell c0 = chooseCells[0];

                if (!anyHasNakedMark(chooseCells) &&
                    !anyIsFilled(chooseCells) &&
                    hasSize(chooseCells, sizes) &&
                    Utils.size(combineCandidateSet) == combineSize) {

                    board.markNaked(chooseCells);


                    if (Utils.isInSameRow(chooseCells)) {
                        hasUpdate = eliminateCandidateFromRow(board,
                                c0.getCandidateSet(), c0.getR(), chooseCells) || hasUpdate;
                    }

                    if (Utils.isInSameCol(chooseCells)) {
                        hasUpdate = eliminateCandidateFromCol(board,
                                c0.getCandidateSet(), c0.getC(), chooseCells) || hasUpdate;
                    }

                    if (Utils.isInSameBox(chooseCells)) {
                        hasUpdate = eliminateCandidateFromBox(board,
                                c0.getCandidateSet(), c0.getBaseR(), c0.getBaseC(), chooseCells) || hasUpdate;
                    }

                    if (hasUpdate) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    static boolean eliminateCandidateFromRow(final SudokuBoard board, final int candidateSet, final int r,
                                             final Cell ... cells) {
        boolean hasUpdate = false;
        for (int i = 0; i < 9; i++) {
            final Cell cell = board.getCell(r, i);
            if (cell.isNotFilled() && Utils.isNot(cell, cells)) {
                hasUpdate = board.unsetCandidate(r, i, candidateSet) || hasUpdate;
            }
        }

        return hasUpdate;
    }

    static boolean eliminateCandidateFromCol(final SudokuBoard board, final int candidateSet, final int c,
                                             final Cell ... cells) {
        boolean hasUpdate = false;
        for (int i = 0; i < 9; i++) {
            final Cell cell = board.getCell(i, c);
            if (cell.isNotFilled() && Utils.isNot(cell, cells)) {
                hasUpdate = board.unsetCandidate(i, c, candidateSet) || hasUpdate;
            }
        }

        return hasUpdate;
    }

    static boolean eliminateCandidateFromBox(final SudokuBoard board, final int candidateSet,
                                             final int baseR, final int baseC, final Cell ... cells) {
        boolean hasUpdate = false;
        for (int smallR = 0; smallR < 3; smallR++) {
            for (int smallC = 0; smallC < 3; smallC++) {
                final int r = baseR + smallR;
                final int c = baseC + smallC;
                final Cell cell = board.getCell(r, c);
                if (cell.isNotFilled() && Utils.isNot(cell, cells)) {
                    hasUpdate = board.unsetCandidate(r, c, candidateSet) || hasUpdate;
                }
            }
        }

        return hasUpdate;
    }
}
