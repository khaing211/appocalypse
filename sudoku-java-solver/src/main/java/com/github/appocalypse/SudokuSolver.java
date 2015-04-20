package com.github.appocalypse;

import com.github.appocalypse.sudokusolver.*;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class SudokuSolver {
    private final SudokuBoard board;
    private final SudokuSolveStrategy[] strategies;

    public SudokuSolver(final SudokuBoard board) {
        this.board = board;
        this.strategies = getStrategies();
    }

    public static SudokuSolveStrategy[] getStrategies() {
        final SudokuSolveStrategy[] strategies = new SudokuSolveStrategy[6];
        strategies[0] = new NakedSingleSolveStrategy();
        strategies[1] = new HiddenSingleSolveStragety();
        strategies[2] = new NakedPairSolveStrategy();
        strategies[3] = new NakedTripleSolveStrategy();
        strategies[4] = new HiddenPairSolveStrategy();
        strategies[5] = new HiddenTripleSolveStrategy();
        return strategies;
    }

    /**
     * @return true if the board has been solved. false otherwise
     */
    public boolean solve() {
        while (board.getNumUnsolvedCell() != 0) {
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

    public SudokuBoard getBoard() {
        return board;
    }

    /**
     * @param scanner to initalize the board
     * @return SudokuBoard from scanner input
     */
    public static SudokuSolver initialize(final Scanner scanner) {
        final SudokuBoard board = new SudokuBoard();

        for (int i = 0; i < 9; i++) {
            try {
                final String row = scanner.nextLine();
                for (int j = 0; j < 9 && j < row.length(); j++) {
                    final char ch = row.charAt(j);
                    if (Character.isDigit(ch)) {
                        final int num = ch - '0';
                        board.fill(i, j, num);
                    }
                }
            } catch (NoSuchElementException e) {
                break;
            }
        }

        return new SudokuSolver(board);
    }



    /**
     * Print out the result and diagnose information when board is not solved.
     */
    public static void mainSolve(SudokuSolver solver) {
        System.out.println("Initial board");
        solver.getBoard().printBoard();

        if (!solver.solve()) {
            System.out.println("Impossible to solve, potential candidate lists");
            solver.getBoard().printUnfilledCellPossible();
        }

        System.out.println("Final board");
        solver.getBoard().printBoard();
    }

    public static void main(String[] args) {
        mainSolve(initialize(new Scanner(ClassLoader.getSystemResourceAsStream("hard_0.txt"))));

        //mainSolve(initialize(new Scanner(System.in)));
    }
}
