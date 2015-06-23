package com.github.appocalypse.pandemic.token;

import java.util.Arrays;
import java.util.function.Function;

import com.github.kn.commons.guava.extra.GuavaCollectors;
import com.github.appocalypse.pandemic.Keyword;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

public class Tokens {
	private Tokens() { }
	
	final static private ImmutableMap<Keyword, Token> KEYWORD_TOKENS = 
			Arrays.stream(Keyword.values())
				.collect(GuavaCollectors.toImmutableMap(
					Function.identity(), 
					it -> new StringToken(it.name().toLowerCase())));
	
	public static Token of(Keyword keyword) {
		return KEYWORD_TOKENS.get(keyword);
	}
	
	public static Token string(String value) {
		return new StringToken(value);
	}
	
	public static Token cities() {
		return CitiesToken.INSTANCE;
	}
	
	public static ImmutableList<Token> tokens(Token... tokens) {
		return Arrays.stream(tokens)
			.collect(GuavaCollectors.toImmutableList());
	}
}
