package com.github.appocalypse.pandemic;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class InfectionRateCounterTest {

	@Test
	public void testImmutable() {
		InfectionRateCounter counter1 = InfectionRateCounter.initial();
		assertEquals(2, counter1.current());
		
		InfectionRateCounter counter2 = counter1;
		
		for (int i = 0; i < 3; i++) {
			counter2 = counter2.increase();
		}
		
		assertEquals(2, counter1.current());
		assertEquals(3, counter2.current());
		
		InfectionRateCounter counter3 = counter2;
		for (int i = 0; i < 3; i++) {
			counter3 = counter3.increase();
		}

		assertEquals(2, counter1.current());
		assertEquals(3, counter2.current());
		assertEquals(4, counter3.current());
	}
	
	@Test
	public void testIncrease() {
		InfectionRateCounter counter = InfectionRateCounter.initial();
		assertEquals(2, counter.current());
		
		counter = counter.increase();
		assertEquals(2, counter.current());
		
		counter = counter.increase();
		assertEquals(2, counter.current());
		
		counter = counter.increase();
		assertEquals(3, counter.current());
		
		counter = counter.increase();
		assertEquals(3, counter.current());
		
		counter = counter.increase();
		assertEquals(4, counter.current());
		
		counter = counter.increase();
		assertEquals(4, counter.current());
	}

}
