package com.github.appcalypse.jdk.extra.function;

import java.util.function.IntPredicate;

public interface IntPredicates {
	public static IntPredicate not(IntPredicate predicate) {
		return predicate.negate();
	}
	
	public static IntPredicate mod(int modulo) {
		return i -> i % modulo == 0;
	}
}
