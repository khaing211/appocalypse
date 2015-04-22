package com.github.appocalypse.sudokusolver;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UtilsTest {
    @Test
    public void choose() {
        final List<int[]> combination = Utils.choose(4, 2);
        for (final int[] set : combination) {
            System.out.println(Arrays.toString(set));
        }
        assertEquals(6, combination.size());
    }

    @Test
    public void chooseBit() {
        final List<Integer> combination = Utils.chooseBit(4, 2);
        System.out.println(combination);
        assertEquals(6, combination.size());
    }
}
