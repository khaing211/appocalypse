package com.github.appcalypse.jdk.extra.concurrent;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicModuloLong {
	final private long mod;
	final private AtomicLong val = new AtomicLong();
	
	public AtomicModuloLong(long mod) {
		this.mod = mod;
	}
}
