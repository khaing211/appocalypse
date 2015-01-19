package com.github.appocalypse.pandemic.parser;

import com.google.common.collect.ImmutableList;

public enum WhitespaceTokenEater implements TokenEater {
	INSTANCE;
	
	@Override
	public ImmutableList<Cursor> eat(int fromIndex, String value) {
		if (fromIndex < 0) {
			return ImmutableList.of();
		}
		
		if (fromIndex >= value.length()) {
			return ImmutableList.of();
		}
		
		for (int endIndex = fromIndex; endIndex < value.length(); endIndex++) {
			if (!Character.isWhitespace(value.charAt(endIndex))) {
				if (endIndex == fromIndex) {
					// return a partial match
					return ImmutableList.of(new Cursor(" ", fromIndex, endIndex));
				} else {
					return ImmutableList.of(new Cursor(value.substring(fromIndex, endIndex), fromIndex, endIndex));
				}
			}
		}
		
		return ImmutableList.of(new Cursor(value.substring(fromIndex), fromIndex, value.length()));
	}
}
