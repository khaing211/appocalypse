package com.github.appocalypse.sudokusolver;

/**
 * Immutable class
 */
public class Cell {
    private final int r;
    private final int c;
    private final int possibleSet;
    private final int n;
    private final boolean hasNakedPair;
    private final boolean hasNakedTriple;
    private final boolean hasNakedQuad;
    private final boolean hasHiddenPair;
    private final boolean hasHiddenTriple;
    private final boolean hasHiddenQuad;

    public Cell(int r, int c, int possibleSet, int n,
                boolean hasNakedPair, boolean hasNakedTriple, boolean hasNakedQuad,
                boolean hasHiddenPair, boolean hasHiddenTriple, boolean hasHiddenQuad) {
        this.r = r;
        this.c = c;
        this.possibleSet = possibleSet;
        this.n = n;
        this.hasNakedPair = hasNakedPair;
        this.hasNakedTriple = hasNakedTriple;
        this.hasNakedQuad = hasNakedQuad;
        this.hasHiddenPair = hasHiddenPair;
        this.hasHiddenTriple = hasHiddenTriple;
        this.hasHiddenQuad = hasHiddenQuad;
    }

    public Cell(int r, int c, int possibleSet, int n) {
        this(r, c, possibleSet, n, false, false, false, false, false, false);
    }

    public Cell(int r, int c, int n) {
        this(r, c, 1<<(n-1), n);
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

    public int getBaseR() {
        return getBoxR() * 3;
    }

    public int getBaseC() {
        return getBoxC() * 3;
    }

    public int getPossibleSet() {
        return possibleSet;
    }

    public int getPossibleSetSize() {
        return Utils.size(possibleSet);
    }

    public int[] getPossibles() {
        final int[] ret = new int[getPossibleSetSize()];
        int j = 0;
        for (int i = 1; i <= 9; i++) {
            if (isPossible(i)) {
                ret[j++] = i;
            }
        }
        return ret;
    }

    public int getN() {
        return n;
    }

    public boolean isFilled() {
        return n != 0;
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

    public boolean hasHiddenQuad() {
        return hasHiddenQuad;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "r=" + r +
                ", c=" + c +
                ", possibleSet=" + possibleSet +
                ", n=" + n +
                ", hasNakedPair=" + hasNakedPair +
                ", hasNakedTriple=" + hasNakedTriple +
                ", hasNakedQuad=" + hasNakedQuad +
                ", hasHiddenPair=" + hasHiddenPair +
                ", hasHiddenTriple=" + hasHiddenTriple +
                ", hasHiddenQuad=" + hasHiddenQuad +
                '}';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder copy(Cell cell) {
        return new Builder()
                .withR(cell.getR())
                .withC(cell.getC())
                .withN(cell.getN())
                .withPossibleSet(cell.getPossibleSet())
                .withHasNakedPair(cell.hasNakedPair())
                .withHasNakedTriple(cell.hasNakedTriple())
                .withHasNakedQuad(cell.hasNakedQuad())
                .withHasHiddenPair(cell.hasHiddenPair())
                .withHasHiddenTriple(cell.hasHiddenTriple())
                .withHasHiddenQuad(cell.hasHiddenQuad());
    }

    public static class Builder {
        private int r;
        private int c;
        private int possibleSet;
        private int n;
        private boolean hasNakedPair;
        private boolean hasNakedTriple;
        private boolean hasNakedQuad;
        private boolean hasHiddenPair;
        private boolean hasHiddenTriple;
        private boolean hasHiddenQuad;

        public Builder withR(int r) {
            this.r = r;
            return this;
        }

        public Builder withC(int c) {
            this.c = c;
            return this;
        }

        public Builder withPossibleSet(int possibleSet) {
            this.possibleSet = possibleSet;
            return this;
        }

        public Builder withN(int n) {
            this.n = n;
            return this;
        }

        public Builder withHasNakedPair(boolean hasNakedPair) {
            this.hasNakedPair = hasNakedPair;
            return this;
        }

        public Builder withHasNakedTriple(boolean hasNakedTriple) {
            this.hasNakedTriple = hasNakedTriple;
            return this;
        }

        public Builder withHasNakedQuad(boolean hasNakedQuad) {
            this.hasNakedQuad = hasNakedQuad;
            return this;
        }

        public Builder withHasHiddenPair(boolean hasHiddenPair) {
            this.hasHiddenPair = hasHiddenPair;
            return this;
        }

        public Builder withHasHiddenTriple(boolean hasHiddenTriple) {
            this.hasHiddenTriple = hasHiddenTriple;
            return this;
        }

        public Builder withHasHiddenQuad(boolean hasHiddenQuad) {
            this.hasHiddenQuad = hasHiddenQuad;
            return this;
        }

        public Cell build() {
            return new Cell(r, c, possibleSet, n, hasNakedPair, hasNakedTriple,
                    hasNakedQuad, hasHiddenPair, hasHiddenTriple, hasHiddenQuad);
        }
    }
}
