package com.github.appocalypse.pandemic.parser;

import com.google.common.collect.ImmutableList;

public class CaseInsensitiveStringTokenEater implements TokenEater {
	
	final private TokenEater tokenEater;
	
	public CaseInsensitiveStringTokenEater(String match) {
		tokenEater = TokenEaters.or(new StringTokenEater(match.toLowerCase()), new StringTokenEater(match.toUpperCase()));
	}
	
	@Override
	public ImmutableList<Cursor> eat(int fromIndex, String value,
			boolean includePartialMatchWhenNoMatch) {
		return tokenEater.eat(fromIndex, value, includePartialMatchWhenNoMatch);
	}

}
