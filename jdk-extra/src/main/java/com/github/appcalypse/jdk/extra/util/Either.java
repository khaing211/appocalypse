package com.github.appcalypse.jdk.extra.util;

import java.util.Optional;

public class Either<T extends Throwable, A> {
    final private T left;
    final private A right;

    private Either(T left, A right) {
        this.left = left;
        this.right = right;
    }

    public static <T extends Throwable, A> Either<T, A> ofThrowable(T throwable) {
        return new Either(throwable, null);
    }

    public static <T extends Throwable, A> Either<T, A> ofValue(A value) {
        return new Either(null, value);
    }

    public boolean isRight() {
        return right != null;
    }

    public boolean isLeft() {
        return left != null;
    }

    /**
     * @param defaultValue
     * @return the right value or default value if left
     */
    public A orLeft(A defaultValue) {
        if (isRight()) {
            return right;
        } else {
            return defaultValue;
        }
    }

    public Optional<A> right() {
        return Optional.ofNullable(right);
    }

    public Optional<T> left() {
        return Optional.ofNullable(left);
    }
}
