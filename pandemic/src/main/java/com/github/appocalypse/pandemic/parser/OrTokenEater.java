package com.github.appocalypse.pandemic.parser;

import java.util.Arrays;

import com.github.appocalypse.guava.extra.GuavaCollectors;
import com.google.common.collect.ImmutableList;

public class OrTokenEater implements TokenEater {

	final private ImmutableList<TokenEater> tokenEaters;
	
	public OrTokenEater(ImmutableList<TokenEater> tokenEaters) {
		this.tokenEaters = tokenEaters;
	}
	
	@Override
	public ImmutableList<Cursor> eat(int fromIndex, String value) {
		return tokenEaters.stream()
			.map(tokenEater -> tokenEater.eat(fromIndex, value))
			.collect(GuavaCollectors.flattenImmutableList());
	}
	
	public static OrTokenEater orTokenEaters(TokenEater ... tokenEaters) {
		return new OrTokenEater(Arrays.stream(tokenEaters).collect(GuavaCollectors.toImmutableList()));
	}
}
