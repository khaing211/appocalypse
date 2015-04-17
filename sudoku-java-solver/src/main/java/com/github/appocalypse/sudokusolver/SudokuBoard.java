package com.github.appocalypse.sudokusolver;

import java.util.Arrays;
import java.util.stream.IntStream;

public class SudokuBoard {
    final private static short ALL = (1<<9)-1;

    final private short[][] possible = new short[9][9];
    final private int[][] board = new int[9][9];

    // copy constructor for snapshot of the SudokuBoard
    public SudokuBoard(SudokuBoard other) {
        Arrays.setAll(possible, r -> {
            return possible[r];
        });

        Arrays.setAll(board, r -> {
            return other.board[r];
        });
    }

    public SudokuBoard() {
        Arrays.setAll(possible, r -> {
            Arrays.fill(possible[r], ALL);
            return possible[r];
        });
    }

    public void setNumber( int r, int c, int n) {
        isValidIndex(r, c);
        isValidNumber(n);

        if (board[r][c] == 0 && isPossible(r,c,n)) {
            board[r][c] = n;
            update(r, c, n);
        } else if (board[r][c] == n) {
            // ignore
        } else {
            throw new IllegalArgumentException("impossible n value " + n);
        }
    }

    public boolean isPossible(int r, int c, int n) {
        isValidIndex(r, c);
        isValidNumber(n);
        final short mask = (short)(1<<(n-1));
        return (possible[r][c] & mask) == mask;
    }

    public boolean isFilled(int r, int c) {
        isValidIndex(r, c);
        return board[r][c] != 0;
    }

    public int[] getLeastPossibleUnfilledCell() {
        final int[] ret = new int[3];
        ret[0] = -1;
        ret[1] = -1;
        ret[2] = 9;

        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (!isFilled(r, c)) {
                    final int curPossibleCount = countPossibles(r, c);
                    if (ret[2] > curPossibleCount) {
                        ret[0] = r;
                        ret[1] = c;
                        ret[2] = curPossibleCount;
                    }
                }
            }
        }

        return ret;
    }

    public int[] getBigCellHeuristic() {
        final int[] ret = new int[3];
        ret[0] = -1;
        ret[1] = -1;
        ret[2] = 0;

        // for each big cell
        for (int bigR = 0; bigR < 3; bigR++) {
            for (int bigC = 0; bigC < 3; bigC++) {

                // for each small cell in big cell
                for (int smallR = 0; smallR < 3; smallR++) {
                    for (int smallC = 0; smallC < 3; smallC++) {

                        final int r = bigR * 3 + smallR;
                        final int c = bigC * 3 + smallC;

                        if (!isFilled(r, c)) {
                            short currentDifference = possible[r][c];

                            // compute against other small cell in big cell
                            for (int otherR = 0; otherR < 3; otherR++) {
                                for (int otherC = 0; otherC < 3; otherC++) {

                                    final int neighborR = bigR * 3 + otherR;
                                    final int neighborC = bigC * 3 + otherC;

                                    if (!(neighborR == r && neighborC == c) && !isFilled(neighborR, neighborC)) {
                                        currentDifference = difference(currentDifference, possible[neighborR][neighborC]);
                                    }

                                    if (currentDifference == 0) {
                                        break;
                                    }
                                }

                                if (currentDifference == 0) {
                                    break;
                                }
                            }

                            if (currentDifference != 0) {
                                final int count = popcount16(currentDifference);
                                if (count == 1) {
                                    ret[0] = r;
                                    ret[1] = c;
                                    ret[2] = Integer.numberOfTrailingZeros(currentDifference) + 1;
                                    return ret;
                                }
                            }
                        }
                    }
                }
            }
        }

        return ret;
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

    private void isValidIndex(int r, int c) {
        if (0 > r || r >= 9) {
            throw new IllegalArgumentException("invalid r value " + r);
        }

        if (0 > c || c >= 9) {
            throw new IllegalArgumentException("invalid c value " + c);
        }
    }

    private void isValidNumber(int n) {
        if (n <= 0 || n > 9) {
            throw new IllegalArgumentException("n value cannot be less than or equal 0 or greater than 9");
        }
    }

    public int[] getPossibles(int r, int c) {
        isValidIndex(r, c);
        final int[] ret = new int[countPossibles(r,c)];
        int j = 0;
        for (int i = 1; i <= 9; i++) {
            if (isPossible(r, c, i)) {
                ret[j++] = i;
            }
        }
        return ret;
    }

    private int countPossibles(int r, int c) {
        return popcount16(possible[r][c]);
    }

    public void printPossible() {
        IntStream.range(0, 9)
                .forEachOrdered(r -> IntStream.range(0, 9)
                        .forEachOrdered(c -> printPossible(r, c)));
    }

    public void printPossible(int r, int c) {
        isValidIndex(r, c);
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
        isValidIndex(r, c);
        System.out.println("[" + r + "," + c + "]" + Arrays.toString(getPossibles(r, c)));
    }

    public void printBoard() {
        IntStream.range(0, 9)
                .forEachOrdered(r -> System.out.println(Arrays.toString(board[r])));
    }

    /**
     * Calculate number of bits in the vector
     *
     * http://en.wikipedia.org/wiki/Hamming_weight
     */
    public static int popcount16(int bits) {
        final int m1  = 0x5555; //binary: 0101...
        final int m2  = 0x3333; //binary: 00110011..
        final int m4  = 0x0f0f; //binary:  4 zeros,  4 ones ...
        final int m8  = 0x00ff; //binary:  8 zeros,  8 ones ..
        bits = (bits & m1 ) + ((bits >>  1) & m1 ); //put count of each  2 bits into those  2 bits
        bits = (bits & m2 ) + ((bits >>  2) & m2 ); //put count of each  4 bits into those  4 bits
        bits = (bits & m4 ) + ((bits >>  4) & m4 ); //put count of each  8 bits into those  8 bits
        bits = (bits & m8 ) + ((bits >>  8) & m8 ); //put count of each 16 bits into those 16 bits
        return bits;
    }

    /**
     * Calculate difference bit set a - b
     *
     * @param a
     * @param b
     *
     * @return 0 if everything is same,
     *         a if a does not have any common with b
     *         a - b if a does have common with b
     */
    public static short difference(short a, short b) {
        return (short)((a ^ b) & a);
    }
}
