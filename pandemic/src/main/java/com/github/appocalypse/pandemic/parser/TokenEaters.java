package com.github.appocalypse.pandemic.parser;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Stream;

import com.github.appocalypse.guava.extra.GuavaCollectors;
import com.github.appocalypse.pandemic.Keyword;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

public class TokenEaters {
	private TokenEaters() { }
	
	final static private ImmutableMap<Keyword, TokenEater> KEYWORD_TOKEN_EATERS = 
			Arrays.stream(Keyword.values())
				.collect(GuavaCollectors.toImmutableMap(
					Function.identity(), 
					it -> new StringTokenEater(it.name())));
	
	public static TokenEater of(Keyword keyword) {
		return KEYWORD_TOKEN_EATERS.get(keyword);
	}
	
	public static TokenEater and(TokenEater ... tokenEaters) {
		return new AndTokenEater(Arrays.stream(tokenEaters).collect(GuavaCollectors.toImmutableList()));
	}
	
	public static OrTokenEater or(TokenEater ... tokenEaters) {
		return new OrTokenEater(Arrays.stream(tokenEaters).collect(GuavaCollectors.toImmutableList()));
	}
	
	public static TokenEater action(TokenEater ... tokenEaters) {
		ImmutableList<TokenEater> newTokenEaters = Arrays.stream(tokenEaters)
			.flatMap(it -> { return Stream.of(it, WhitespaceTokenEater.INSTANCE); } )
			.collect(GuavaCollectors.toImmutableList());
		
		return new AndTokenEater(newTokenEaters);
	}
}
