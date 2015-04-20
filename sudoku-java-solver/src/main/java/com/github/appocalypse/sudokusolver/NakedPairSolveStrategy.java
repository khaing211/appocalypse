package com.github.appocalypse.sudokusolver;

import java.util.Arrays;
import java.util.Comparator;

public class NakedPairSolveStrategy implements SudokuSolveStrategy {
    @Override
    public boolean update(SudokuBoard board) {
        System.out.println("NakedPairSolveStragety - initial board");
        //board.printBoard();

        final NakedPairCell[] nakedPairCells = new NakedPairCell[Utils.MAX_NUM_UNSOLVED_CELL];
        int numNakedPair = 0;
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board.getPossibleSetSize(r, c) == 2) {
                    nakedPairCells[numNakedPair++] = new NakedPairCell(r, c, board.getPossibleSet(r, c));
                }
            }
        }

        // sorted by possible set
        Arrays.sort(nakedPairCells, 0, numNakedPair, Comparator.comparingInt(NakedPairCell::getPossible));

        int j = 0;
        while (j < numNakedPair) {
            final int startIndex = j;
            while (j < numNakedPair && nakedPairCells[startIndex].getPossible() == nakedPairCells[j].getPossible()) {
                j++;
            }

            // exclusive endIndex
            final int endIndex = j;

            boolean hasUpdate = false;

            // if the possible set is same
            for (int i = startIndex; i < endIndex; i++) {
                for (int k = i+1; k < endIndex; k++) {

                    // if pair are in same row,
                    if (nakedPairCells[i].getR() == nakedPairCells[k].getR()) {
                        hasUpdate = hasUpdate || eliminatePossibleFromRow(board, nakedPairCells[i], nakedPairCells[k]);
                    }

                    // if pair are in same column
                    if (nakedPairCells[i].getC() == nakedPairCells[k].getC()) {
                        hasUpdate = hasUpdate || eliminatePossibleFromColumn(board, nakedPairCells[i], nakedPairCells[k]);
                    }

                    // if pair in same box
                    if (Utils.isInSameBox(nakedPairCells[i].getR(), nakedPairCells[i].getC(),
                                          nakedPairCells[k].getR(), nakedPairCells[k].getC())) {
                        hasUpdate = hasUpdate || eliminatePossibleFromBox(board, nakedPairCells[i], nakedPairCells[k]);
                    }
                }
            }

            if (hasUpdate) {
                return true;
            }
        }

        //board.printUnfilledCellPossible();
        return false;
    }

    private boolean eliminatePossibleFromRow(SudokuBoard board, NakedPairCell np1, NakedPairCell np2) {
        System.out.println("eliminatePossibleFromRow np1: " + np1 + " np2: " + np2);

        boolean hasUpdate = false;
        for (int i = 0; i < 9; i++) {
            if (!board.isFilled(np1.getR(), i) && isNeitherNakedPairCell(np1.getR(), i, np1, np2)) {
                hasUpdate = hasUpdate || board.unsetPossible(np1.getR(), i, np1.getPossible());
            }
        }

        return hasUpdate;
    }

    private boolean eliminatePossibleFromColumn(SudokuBoard board, NakedPairCell np1, NakedPairCell np2) {
        System.out.println("eliminatePossibleFromColumn np1: " + np1 + " np2: " + np2);

        boolean hasUpdate = false;
        for (int i = 0; i < 9; i++) {
            if (!board.isFilled(i, np1.getC()) && isNeitherNakedPairCell(i, np1.getC(), np1, np2)) {
                hasUpdate = hasUpdate || board.unsetPossible(i, np1.getC(), np1.getPossible());
            }
        }

        return hasUpdate;
    }

    private boolean eliminatePossibleFromBox(SudokuBoard board, NakedPairCell np1, NakedPairCell np2) {
        System.out.println("eliminatePossibleFromBox np1: " + np1 + " np2: " + np2);

        boolean hasUpdate = false;
        int bigR = np1.getR() / 3;
        int bigC = np1.getC() / 3;
        for (int smallR = 0; smallR < 3; smallR++) {
            for (int smallC = 0; smallC < 3; smallC++) {
                final int r = bigR * 3 + smallR;
                final int c = bigC * 3 + smallC;
                if (!board.isFilled(r, c) && isNeitherNakedPairCell(r, c, np1, np2)) {
                    hasUpdate = hasUpdate || board.unsetPossible(r, c, np1.getPossible());
                }
            }
        }

        return hasUpdate;
    }

    private static boolean isNeitherNakedPairCell(int r, int c, NakedPairCell np1, NakedPairCell np2) {
        return !((r == np1.getR() && c == np1.getC()) ||  (r == np2.getR() && c == np2.getC()));
    }

    private static class NakedPairCell {
        private final int r;
        private final int c;
        private final int possible;


        public NakedPairCell(int r, int c, int possible) {
            this.r = r;
            this.c = c;
            this.possible = possible;
        }


        public int getR() {
            return r;
        }

        public int getC() {
            return c;
        }

        public int getPossible() {
            return possible;
        }

        @Override
        public String toString() {
            return "NakedPairCell{" +
                    "r=" + r +
                    ", c=" + c +
                    ", possible=" + possible +
                    '}';
        }
    }
}
