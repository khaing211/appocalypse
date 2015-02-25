package com.github.appcalypse.jdk.extra.util;

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

    public A orLeft(A defaultValue) {
        if (isRight()) {
            return right;
        } else {
            return defaultValue;
        }
    }
}
