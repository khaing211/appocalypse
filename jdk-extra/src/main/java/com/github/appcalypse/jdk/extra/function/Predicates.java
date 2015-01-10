package com.github.appcalypse.jdk.extra.function;

import java.util.function.Predicate;

public interface Predicates {
	public static <T> Predicate<T> not(Predicate<T> predicate) {
		return predicate.negate();
	}
	
	public static Predicate<Integer> mod(int modulo) {
		return i -> i % modulo == 0;
	}
	
	public static Predicate<Long> mod(long modulo) {
		return i -> i % modulo == 0;
	}
}
