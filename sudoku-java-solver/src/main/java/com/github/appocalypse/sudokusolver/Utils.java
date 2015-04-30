package com.github.appocalypse.sudokusolver;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public interface Utils {
    int ALL = (1<<9)-1;
    int MAX_NUM_UNSOLVED_CELL = 81;
    List<Integer> CHOOSE_2_BIT_SET = chooseBit(9, 2);
    List<Integer> CHOOSE_3_BIT_SET = chooseBit(9, 3);
    List<Integer> CHOOSE_4_BIT_SET = chooseBit(9, 4);
    List<int[]> CHOOSE_2_SET = choose(9, 2);
    List<int[]> CHOOSE_3_SET = choose(9, 3);
    List<int[]> CHOOSE_4_SET = choose(9, 4);

    static void isValidIndex(int r, int c) {
        if (0 > r || r >= 9) {
            throw new IllegalArgumentException("invalid r value " + r);
        }

        if (0 > c || c >= 9) {
            throw new IllegalArgumentException("invalid c value " + c);
        }
    }

    static void isValidNumber(int n) {
        if (n <= 0 || n > 9) {
            throw new IllegalArgumentException("n value cannot be less than or equal 0 or greater than 9");
        }
    }

    static boolean isInSameBox(Cell c0, Cell c1) {
        return c0.getBoxR() == c1.getBoxR() && c0.getBoxC() == c1.getBoxC();
    }

    static boolean isInSameBox(Cell ... cells) {
        if (cells.length < 2) return true;
        final Cell c0 = cells[0];
        for (Cell cell : cells) {
            if (!isInSameBox(c0, cell)) return false;
        }
        return true;
    }

    static boolean isInSameRow(Cell c0, Cell c1) {
        return c0.getR() == c1.getBoxR();
    }

    static boolean isInSameRow(Cell ... cells) {
        if (cells.length < 2) return true;
        final Cell c0 = cells[0];
        for (Cell cell : cells) {
            if (!isInSameRow(c0, cell)) return false;
        }
        return true;
    }

    static boolean isInSameCol(Cell c0, Cell c1) {
        return c0.getC() == c1.getC();
    }

    static boolean isInSameCol(Cell ... cells) {
        if (cells.length < 2) return true;
        final Cell c0 = cells[0];
        for (Cell cell : cells) {
            if (!isInSameCol(c0, cell)) return false;
        }
        return true;
    }

    /**
     * It is good to use when you only have 1 bit set
     * This method will return number from candidateSet
     *
     * @param candidateSet
     * @return n
     */
    static int getNumber(int candidateSet) {
        return Integer.numberOfTrailingZeros(candidateSet) + 1;
    }

    /**
     * Alias for popcount32
     * @param candidateSet
     * @return
     */
    static int size(int candidateSet) {
        return popcount32(candidateSet);
    }

    static int unset(int candidateSet, int n) {
        final int mask = ((~(1<<(n-1))) & ALL);
        return candidateSet & mask;
    }

    static boolean isNotIn(final int[] set, int needle) {
        return !isIn(set, needle);
    }

    static boolean isIn(final int[] set, int needle) {
        for (final int i : set) {
            if (i == needle) {
                return true;
            }
        }
        return false;
    }

    static boolean isNot(final Cell thisCell, final Cell ... cells) {
        for (final Cell otherCell : cells) {
            if (otherCell.getR() == thisCell.getR() && otherCell.getC() == thisCell.getC()) {
                return false;
            }
        }
        return true;
    }

    static boolean isAllTrue(boolean[] check) {
        for (boolean b : check) if (!b) return false;
        return true;
    }

    /**
     * Calculate number of bits in the vector
     *
     * http://en.wikipedia.org/wiki/Hamming_weight
     */
    static int popcount32(int bits) {
        // fast check
        if (bits == 0) return 0;

        final int m1  = 0x5555;     //binary: 0101...
        final int m2  = 0x3333;     //binary: 00110011..
        final int m4  = 0x0f0f;     //binary:  4 zeros,  4 ones ...
        final int m8  = 0x00ff;     //binary:  8 zeros,  8 ones ..
        final int m16 = 0x0000ffff; //binary: 16 zeros, 16 ones ...

        bits = (bits & m1 ) + ((bits >>  1) & m1 ); //put count of each  2 bits into those  2 bits
        bits = (bits & m2 ) + ((bits >>  2) & m2 ); //put count of each  4 bits into those  4 bits
        bits = (bits & m4 ) + ((bits >>  4) & m4 ); //put count of each  8 bits into those  8 bits
        bits = (bits & m8 ) + ((bits >>  8) & m8 ); //put count of each 16 bits into those 16 bits
        bits = (bits & m16) + ((bits >> 16) & m16); //put count of each 32 bits into those 32 bits
        return bits;
    }

    /**
     * Calculate difference bit set a - b
     *
     * @param a 16 bits vector
     * @param b 16 bits vector
     *
     * @return 0 if everything is same,
     *         a if a does not have any common with b
     *         a - b if a does have common with b
     */
    static int difference(int a, int b) {
        return ((a ^ b) & a);
    }

    static List<int[]> choose(final int n, final int k) {
        if (k < 0 || n < 0 || k > n) {
            return ImmutableList.of();
        }

        final ImmutableList.Builder<int[]> sets = ImmutableList.builder();
        final int[] set = IntStream.range(0, k).toArray();
        do {
            sets.add(Arrays.copyOf(set, k));
        } while (next(set, n, k));

        return sets.build();
    }

    static boolean next(int[] set, int n, int k) {
        int i = k - 1;
        while (i >= 0) {
            if (set[i] == n-k+i) {
                i--;
            } else {
                set[i]++;
                for (; i < k-1; i++) {
                    set[i+1] = set[i]+1;
                }
                return true;
            }
        }

        return false;
    }

    /**
     * http://stackoverflow.com/questions/1851134/generate-all-binary-strings-of-length-n-with-k-bits-set
     *
     * Suppose we have a pattern of N bits set to 1 in an integer and we want the
     * next permutation of N 1 bits in a lexicographical sense.
     *
     * For example, if N is 3 and the bit pattern is 00010011, the next patterns would be
     * 00010101, 00010110, 00011001, 00011010, 00011100, 00100011, and so forth.
     *
     * @param n between 0 and 31
     */
    static List<Integer> chooseBit(int n, int k) {
        final ImmutableList.Builder<Integer> sets = ImmutableList.builder();
        final int mask = (1<<n)-1;
        final int testMask = ~mask;

        int v = (1<<k)-1; // current permutation of bits


        do {
            sets.add(v & mask);
            // t gets v's least significant 0 bits set to 1
            final int t = v | (v - 1);

            // Next set to 1 the most significant bit to change,
            // set to 0 the least significant ones, and add the necessary 1 bits.
            v = (t + 1) | (((~t & -~t) - 1) >> (Integer.numberOfTrailingZeros(v) + 1));
        } while((v & testMask) == 0);

        return sets.build();
    }
}
