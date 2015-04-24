package com.github.appocalypse.sudokusolver;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class UtilsTest {
    @Test
    public void choose() {
        final List<int[]> combination = Utils.choose(4, 2);
        assertEquals(6, combination.size());
    }

    @Test
    public void choose2() {
        assertEquals(36, Utils.CHOOSE_2_SET.size());
        assertEquals(36, Utils.CHOOSE_2_BIT_SET.size());
    }

    @Test
    public void choose3() {
        assertEquals(84, Utils.CHOOSE_3_SET.size());
        assertEquals(84, Utils.CHOOSE_3_BIT_SET.size());

    }

    @Test
    public void choose4() {
        assertEquals(126, Utils.CHOOSE_4_SET.size());
        assertEquals(126, Utils.CHOOSE_4_BIT_SET.size());
    }

    @Test
    public void chooseBit() {
        final List<Integer> combination = Utils.chooseBit(4, 2);
        assertEquals(6, combination.size());
    }
}
