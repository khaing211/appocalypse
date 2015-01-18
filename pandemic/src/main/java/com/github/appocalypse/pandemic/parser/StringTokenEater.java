package com.github.appocalypse.pandemic.parser;

import com.google.common.collect.ImmutableList;

public class StringTokenEater implements TokenEater {

	final private String match;
	
	public StringTokenEater(String match) {
		this.match = match;
	}

	@Override
	public ImmutableList<Cursor> eat(int fromIndex, String value) {
		if (fromIndex < 0) {
			return ImmutableList.of();
		}
		
		if (fromIndex >= value.length()) {
			return ImmutableList.of();
		}
		
		if (fromIndex + match.length() > value.length()) {
			return ImmutableList.of();
		}
		
		for (int i = 0; i < match.length(); i++) {
			if (!charCompareIgnoreCase(value.charAt(fromIndex + i), match.charAt(i))) {
				if (i == 0) {
					return ImmutableList.of();
				} else {
					return ImmutableList.of(new Cursor(match, fromIndex, fromIndex + i));
				}
			}
		}
		
		return ImmutableList.of(new Cursor(match, fromIndex, fromIndex + match.length()));
	}
	
	private boolean charCompareIgnoreCase(char left, char right) {
		return Character.toLowerCase(left) == Character.toLowerCase(right);

	}

}
