package com.github.appcalypse.jdk.extra.function;

import java.util.Arrays;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;

public interface Predicates {
	public static <T> Predicate<T> not(Predicate<T> predicate) {
		return predicate.negate();
	}
	
	/**
	 * @return a predicate that always return true
	 */
	public static <T> Predicate<T> alwaysTrue() {
		return i -> true;
	}
	
	/**
	 * @return predicate that always return false
	 */
	public static <T> Predicate<T> alwaysFalse() {
		return i -> false;
	}
	
	/**
	 * When boxed, predicate check for null before apply internal predicate
	 */
	public static Predicate<Integer> boxed(IntPredicate intPredicate) {
		return i -> i != null && intPredicate.test(i.intValue());
	}
	
	/**
	 * When boxed, predicate check for null before apply internal predicate
	 */
	public static Predicate<Long> boxed(LongPredicate longPredicate) {
		return i -> i != null && longPredicate.test(i.longValue());
	}
	
	public static Predicate<Integer> mod(int modulo) {
		return boxed(IntPredicates.mod(modulo));
	}
	
	public static Predicate<Long> mod(long modulo) {
		return boxed(LongPredicates.mod(modulo));
	}
	
	public static Predicate<Integer> equalsTo(int val) {
		return i -> i == val;
	}
	
	public static Predicate<Long> equalsTo(long val) {
		return i -> i == val;
	}
	
	public static Predicate<Integer> lessThan(int bound) {
		return boxed(IntPredicates.lessThan(bound));
	}
	
	public static Predicate<Long> lessThan(long bound) {
		return boxed(LongPredicates.lessThan(bound));
	}
	
	public static Predicate<Integer> greaterThan(int bound) {
		return boxed(IntPredicates.greaterThan(bound));
	}
	
	public static Predicate<Long> greaterThan(long bound) {
		return boxed(LongPredicates.greaterThan(bound));
	}
	
	public static Predicate<Integer> lessThanOrEquals(int bound) {
		return lessThan(bound).or(equalsTo(bound));
	}
	
	public static Predicate<Long> lessThanOrEquals(long bound) {
		return lessThan(bound).or(equalsTo(bound));
	}
	
	public static Predicate<Integer> greaterThanOrEquals(int bound) {
		return greaterThan(bound).or(equalsTo(bound));
	}
	
	public static Predicate<Long> greaterThanOrEquals(long bound) {
		return greaterThan(bound).or(equalsTo(bound));
	}
	
	/**
	 * @param objects
	 * @return a predicate that match any of the given objects using Object.equals()
	 */
	public static <T> Predicate<T> in(Object ... objects) {
		return Arrays.stream(objects)
			.map(i -> Predicate.<T>isEqual(i))
			.reduce(alwaysFalse(), (i, j) -> i.or(j));
	}
	
	/**
	 * @param predicates
	 * @return a predicate that match of given predicate (or logic)
	 * 
	 * Benefit of this is to combine all method reference without casting
	 * Furthermore, pass no predicates result in alwaysFalse()
	 */
	@SafeVarargs
	public static <T> Predicate<T> or(Predicate<T> ... predicates) {
		return Arrays.stream(predicates).reduce(alwaysFalse(), (i, j) -> i.or(j));
	}
	
	/**
	 * @param predicates
	 * @return a predicate that match of given predicate (and logic)
	 * 
	 * Benefit of this is to combine all method reference without casting
	 * Furthermore, pass no predicates result in alwaysTrue()
	 */
	public static <T> Predicate<T> and(Predicate<T> ... predicates) {
		return Arrays.stream(predicates).reduce(alwaysTrue(), (i, j) -> i.and(j));
	}
}
