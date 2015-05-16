package com.github.appocalypse;

import com.github.appocalypse.sudokusolver.*;
import com.google.common.collect.ImmutableList;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class SudokuSolver {
    private final SudokuBoard board;
    private final SudokuSolveStrategy[] strategies;

    public SudokuSolver(final SudokuBoard board) {
        this(board, getStrategies());
    }

    public SudokuSolver(final SudokuBoard board, SudokuSolveStrategy[] strategies) {
        this.board = board;
        this.strategies = strategies;
    }

    public static SudokuSolveStrategy[] getStrategies() {
        final SudokuSolveStrategy[] strategies = new SudokuSolveStrategy[10];
        strategies[0] = new NakedSingleSolveStrategy();
        strategies[1] = new HiddenSingleSolveStrategy();
        strategies[2] = new NakedPairSolveStrategy();
        strategies[3] = new NakedTripleSolveStrategy();
        strategies[4] = new HiddenPairSolveStrategy();
        strategies[5] = new HiddenTripleSolveStrategy();
        strategies[6] = new NakedQuadSolveStrategy();
        strategies[7] = new HiddenQuadSolveStrategy();
        strategies[8] = new PointingPairOrTripleSolveStrategy();
        strategies[9] = new BoxLineReductionSolveStrategy();
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

    public boolean validateBoard() {
        final ImmutableList<Unit> units = board.getAllUnits();
        final boolean[] check = new boolean[9];

        for (final Unit unit : units) {
            Arrays.fill(check, false);
            final ImmutableList<Cell> cells = unit.cells();
            if (cells.size() != 9) return false;

            for (int i = 0; i < 9; i++) {
                final Cell cell = cells.get(i);
                if (cell.isNotFilled()) return false;
                check[cell.getN()-1] = true;
            }

            if (!Utils.isAllTrue(check)) return false;
        }

        return true;
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
                        if (num != 0) {
                            board.fill(i, j, num);
                        }
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
        final SudokuBoard initialBoard = new SudokuBoard(solver.getBoard());

        try {
            if (!solver.solve()) {
                System.out.println("Unable to solve, final board is partial result");
            } else {
                System.out.println("Solve and validation result: " + solver.validateBoard());
            }
        } finally {
            System.out.println(initialBoard.sideBySide(solver.getBoard()));
        }
    }

    public static void main(String[] args) {
        mainSolve(initialize(new Scanner(System.in)));
    }
}
