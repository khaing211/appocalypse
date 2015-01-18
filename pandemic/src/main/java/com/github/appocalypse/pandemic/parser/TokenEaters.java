package com.github.appocalypse.pandemic.parser;

import java.util.Arrays;
import java.util.function.Function;

import com.github.appocalypse.guava.extra.GuavaCollectors;
import com.github.appocalypse.pandemic.console.Keyword;
import com.google.common.collect.ImmutableMap;

public class TokenEaters {
	private TokenEaters() { }
	
	final static private ImmutableMap<Keyword, TokenEater> keywordTokenEater = 
			Arrays.stream(Keyword.values())
				.collect(GuavaCollectors.toImmutableMap(
					Function.identity(), 
					it -> new StringTokenEater(it.name())));
	
	public TokenEater getTokenEater(Keyword keyword) {
		return keywordTokenEater.get(keyword);
	}
}
