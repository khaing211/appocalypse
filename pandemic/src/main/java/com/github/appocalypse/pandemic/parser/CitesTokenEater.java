package com.github.appocalypse.pandemic.parser;

import java.util.Arrays;

import com.github.appocalypse.guava.extra.GuavaCollectors;
import com.github.appocalypse.pandemic.Cities;
import com.google.common.collect.ImmutableList;

public enum CitesTokenEater implements TokenEater {
	INSTANCE;

	final private ImmutableList<TokenEater> CITIES_TOKEN_EATERS = Arrays.stream(Cities.values())
			.map(it -> new StringTokenEater(it.getName()))
			.collect(GuavaCollectors.toImmutableList());
	
	final private TokenEater innerTokenEater = new OrTokenEater(CITIES_TOKEN_EATERS);
	
	@Override
	public ImmutableList<Cursor> eat(int fromIndex, String value) {
		return innerTokenEater.eat(fromIndex, value);
	}
}
