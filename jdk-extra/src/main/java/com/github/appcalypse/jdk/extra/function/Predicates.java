package com.github.appcalypse.jdk.extra.function;

import java.util.function.Predicate;

public interface Predicates {
	public static <T> Predicate<T> not(Predicate<T> predicate) {
		return predicate.negate();
	}
}
