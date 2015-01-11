package com.github.appcalypse.jdk.extra.function;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.junit.Test;

public class PredicatesTest {

	@Test
	public void testAlwaysTrue() {
		assertEquals(10, IntStream.range(0, 10).boxed().filter(Predicates.alwaysTrue()).count());
	}
	
	@Test
	public void testAlwaysFalse() {
		assertEquals(0, IntStream.range(0, 10).boxed().filter(Predicates.alwaysFalse()).count());
	}
}
