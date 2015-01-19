package com.github.appocalypse.pandemic.parser;

import com.google.common.collect.ImmutableList;

public class StringTokenEater implements TokenEater {

	final private String match;
	
	public StringTokenEater(String match) {
		this.match = match;
	}

	@Override
	public ImmutableList<Cursor> eat(final int fromIndex, final String value, final boolean includePartialMatchWhenNoMatch) {
		if (fromIndex < 0) {
			return ImmutableList.of();
		}
		
		if (fromIndex >= value.length()) {
			if (includePartialMatchWhenNoMatch) {
				return ImmutableList.of(new Cursor(match, true, fromIndex, fromIndex));
			} else {
				return ImmutableList.of();
			}
		}
		
		
		for (int i = 0; i < match.length() && fromIndex + i < value.length(); i++) {
			if (value.charAt(fromIndex + i) != match.charAt(i)) {
				if (includePartialMatchWhenNoMatch) {
					final int endIndex = fromIndex + i;
					return ImmutableList.of(new Cursor(match, true, fromIndex, endIndex));
				} else {
					return ImmutableList.of();
				}
			}
		}
		
		final int endIndex = Math.min(fromIndex + match.length(), value.length());
		final boolean partialMatch = fromIndex + match.length() > value.length();
		return ImmutableList.of(new Cursor(match, partialMatch, fromIndex, endIndex));
	}
	
	@Override
	public String toString() {
		return "StringTokenEater [match=" + match + "]";
	}
}
