package com.github.appocalypse.sudokusolver;

public interface Utils {

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

    /**
     * Calculate number of bits in the vector
     *
     * http://en.wikipedia.org/wiki/Hamming_weight
     */
    static int popcount16(int bits) {
        // fast check
        if (bits == 0) return 0;

        final int m1  = 0x5555; //binary: 0101...
        final int m2  = 0x3333; //binary: 00110011..
        final int m4  = 0x0f0f; //binary:  4 zeros,  4 ones ...
        final int m8  = 0x00ff; //binary:  8 zeros,  8 ones ..
        bits = (bits & m1 ) + ((bits >>  1) & m1 ); //put count of each  2 bits into those  2 bits
        bits = (bits & m2 ) + ((bits >>  2) & m2 ); //put count of each  4 bits into those  4 bits
        bits = (bits & m4 ) + ((bits >>  4) & m4 ); //put count of each  8 bits into those  8 bits
        bits = (bits & m8 ) + ((bits >>  8) & m8 ); //put count of each 16 bits into those 16 bits
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
    static short difference(short a, short b) {
        return (short)((a ^ b) & a);
    }
}
