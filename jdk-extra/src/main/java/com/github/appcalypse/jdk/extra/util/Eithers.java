package com.github.appcalypse.jdk.extra.util;

import java.util.concurrent.Callable;

public interface Eithers {
    public static <T extends Throwable, A> Either<T, A> wrap(Callable<A> callable, Class<T> throwableClass) {
        try {
            return Either.ofValue(callable.call());
        } catch (Exception e) {
            return Either.ofThrowable(throwableClass.cast(e));
        }
    }

    public static <A> Either<Exception, A> wrap(Callable<A> callable) {
        try {
            return Either.ofValue(callable.call());
        } catch (Exception e) {
            return Either.ofThrowable(e);
        }
    }
}
