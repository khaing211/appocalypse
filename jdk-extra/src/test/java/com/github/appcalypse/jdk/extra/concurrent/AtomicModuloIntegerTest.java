package com.github.appcalypse.jdk.extra.concurrent;

import static org.junit.Assert.*;

import org.junit.Test;

public class AtomicModuloIntegerTest {

	@Test
	public void testGetAndIncrement() {
		AtomicModuloInteger val = new AtomicModuloInteger(3);
		assertEquals(0, val.get());
		assertEquals(0, val.getAndIncrement());
		assertEquals(1, val.get());
		assertEquals(1, val.getAndIncrement());
		assertEquals(2, val.get());
		assertEquals(2, val.getAndIncrement());
		assertEquals(0, val.get());
	}
	
	@Test
	public void testIncrementAndGet() {
		AtomicModuloInteger val = new AtomicModuloInteger(3);
		assertEquals(0, val.get());
		assertEquals(1, val.incrementAndGet());
		assertEquals(1, val.get());
		assertEquals(2, val.incrementAndGet());
		assertEquals(2, val.get());
		assertEquals(0, val.incrementAndGet());
		assertEquals(0, val.get());
	}
	
	@Test
	public void testGetAndDecrement() {
		AtomicModuloInteger val = new AtomicModuloInteger(3);
		assertEquals(0, val.get());
		assertEquals(0, val.getAndDecrement());
		assertEquals(2, val.get());
		assertEquals(2, val.getAndDecrement());
		assertEquals(1, val.get());
		assertEquals(1, val.getAndDecrement());
		assertEquals(0, val.get());
	}
	
	@Test
	public void testDecrementAndGet() {
		AtomicModuloInteger val = new AtomicModuloInteger(3);
		assertEquals(0, val.get());
		assertEquals(2, val.decrementAndGet());
		assertEquals(2, val.get());
		assertEquals(1, val.decrementAndGet());
		assertEquals(1, val.get());
		assertEquals(0, val.decrementAndGet());
		assertEquals(0, val.get());
	}
	
	@Test
	public void testGetAndAdd() {
		AtomicModuloInteger val = new AtomicModuloInteger(3);
		assertEquals(0, val.get());
		assertEquals(0, val.getAndAdd(2));
		assertEquals(2, val.get());
		assertEquals(2, val.getAndAdd(2));
		assertEquals(1, val.get());
		assertEquals(1, val.getAndAdd(2));
		assertEquals(0, val.get());
	}
	
	@Test
	public void testAddAndGet() {
		AtomicModuloInteger val = new AtomicModuloInteger(3);
		assertEquals(0, val.get());
		assertEquals(2, val.addAndGet(2));
		assertEquals(2, val.get());
		assertEquals(1, val.addAndGet(2));
		assertEquals(1, val.get());
		assertEquals(0, val.addAndGet(2));
		assertEquals(0, val.get());
	}
}
