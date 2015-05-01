package com.github.appocalypse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class SudokuSolverTest {
    @Parameterized.Parameters
    public static Collection<String> data() {
        return Arrays.asList(
                "easy_0.txt",
                "easy_1.txt",
                "medium_0.txt",
                "medium_1.txt",
                "hard_0.txt");
    }

    @Parameterized.Parameter
    public String sudokuFile;

    @Test
    public void testSudoku() {
        System.out.println("Testing " + sudokuFile);
        final SudokuSolver solver = SudokuSolver.initialize(new Scanner(ClassLoader.getSystemResourceAsStream(sudokuFile)));
        assertTrue("unable to solve " + sudokuFile, solver.solve());
        assertTrue("solution is not valid for " + sudokuFile, solver.validateBoard());
    }
}
