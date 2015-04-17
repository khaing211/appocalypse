package com.github.appocalypse.sudokusolver;

import java.util.Arrays;
import java.util.stream.IntStream;

public class SudokuBoard {
    final private boolean[][][] possible = new boolean[9][9][9];
    final private int[][] board = new int[9][9];

    // copy constructor for snapshot of the SudokuBoard
    public SudokuBoard(SudokuBoard other) {
        Arrays.setAll(possible, r -> {
            Arrays.setAll(possible[r], c -> {
                return other.possible[r][c];
            });
            return possible[r];
        });

        Arrays.setAll(board, r -> {
            return other.board[r];
        });
    }

    public SudokuBoard() {
        Arrays.setAll(possible, r -> {
            Arrays.setAll(possible[r], c -> {
                Arrays.fill(possible[r][c], true);
                return possible[r][c];
            });
            return possible[r];
        });
    }

    public void setNumber( int r, int c, int n) {
        isValidIndex(r, c);
        isValidNumber(n);

        if (board[r][c] == 0 && possible[r][c][n-1]) {
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
        return possible[r][c][n-1];
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

    private void update(final int r, final int c, final int n) {
        for (int i = 0; i < 9; i++) {
            if (i != c) {
                possible[r][i][n-1] = false;
            }

            if (i != r) {
                possible[i][c][n-1] = false;
            }

            if (i != n-1) {
                possible[r][c][i] = false;
            }
        }

        final int baseR = (r / 3) * 3;
        final int baseC = (c / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final int curR = baseR + i;
                final int curC = baseC + j;
                if (curR != r && curC != c) {
                    possible[curR][curC][n-1] = false;
                }
            }
        }
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
        for (int i = 0; i < 9; i++) {
            if (possible[r][c][i]) {
                ret[j++] = i+1;
            }
        }
        return ret;
    }

    private int countPossibles(int r, int c) {
        int count = 0;
        for (int i = 0; i < 9; i++) {
            count += possible[r][c][i] ? 1: 0;
        }
        return count;
    }

    public void printPossible() {
        IntStream.range(0, 9)
                .forEachOrdered(r -> IntStream.range(0, 9)
                        .forEachOrdered(c -> printPossible(r, c)));
    }

    public void printPossible(int r, int c) {
        isValidIndex(r, c);
        System.out.println("[" + r + "," + c + "]" + Arrays.toString(possible[r][c]));
    }

    public void printNumberPossible() {
        IntStream.range(0, 9)
                .forEachOrdered(r -> IntStream.range(0, 9)
                        .forEachOrdered(c -> printNumberPossible(r, c)));
    }

    public void printUnfillNumberPossible() {
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
}
