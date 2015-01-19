package com.github.appocalypse.pandemic.parser;

import com.google.common.collect.ImmutableList;

public interface TokenEater {
	public default ImmutableList<Cursor> eat(int fromIndex, String value) {
		return eat(fromIndex, value, false);
	}
	
	public ImmutableList<Cursor> eat(int fromIndex, String value, boolean includePartialMatchWhenNoMatch);
	
	
	public static class Cursor {
		final private Cursor prevCursor;
		final private String match;
		final private boolean partialMatch;
		final private int fromIndex; // inclusive
		final private int endIndex; // exclusive
		
		public Cursor(String match, boolean partialMatch, int fromIndex, int endIndex) {
			this(null, match, partialMatch, fromIndex, endIndex);
		}
		
		public Cursor(Cursor prevCursor, String match, boolean partialMatch, int fromIndex, int endIndex) {
			this.prevCursor = prevCursor;
			this.match = match;
			this.partialMatch = partialMatch;
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
			return partialMatch;
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
			boolean curPartialMatch = this.partialMatch;
			StringBuilder curMatch = new StringBuilder(100);
			curMatch.append(match);
			
			Cursor curPrevCursor = prevCursor;
			while (curPrevCursor != null) {
				curFromIndex = curPrevCursor.getFromIndex();
				curMatch.insert(0, curPrevCursor.getMatch());
				curPrevCursor = curPrevCursor.getPrevCursor();
			}
			
			return new Cursor(curMatch.toString(), curPartialMatch, curFromIndex, curEndIndex);
		}
		
		@Override
		public String toString() {
			return "Cursor [prevCursor=" + prevCursor + ", match=" + match
					+ ", partialMatch=" + partialMatch + ", fromIndex="
					+ fromIndex + ", endIndex=" + endIndex + "]";
		}
	}
}