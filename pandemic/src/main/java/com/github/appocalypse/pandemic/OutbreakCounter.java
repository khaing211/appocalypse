package com.github.appocalypse.pandemic;

import com.google.common.collect.ImmutableList;

public class OutbreakCounter {
	final private ImmutableList<City> outbreakCities;
	
	private OutbreakCounter(ImmutableList<City> outbreakCities) {
		this.outbreakCities = outbreakCities;
	}
	
	public static OutbreakCounter zero() {
		return new OutbreakCounter(ImmutableList.of());
	}
	
	public OutbreakCounter outbreakAt(City city) {
		return new OutbreakCounter(ImmutableList.<City>builder().addAll(outbreakCities).add(city).build());
	}
	
	public int current() {
		return outbreakCities.size();
	}
	
	public ImmutableList<City> getOutbreakCities() {
		return outbreakCities;
	}
}
