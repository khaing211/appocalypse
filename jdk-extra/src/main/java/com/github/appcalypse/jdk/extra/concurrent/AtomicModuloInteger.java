package com.github.appcalypse.jdk.extra.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicModuloInteger {
	final private int mod;
	final private AtomicInteger val = new AtomicInteger(0);
	
	public AtomicModuloInteger(int mod) {
		this.mod = mod;
	}
	
	public int getAndIncrement() {
		return val.getAndUpdate(i -> Math.floorMod(i+1, mod));
	}
	
	public int incrementAndGet() {
		return val.updateAndGet(i -> Math.floorMod(i+1, mod));
	}
	
	public int get() {
		return val.get();
	}
	
	public int getAndDecrement() {
		return val.getAndUpdate(i -> Math.floorMod(i-1, mod));
	}
	
	public int decrementAndGet() {
		return val.updateAndGet(i -> Math.floorMod(i-1, mod));
	}
	
	public int addAndGet(int delta) {
		return val.updateAndGet(i -> Math.floorMod(i+delta, mod));
	}
	
	public int getAndAdd(int delta) {
		return val.getAndUpdate(i -> Math.floorMod(i+delta, mod));
	}
}
