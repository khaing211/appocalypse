package com.github.appocalypse.pandemic.token;

import com.google.common.collect.ImmutableList;

public class StringToken implements Token {
	final private String value;
	
	public StringToken(String value) {
		this.value = value;
	}

	@Override
	public ImmutableList<String> values(String buffer) {
		if (value.startsWith(buffer) || buffer.startsWith(value)) {
			return ImmutableList.of(value);
		} else {
			return ImmutableList.of();
		}
	}
}
