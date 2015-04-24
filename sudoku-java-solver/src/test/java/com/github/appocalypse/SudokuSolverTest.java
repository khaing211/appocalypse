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
        assertTrue(SudokuSolver.initialize(new Scanner(ClassLoader.getSystemResourceAsStream(sudokuFile))).solve());
    }
}
