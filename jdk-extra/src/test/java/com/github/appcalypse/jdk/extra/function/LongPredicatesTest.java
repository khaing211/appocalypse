package com.github.appcalypse.jdk.extra.function;

import static org.junit.Assert.*;

import java.util.stream.LongStream;

import org.junit.Test;

public class LongPredicatesTest {

	@Test
	public void testLongModulo() {
		LongStream.range(0, 10)
			.boxed()
			.filter(Predicates.mod(3L))
			.peek(System.out::println)
			.forEach(i -> assertTrue(i % 3 == 0));
	}

}
