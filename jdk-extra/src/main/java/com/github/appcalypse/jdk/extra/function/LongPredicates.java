package com.github.appcalypse.jdk.extra.function;

import java.util.function.LongPredicate;

public interface LongPredicates {
	public static LongPredicate not(LongPredicate predicate) {
		return predicate.negate();
	}
	
	public static LongPredicate mod(long modulo) {
		return i -> i % modulo == 0;
	}
}
