package com.github.appocalypse.sudokusolver;

import java.util.Optional;

/**
 * HiddenSingle is to eliminate cells that has a possible candidate X that can't go anywhere else
 * in (exclusive) either row, column or box i.e. it must go here in the cell
 */
public class HiddenSingleSolveStragety implements SudokuSolveStrategy {

    @Override
    public boolean update(SudokuBoard board) {
        // for each big cell
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {

                if (!board.isFilled(r, c)) {
                    final int boxDifference = checkInBox(board, r, c);

                    // hidden single in box
                    if (Utils.popcount16(boxDifference) == 1) {
                        board.setNumber(r, c, Utils.getNumber(boxDifference));
                        return true;
                    }

                    // hidden single in row
                    final int rowDifference = checkInRow(board, r, c);
                    if (Utils.popcount16(rowDifference) == 1) {
                        board.setNumber(r, c, Utils.getNumber(rowDifference));
                        return true;
                    }

                    // hidden single in column
                    final int colDifference = checkInColumn(board, r, c);
                    if (Utils.popcount16(colDifference) == 1) {
                        board.setNumber(r, c, Utils.getNumber(colDifference));
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private int checkInBox(SudokuBoard board, int r, int c) {
        int currentDifference = board.getPossibleMask(r, c);
        final int bigR = r / 3;
        final int bigC = c / 3;

        // compute against other cells in box
        for (int otherR = 0; otherR < 3; otherR++) {
            for (int otherC = 0; otherC < 3; otherC++) {

                final int neighborR = bigR * 3 + otherR;
                final int neighborC = bigC * 3 + otherC;

                if (!(neighborR == r && neighborC == c) && !board.isFilled(neighborR, neighborC)) {
                    currentDifference = Utils.difference(currentDifference, board.getPossibleMask(neighborR, neighborC));
                }

                if (currentDifference == 0) {
                    return currentDifference;
                }
            }
        }

        return currentDifference;
    }

    private int checkInRow(SudokuBoard board, int r, int c) {
        int currentDifference = board.getPossibleMask(r, c);

        for (int i = 0; i < 9; i++) {
            // check in row
            if (i != c && !board.isFilled(r, i)) {
                currentDifference = Utils.difference(currentDifference, board.getPossibleMask(r, i));
            }
        }

        return currentDifference;
    }

    private int checkInColumn(SudokuBoard board, int r, int c) {
        int currentDifference = board.getPossibleMask(r, c);

        for (int i = 0; i < 9; i++) {
            // check in column
            if (i != r && !board.isFilled(i, c)) {
                currentDifference = Utils.difference(currentDifference, board.getPossibleMask(i, c));
            }
        }

        return currentDifference;
    }
}
