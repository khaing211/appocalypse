package com.github.appcalypse.jdk.extra.concurrent;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicModuloLong {
	final private long mod;
	final private AtomicLong val = new AtomicLong();
	
	public AtomicModuloLong(long mod) {
		this.mod = mod;
	}
	
	public long incrementAndGet() {
		return val.updateAndGet(i -> Math.floorMod(i+1, mod));
	}
	
	public long get() {
		return val.get();
	}
	
	public long decrementAndGet() {
		return val.updateAndGet(i -> Math.floorMod(i-1, mod));
	}
	
	public long addAndGet(long increment) {
		return val.updateAndGet(i -> Math.floorMod(i+increment, mod));
	}
}
