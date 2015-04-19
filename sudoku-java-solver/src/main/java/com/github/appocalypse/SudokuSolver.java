package com.github.appocalypse;

import com.github.appocalypse.sudokusolver.*;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;

public class SudokuSolver {

    public static SudokuSolveStrategy[] getStrategies() {
        final SudokuSolveStrategy[] strategies = new SudokuSolveStrategy[5];
        strategies[0] = new NakedSingleSolveStrategy();
        strategies[1] = new HiddenSingleSolveStragety();
        strategies[2] = new NakedPairSolveStrategy();
        strategies[3] = new NakedTripleSolveStrategy();
        strategies[4] = new HiddenPairSolveStrategy();
        return strategies;
    }

    /**
     * @param board to be solve
     * @return true if the board has been solved. false otherwise
     */
    public static boolean solve(final SudokuBoard board) {
        final SudokuSolveStrategy[] strategies = getStrategies();

        int curNumUnsolvedCell, postSolveNumUnsolvedCell;

        while ((curNumUnsolvedCell = board.getNumUnsolvedCell()) != 0) {
            boolean hasUpdated = false;
            for (SudokuSolveStrategy strategy : strategies) {
                hasUpdated = strategy.update(board);
                if (hasUpdated) {
                    break;
                }
            }

            if (!hasUpdated) {
                return false;
            }
        }

        return true;
    }

    /**
     * @param scanner to initalize the board
     * @return SudokuBoard from scanner input
     */
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

    /**
     * Print out the result and diagnose information when board is not solved.
     */
    public static void mainSolve(SudokuBoard board) {
        System.out.println("Initial board");
        board.printBoard();

        if (!solve(board)) {
            System.out.println("Impossible to solve, potential candidate lists");
            board.printUnfilledCellPossible();
        }

        System.out.println("Final board");
        board.printBoard();
    }

    public static void main(String[] args) {
        //mainSolve(initialize(new Scanner(ClassLoader.getSystemResourceAsStream("easy_0.txt"))));
        //mainSolve(initialize(new Scanner(ClassLoader.getSystemResourceAsStream("medium_0.txt"))));
        mainSolve(initialize(new Scanner(ClassLoader.getSystemResourceAsStream("hard_0.txt"))));

        //mainSolve(initialize(new Scanner(System.in)));
    }
}
