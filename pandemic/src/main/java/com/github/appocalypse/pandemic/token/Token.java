package com.github.appocalypse.pandemic.token;

import com.google.common.collect.ImmutableList;

public interface Token {
	/**
	 * @param prefix
	 * @return a list of values with that prefix
	 */
	public ImmutableList<String> values(String prefix);
	
}
