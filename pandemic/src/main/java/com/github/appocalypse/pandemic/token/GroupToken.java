package com.github.appocalypse.pandemic.token;

import com.github.appocalypse.guava.extra.GuavaCollectors;
import com.google.common.collect.ImmutableList;

public class GroupToken implements Token {

	final private ImmutableList<String> values;
	
	public GroupToken(ImmutableList<String> values) {
		this.values = values;
	}
	
	@Override
	public ImmutableList<String> values(String prefix) {
		return values.stream().filter(it -> it.startsWith(prefix)).collect(GuavaCollectors.toImmutableList());
	}

}
