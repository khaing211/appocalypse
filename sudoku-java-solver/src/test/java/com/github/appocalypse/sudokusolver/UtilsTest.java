package com.github.appocalypse.sudokusolver;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class UtilsTest {
    @Test
    public void combination2() {
        final List<int[]> combination = Utils.choose(4, 2);
        for (final int[] set : combination) {
            System.out.println(Arrays.toString(set));
        }
    }
}
