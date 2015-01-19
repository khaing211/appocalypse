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
		final private Cursor prevCursor;
		final private String match;
		final private int fromIndex; // inclusive
		final private int endIndex; // exclusive
		
		public Cursor(String match, int fromIndex, int endIndex) {
			this(null, match, fromIndex, endIndex);
		}
		
		public Cursor(Cursor prevCursor, String match, int fromIndex, int endIndex) {
			this.prevCursor = prevCursor;
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
		
		public Cursor getPrevCursor() {
			return prevCursor;
		}
		
		/**
		 * @return a cursor that combine all results from current to prev
		 * 		till prevCursor is null
		 */
		public Cursor combineCursors() {
			int curEndIndex = this.endIndex;
			int curFromIndex = this.fromIndex;
			StringBuilder curMatch = new StringBuilder(100);
			curMatch.append(match);
			
			Cursor curPrevCursor = prevCursor;
			while (curPrevCursor != null) {
				curFromIndex = curPrevCursor.getFromIndex();
				curMatch.insert(0, curPrevCursor.getMatch());
				curPrevCursor = curPrevCursor.getPrevCursor();
			}
			
			return new Cursor(curMatch.toString(), curFromIndex, curEndIndex);
		}
		
		@Override
		public String toString() {
			return "Cursor [prevCursor=" + prevCursor + ", match=" + match
					+ ", fromIndex=" + fromIndex + ", endIndex=" + endIndex
					+ "]";
		}
	}
}