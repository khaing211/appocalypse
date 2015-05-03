package com.github.appocalypse.sudokusolver;

import com.github.appocalypse.SudokuSolver;

import java.util.Scanner;

public interface SolveStratetgyTest {
    static SudokuBoard getBoard(String name) {
        return SudokuSolver.initialize(new Scanner(ClassLoader.getSystemResourceAsStream(name))).getBoard();
    }
}
