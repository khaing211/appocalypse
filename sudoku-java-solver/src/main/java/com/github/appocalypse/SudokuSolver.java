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
                board.printUnfilledCellPossible();
                break;
            }

            // easy case: eliminate cell with 1 possible
            final int[] possibles = board.getPossibles(r, c);
            if (count == 1) {
                board.setNumber(r, c, possibles[0]);
                continue;
            }

            // medium case: eliminate cell by set of 1 possible among cells within big cell
            final int[] result = board.getBigCellHeuristic();
            if (result[0] != -1 && result[1] != -1 && result[2] != 0) {
                board.setNumber(result[0], result[1], result[2]);
                continue;
            }

            System.out.println("Impossible to solve, potential candidate lists");
            board.printUnfilledCellPossible();
            break;
        }

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
        //solve(initialize(new Scanner(ClassLoader.getSystemResourceAsStream("easy_0.txt"))));
        //solve(initialize(new Scanner(ClassLoader.getSystemResourceAsStream("medium_0.txt"))));
        solve(initialize(new Scanner(ClassLoader.getSystemResourceAsStream("hard_0.txt"))));

        //solve(initialize(new Scanner(System.in)));
    }
}
