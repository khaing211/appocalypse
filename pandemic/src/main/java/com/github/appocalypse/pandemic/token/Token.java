package com.github.appocalypse.pandemic.token;

import com.google.common.collect.ImmutableList;

public interface Token {
	/**
	 * @param buffer
	 * @return a list of values that buffer.startsWith()
	 */
	ImmutableList<String> values(String buffer);
}
