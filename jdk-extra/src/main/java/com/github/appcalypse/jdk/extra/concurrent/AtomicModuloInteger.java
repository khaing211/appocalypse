package com.github.appcalypse.jdk.extra.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicModuloInteger {
	final private int mod;
	final private AtomicInteger val = new AtomicInteger(0);
	
	public AtomicModuloInteger(int mod) {
		this.mod = mod;
	}
}
