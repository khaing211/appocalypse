package com.github.appocalypse.pandemic.token;

import java.util.Arrays;

import com.github.appocalypse.guava.extra.GuavaCollectors;
import com.github.appocalypse.pandemic.Cities;
import com.github.appocalypse.pandemic.City;
import com.google.common.collect.ImmutableList;

public enum CitiesToken implements Token {
	INSTANCE;
	
	final private Token delegate;
	
	private CitiesToken() {
		delegate = new GroupToken(Arrays.stream(Cities.values())
				.map(City::getName)
				.collect(GuavaCollectors.toImmutableList()));
	}
	
	@Override
	public ImmutableList<String> values(String prefix) {
		return delegate.values(prefix);
	}

}
