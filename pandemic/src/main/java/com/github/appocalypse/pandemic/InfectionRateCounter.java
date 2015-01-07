package com.github.appocalypse.pandemic;

public class InfectionRateCounter {
	final private static int[] RATE = { 2, 2, 2, 3, 3, 4, 4 };
	
	final private int rateCounter;
	
	private InfectionRateCounter(int rateCounter) {
		this.rateCounter = rateCounter;
	}
	
	public int current() {
		return RATE[rateCounter];
	}
	
	public static InfectionRateCounter initial() {
		return new InfectionRateCounter(0);
	}
	
	public InfectionRateCounter increase() {
		if (rateCounter >= RATE.length) return this;
		return new InfectionRateCounter(rateCounter + 1);
	}
}
