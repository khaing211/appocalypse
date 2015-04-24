package com.github.appocalypse.sudokusolver;

public interface NakedSolveStrategy {

    static boolean eliminateCandidateFromRow(final SudokuBoard board, final int candidateSet, final int r,
                                             final Cell ... cells) {
        boolean hasUpdate = false;
        for (int i = 0; i < 9; i++) {
            final Cell cell = board.getCell(r, i);
            if (cell.isNotFilled() && Utils.isNot(cell, cells)) {
                hasUpdate = hasUpdate || board.unsetCandidate(r, i, candidateSet);
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
                hasUpdate = hasUpdate || board.unsetCandidate(i, c, candidateSet);
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
                    hasUpdate = hasUpdate || board.unsetCandidate(r, c, candidateSet);
                }
            }
        }

        return hasUpdate;
    }
}
