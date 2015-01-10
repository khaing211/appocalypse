package com.github.appcalypse.jdk.extra.function;

import static org.junit.Assert.assertTrue;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.junit.Test;

public class PredicatesTest {

	@Test
	public void testIntModulo() {
		IntStream.range(0, 10)
			.boxed()
			.filter(Predicates.mod(3))
			.peek(System.out::println)
			.forEach(i -> assertTrue(i % 3 == 0));
	}

	@Test
	public void testLongModulo() {
		LongStream.range(0, 10)
			.boxed()
			.filter(Predicates.mod(3L))
			.peek(System.out::println)
			.forEach(i -> assertTrue(i % 3 == 0));
	}
}
