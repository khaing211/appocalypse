package com.github.appcalypse.jdk.extra.stream;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collector;
import java.util.stream.Collector.Characteristics;

public class JdkCollectors {
	/**
	 * Apply or logic across stream of boolean
	 * Support concurrent stream
	 * Empty stream will result in false
	 */
	public static Collector<Boolean, ?, Boolean> or() {
		return Collector.of(() -> new AtomicBoolean(false), 
				(builder, element) -> { while (builder.compareAndSet(builder.get(), builder.get() || element) == false); },
				(i,j) -> new AtomicBoolean(i.get() || j.get()), 
				i -> i.get(),
				Characteristics.CONCURRENT);
	}
	
	/**
	 * Apply and logic across stream of boolean
	 * Support concurrent stream
	 * Empty stream will result in true
	 */
	public static Collector<Boolean, ?, Boolean> and() {
		return Collector.of(() -> new AtomicBoolean(true), 
				(builder, element) -> { while (builder.compareAndSet(builder.get(), builder.get() && element) == false); },
				(i,j) -> new AtomicBoolean(i.get() && j.get()), 
				i -> i.get(),
				Characteristics.CONCURRENT);
	}
}
