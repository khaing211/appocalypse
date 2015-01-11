package com.github.appcalypse.jdk.extra.function;

import java.util.function.IntPredicate;

public interface IntPredicates {
	public static IntPredicate not(IntPredicate predicate) {
		return predicate.negate();
	}
	
	public static IntPredicate mod(int modulo) {
		return i -> i % modulo == 0;
	}
	
	public static IntPredicate equalsTo(int val) {
		return i -> i == val;
	}
	
	public static IntPredicate lessThan(int bound) {
		return i -> i < bound;
	}
	
	public static IntPredicate greaterThan(int bound) {
		return i -> i > bound;
	}
	
	public static IntPredicate lessThanOrEquals(int bound) {
		return lessThan(bound).or(equalsTo(bound));
	}
	
	public static IntPredicate greaterThanOrEquals(int bound) {
		return greaterThan(bound).or(equalsTo(bound));
	}
}
