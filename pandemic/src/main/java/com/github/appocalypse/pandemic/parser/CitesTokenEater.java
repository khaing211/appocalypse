package com.github.appocalypse.pandemic.parser;

import java.util.Arrays;

import com.github.appocalypse.guava.extra.GuavaCollectors;
import com.github.appocalypse.pandemic.Cities;
import com.google.common.collect.ImmutableList;

public class CitesTokenEater extends OrTokenEater {

	final private static ImmutableList<TokenEater> CITIES_TOKEN_EATER = Arrays.stream(Cities.values())
			.map(it -> new StringTokenEater(it.getName()))
			.collect(GuavaCollectors.toImmutableList());
	
	public CitesTokenEater() {
		super(CITIES_TOKEN_EATER);
	}
}
