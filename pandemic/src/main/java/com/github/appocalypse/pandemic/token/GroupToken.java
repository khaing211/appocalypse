package com.github.appocalypse.pandemic.token;

import com.github.kn.commons.guava.extra.GuavaCollectors;
import com.google.common.collect.ImmutableList;

public class GroupToken implements Token {

	final private ImmutableList<Token> tokens;
	
	public GroupToken(ImmutableList<Token> tokens) {
		this.tokens = tokens;
	}
	
	@Override
	public ImmutableList<String> values(String buffer) {
		return tokens.stream()
				.map(it -> it.values(buffer))
				.collect(GuavaCollectors.flattenImmutableList());
	}

}
