package com.github.appocalypse.sudokusolver;

public class Cell {
    private final int r;
    private final int c;
    private final int possibleSet;
    private final int value;
    private final boolean hasNakedPair;
    private final boolean hasNakedTriple;
    private final boolean hasNakedQuad;
    private final boolean hasHiddenPair;
    private final boolean hasHiddenTriple;
    private final boolean hasHiddenQuad;

    public Cell(int r, int c, int possibleSet, int value,
                boolean hasNakedPair, boolean hasNakedTriple, boolean hasNakedQuad,
                boolean hasHiddenPair, boolean hasHiddenTriple, boolean hasHiddenQuad) {
        this.r = r;
        this.c = c;
        this.possibleSet = possibleSet;
        this.value = value;
        this.hasNakedPair = hasNakedPair;
        this.hasNakedTriple = hasNakedTriple;
        this.hasNakedQuad = hasNakedQuad;
        this.hasHiddenPair = hasHiddenPair;
        this.hasHiddenTriple = hasHiddenTriple;
        this.hasHiddenQuad = hasHiddenQuad;
    }

    public Cell(int r, int c, int possibleSet, int value) {
        this(r, c, possibleSet, value, false, false, false, false, false, false);
    }

    public Cell(int r, int c) {
        this(r, c, Utils.ALL, 0);
    }

    public int getR() {
        return r;
    }

    public int getC() {
        return c;
    }

    public int getBoxR() {
        return r / 3;
    }

    public int getBoxC() {
        return c / 3;
    }

    public int getPossibleSet() {
        return possibleSet;
    }

    public int getValue() {
        return value;
    }

    public boolean isFilled() {
        return value != 0;
    }

    public boolean isNotFilled() {
        return !isFilled();
    }

    public boolean isPossible(int n) {
        final int mask = (1<<(n-1));
        return (possibleSet & mask) == mask;
    }

    public boolean hasNakedPair() {
        return hasNakedPair;
    }

    public boolean hasNakedTriple() {
        return hasNakedTriple;
    }

    public boolean hasNakedQuad() {
        return hasNakedQuad;
    }

    public boolean hasHiddenPair() {
        return hasHiddenPair;
    }

    public boolean hasHiddenTriple() {
        return hasHiddenTriple;
    }

    public boolean h0asHiddenQuad() {
        return hasHiddenQuad;
    }
}
