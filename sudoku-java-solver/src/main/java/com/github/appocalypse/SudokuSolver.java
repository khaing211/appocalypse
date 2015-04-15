package com.github.appocalypse;

import com.github.appocalypse.sudokusolver.SudokuBoard;

import java.util.Arrays;
import java.util.Scanner;

public class SudokuSolver {

    public static void solve(final Scanner scanner) {
        final SudokuBoard board = new SudokuBoard();

        for (int i = 0; i < 9; i++) {
            final String row = scanner.nextLine();
            for (int j = 0; j < 0; j++) {
                final char ch = row.charAt(j);
                if (Character.isDigit(ch)) {
                    final int num = ch - '0';
                    board.setNumber(i,j,1);
                }
            }
        }

        board.printBoard();
        board.printNumberPossible();

        System.out.println(Arrays.toString(board.getLeastPossibleUnsolvedCell()));
    }

    public static void main(String[] args) {
        solve(new Scanner(System.in));
    }
}
