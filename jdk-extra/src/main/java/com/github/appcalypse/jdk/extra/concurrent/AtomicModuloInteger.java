package com.github.appcalypse.jdk.extra.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicModuloInteger {
	final private int mod;
	final private AtomicInteger val = new AtomicInteger(0);
	
	public AtomicModuloInteger(int mod) {
		this.mod = mod;
	}
	
	public int incrementAndGet() {
		return val.updateAndGet(i -> Math.floorMod(i+1, mod));
	}
	
	public int get() {
		return val.get();
	}
	
	public int decrementAndGet() {
		return val.updateAndGet(i -> Math.floorMod(i-1, mod));
	}
	
	public int addAndGet(int increment) {
		return val.updateAndGet(i -> Math.floorMod(i+increment, mod));
	}
}
