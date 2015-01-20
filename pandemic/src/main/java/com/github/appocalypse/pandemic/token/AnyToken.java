package com.github.appocalypse.pandemic.token;

import com.google.common.collect.ImmutableList;

public enum AnyToken implements Token {
	INSTANCE;
	
	@Override
	public ImmutableList<String> values(String prefix) {
		return ImmutableList.of(prefix);
	}

}
