package com.github.appocalypse.sudokusolver;

import java.util.Optional;

/**
 * HiddenSingle is to eliminate cells that has a possible candidate X that can't go anywhere else
 * in either row, column or box i.e. it must go here in the cell
 */
public class HiddenSingleSolveStragety implements SudokuSolveStrategy {

    @Override
    public Optional<SudokuStrategyResult> solve(SudokuBoard board) {
        // for each big cell
        for (int bigR = 0; bigR < 3; bigR++) {
            for (int bigC = 0; bigC < 3; bigC++) {

                // for each small cell in big cell
                for (int smallR = 0; smallR < 3; smallR++) {
                    for (int smallC = 0; smallC < 3; smallC++) {

                        final int r = bigR * 3 + smallR;
                        final int c = bigC * 3 + smallC;

                        if (!board.isFilled(r, c)) {
                            short currentDifference = board.getPossibleMask(r, c);
                            currentDifference = checkInBox(board, r, c, currentDifference, bigR, bigC);

                            // TODO: check row and column

                            if (currentDifference != 0) {
                                final int count = Utils.popcount16(currentDifference);
                                if (count == 1) {
                                    return Optional.of(new SudokuStrategyResult(r, c, Integer.numberOfTrailingZeros(currentDifference) + 1));
                                }
                            }
                        }
                    }
                }
            }
        }

        return Optional.empty();
    }

    private short checkInBox(SudokuBoard board, int r, int c, short currentDifference, int bigR, int bigC) {
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
}
