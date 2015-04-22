package com.github.appocalypse;

import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.assertTrue;

public class SudokuSolverTest {
    // TODO: refactor using dataset ?
    @Test
    public void testEasy_0() {
        assertTrue(SudokuSolver.initialize(new Scanner(ClassLoader.getSystemResourceAsStream("easy_0.txt"))).solve());
    }

    @Test
    public void testMedium_0() {
        assertTrue(SudokuSolver.initialize(new Scanner(ClassLoader.getSystemResourceAsStream("medium_0.txt"))).solve());
    }

    @Test
    public void testHard_0() {
        SudokuSolver.mainSolve(SudokuSolver.initialize(new Scanner(ClassLoader.getSystemResourceAsStream("hard_0.txt"))));
    }
}
