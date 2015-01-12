package com.github.appcalypse.jdk.extra.concurrent;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicModuloLong {
	final private long mod;
	final private AtomicLong val = new AtomicLong();
	
	public AtomicModuloLong(long mod) {
		this.mod = mod;
	}
	
	public long getAndIncrement() {
		return val.updateAndGet(i -> Math.floorMod(i+1, mod));
	}
	
	public long incrementAndGet() {
		return val.updateAndGet(i -> Math.floorMod(i+1, mod));
	}
	
	public long get() {
		return val.get();
	}
	
	public long getAndDecrement() {
		return val.updateAndGet(i -> Math.floorMod(i-1, mod));
	}
	
	public long decrementAndGet() {
		return val.updateAndGet(i -> Math.floorMod(i-1, mod));
	}
	
	public long addAndGet(long delta) {
		return val.updateAndGet(i -> Math.floorMod(i+delta, mod));
	}
	
	public long getAndAdd(long delta) {
		return val.getAndUpdate(i -> Math.floorMod(i+delta, mod));
	}
}
