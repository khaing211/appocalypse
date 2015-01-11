package com.github.appcalypse.jdk.extra.function;

import static org.junit.Assert.*;

import java.util.stream.IntStream;

import org.junit.Test;

public class IntPredicatesTest {

	@Test
	public void testIntModulo() {
		IntStream.range(0, 10)
			.boxed()
			.filter(Predicates.mod(3))
			.peek(System.out::println)
			.forEach(i -> assertTrue(i % 3 == 0));
	}

}
