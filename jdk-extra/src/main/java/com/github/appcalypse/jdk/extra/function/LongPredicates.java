package com.github.appcalypse.jdk.extra.function;

import java.util.function.LongPredicate;

public interface LongPredicates {
	public static LongPredicate not(LongPredicate predicate) {
		return predicate.negate();
	}
	
	public static LongPredicate odd() {
		return i -> i % 2 == 1;
	}
	
	public static LongPredicate even() {
		return mod(2);
	}
	
	public static LongPredicate mod(long modulo) {
		return i -> i % modulo == 0;
	}
	
	public static LongPredicate equalsTo(long val) {
		return i -> i == val;
	}
	
	public static LongPredicate lessThan(long bound) {
		return i -> i < bound;
	}
	
	public static LongPredicate greaterThan(long bound) {
		return i -> i > bound;
	}
	
	public static LongPredicate lessThanOrEquals(long bound) {
		return lessThan(bound).or(equalsTo(bound));
	}
	
	public static LongPredicate greaterThanOrEquals(long bound) {
		return greaterThan(bound).or(equalsTo(bound));
	}
}
