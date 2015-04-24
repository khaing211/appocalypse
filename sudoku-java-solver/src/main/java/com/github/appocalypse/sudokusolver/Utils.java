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

    static boolean isInSameBox(int r, int c, int otherR, int otherC) {
        return (r/3 == otherR/3) && (c/3 == otherC/3);
    }

    /**
     * It is good to use when you only have 1 bit set
     * This method will return number from possibleSet
     *
     * @param possibleSet
     * @return n
     */
    static int getNumber(int possibleSet) {
        return Integer.numberOfTrailingZeros(possibleSet) + 1;
    }


    /**
     * Alias for popcount32
     * @param possibleSet
     * @return
     */
    static int size(int possibleSet) {
        return popcount32(possibleSet);
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

    static int unset(int possibleSet, int n) {
        final int mask = ((~(1<<(n-1))) & ALL);
        return possibleSet & mask;
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
