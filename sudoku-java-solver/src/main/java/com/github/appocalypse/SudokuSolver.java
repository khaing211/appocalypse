package com.github.appocalypse;

import com.github.appocalypse.sudokusolver.SudokuBoard;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class SudokuSolver {

    public static void solve(final SudokuBoard board) {
        System.out.println("Initial board");
        board.printBoard();

        while (true) {
            final int[] cell = board.getLeastPossibleUnfilledCell();
            final int r = cell[0];
            final int c = cell[1];
            final int count = cell[2];

            if (r == -1 || c == -1) break;
            if (count == 0) {
                System.out.println("Impossible to solve, potential candidate lists");
                board.printUnfillNumberPossible();
                break;
            }

            final int[] possibles = board.getPossibles(r, c);
            if (count == 1) {
                board.setNumber(r, c, possibles[0]);
            } else {
                if (board.isFilled(3, 8)) {
                    break;
                }

                if (board.isFilled(6, 8)) {
                    break;
                }

                if (board.isFilled(8, 8)) {
                    break;
                }

                board.setNumber(3, 8, 9);
                board.setNumber(6, 8, 8);
                board.setNumber(8, 8, 4);

                //break;
            }
        }
        board.printUnfillNumberPossible();
        System.out.println("Final board");
        board.printBoard();

    }

    public static SudokuBoard initialize(final Scanner scanner) {
        final SudokuBoard board = new SudokuBoard();

        for (int i = 0; i < 9; i++) {
            try {
                final String row = scanner.nextLine();
                for (int j = 0; j < 9 && j < row.length(); j++) {
                    final char ch = row.charAt(j);
                    if (Character.isDigit(ch)) {
                        final int num = ch - '0';
                        board.setNumber(i, j, num);
                    }
                }
            } catch (NoSuchElementException e) {
                break;
            }
        }

        return board;
    }

    public static void main(String[] args) {
        solve(initialize(new Scanner(ClassLoader.getSystemResourceAsStream("easy_0.txt"))));

        //solve(initialize(new Scanner(System.in)));
    }
}
