package com.github.appocalypse.sudokusolver;

import java.util.Arrays;
import java.util.stream.IntStream;

public class SudokuBoard {
    private static final short ALL = (1<<9)-1;
    private static final int MAX_NUM_UNSOLVED_CELL = 81;

    private final short[][] possible = new short[9][9];
    private final int[][] board = new int[9][9];

    private int numUnsolvedCell;

    // copy constructor for snapshot of the SudokuBoard
    public SudokuBoard(SudokuBoard other) {
        Arrays.setAll(possible, r -> possible[r]);

        Arrays.setAll(board, r -> other.board[r]);

        this.numUnsolvedCell = other.numUnsolvedCell;
    }

    public SudokuBoard() {
        this.numUnsolvedCell = MAX_NUM_UNSOLVED_CELL;
        Arrays.setAll(possible, r -> {
            Arrays.fill(possible[r], ALL);
            return possible[r];
        });
    }

    public int getNumUnsolvedCell() {
        return numUnsolvedCell;
    }

    public void setNumber( int r, int c, int n) {
        Utils.isValidIndex(r, c);
        Utils.isValidNumber(n);

        if (board[r][c] == 0 && isPossible(r,c,n)) {
            board[r][c] = n;
            update(r, c, n);
            numUnsolvedCell--;
        } else if (board[r][c] == n) {
            // ignore
        } else {
            throw new IllegalArgumentException("impossible n value " + n);
        }
    }

    public boolean isPossible(int r, int c, int n) {
        Utils.isValidIndex(r, c);
        Utils.isValidNumber(n);
        final short mask = (short)(1<<(n-1));
        return (possible[r][c] & mask) == mask;
    }

    public boolean isFilled(int r, int c) {
        Utils.isValidIndex(r, c);
        return board[r][c] != 0;
    }

    private void update(final int r, final int c, final int n) {
        for (int i = 0; i < 9; i++) {
            if (i != c) {
                unsetPossible(r, i, n);
            }

            if (i != r) {
                unsetPossible(i, c, n);
            }

            if (i != n-1) {
                unsetPossible(r, c, i+1);
            }
        }

        final int baseR = (r / 3) * 3;
        final int baseC = (c / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final int curR = baseR + i;
                final int curC = baseC + j;
                if (!(curR == r && curC == c)) {
                    unsetPossible(curR, curC, n);
                }
            }
        }
    }

    private void unsetPossible(int r, int c, int n) {
        final short mask = (short)((~(1<<(n-1))) & ALL);
        possible[r][c] &= mask;
    }

    public short getPossibleMask(int r, int c) {
        Utils.isValidIndex(r, c);
        return possible[r][c];
    }

    public int[] getPossibles(int r, int c) {
        Utils.isValidIndex(r, c);
        final int[] ret = new int[countPossibles(r,c)];
        int j = 0;
        for (int i = 1; i <= 9; i++) {
            if (isPossible(r, c, i)) {
                ret[j++] = i;
            }
        }
        return ret;
    }

    public int countPossibles(int r, int c) {
        Utils.isValidIndex(r, c);
        return Utils.popcount16(possible[r][c]);
    }

    public void printPossible() {
        IntStream.range(0, 9)
                .forEachOrdered(r -> IntStream.range(0, 9)
                        .forEachOrdered(c -> printPossible(r, c)));
    }

    public void printPossible(int r, int c) {
        Utils.isValidIndex(r, c);
        System.out.println("[" + r + "," + c + "," + possible[r][c] + "]");
    }

    public void printNumberPossible() {
        IntStream.range(0, 9)
                .forEachOrdered(r -> IntStream.range(0, 9)
                        .forEachOrdered(c -> printNumberPossible(r, c)));
    }

    public void printUnfilledCellPossible() {
        IntStream.range(0, 9)
                .forEachOrdered(r -> IntStream.range(0, 9)
                        .forEachOrdered(c -> {
                            if (!isFilled(r, c)) {
                                printNumberPossible(r, c);
                            }
                        }));
    }

    public void printNumberPossible(int r, int c) {
        Utils.isValidIndex(r, c);
        System.out.println("[" + r + "," + c + "]" + Arrays.toString(getPossibles(r, c)));
    }

    public void printBoard() {
        IntStream.range(0, 9)
                .forEachOrdered(r -> System.out.println(Arrays.toString(board[r])));
    }
}
