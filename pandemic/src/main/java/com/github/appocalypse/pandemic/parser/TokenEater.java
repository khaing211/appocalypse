package com.github.appocalypse.pandemic.parser;

import com.google.common.collect.ImmutableList;

public interface TokenEater {
	/**
	 * @param fromIndex to start inclusive
	 * @param value to parse
	 * @return cursor contains full match or not and index
	 */
	public ImmutableList<Cursor> eat(int fromIndex, String value);
	
	
	public static class Cursor {
		final private String match;
		final private int fromIndex; // inclusive
		final private int endIndex; // exclusive
		
		public Cursor(String match, int fromIndex, int endIndex) {
			this.match = match;
			this.fromIndex = fromIndex;
			this.endIndex = endIndex;
		}

		public String getMatch() {
			return match;
		}


		public int getFromIndex() {
			return fromIndex;
		}


		public int getEndIndex() {
			return endIndex;
		}
		
		public boolean isPartialMatch() {
			return match.length() != endIndex - fromIndex;
		}
	}
}