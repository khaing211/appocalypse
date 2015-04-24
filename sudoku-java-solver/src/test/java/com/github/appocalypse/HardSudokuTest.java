package com.github.appocalypse;

import org.junit.Test;

import java.util.Scanner;

public class HardSudokuTest {
    @Test
    public void testHard_0() {
        SudokuSolver.mainSolve(SudokuSolver.initialize(new Scanner(ClassLoader.getSystemResourceAsStream("hard_0.txt"))));
    }
}
