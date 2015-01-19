package com.github.appocalypse.pandemic.parser;

import com.google.common.collect.ImmutableList;

public enum WhitespaceTokenEater implements TokenEater {
	INSTANCE;
	
	@Override
	public ImmutableList<Cursor> eat(int fromIndex, String value, boolean includePartialMatchWhenNoMatch) {
		if (fromIndex < 0) {
			return ImmutableList.of();
		}
		
		if (fromIndex >= value.length()) {
			return ImmutableList.of();
		}
		
		for (int endIndex = fromIndex; endIndex < value.length(); endIndex++) {
			if (!Character.isWhitespace(value.charAt(endIndex))) {
				if (endIndex == fromIndex) {
					if (includePartialMatchWhenNoMatch) {
						// return a partial match
						return ImmutableList.of(new Cursor(" ", true, fromIndex, endIndex));
					} else {
						return ImmutableList.of();
					}
				} else {
					return ImmutableList.of(new Cursor(value.substring(fromIndex, endIndex), false, fromIndex, endIndex));
				}
			}
		}
		
		return ImmutableList.of(new Cursor(value.substring(fromIndex), false, fromIndex, value.length()));
	}
}
