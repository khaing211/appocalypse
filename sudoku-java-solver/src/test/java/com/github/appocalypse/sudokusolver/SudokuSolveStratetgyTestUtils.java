package com.github.appocalypse.sudokusolver;

import com.github.appocalypse.SudokuSolver;

import java.util.Scanner;

import static org.junit.Assert.assertTrue;

public interface SudokuSolveStratetgyTestUtils {
    static SudokuBoard getBoard(String name) {
        return SudokuSolver.initialize(new Scanner(ClassLoader.getSystemResourceAsStream(name))).getBoard();
    }

    static void assertUpdate(String name, SudokuSolveStrategy strategy) {
        assertUpdate(name, strategy, false);
    }

    static void assertUpdate(String name, SudokuSolveStrategy strategy, boolean debug) {
        final SudokuBoard board = SudokuSolveStratetgyTestUtils.getBoard(name);
        final SudokuBoard testBoard = new SudokuBoard(board);
        final boolean hasUpdate = strategy.update(testBoard);
        assertTrue(name + "\n" + board.sideBySide(testBoard), hasUpdate);

        if (debug && hasUpdate) {
            System.out.println(board.sideBySide(testBoard));
        }
    }
}
