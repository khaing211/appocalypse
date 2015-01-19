package com.github.appocalypse.pandemic.parser;

import java.util.Optional;
import java.util.stream.Stream;

import com.github.appocalypse.guava.extra.GuavaCollectors;
import com.google.common.collect.ImmutableList;

public class AndTokenEater implements TokenEater {
	
	final private Optional<TokenEater> andTokenEater;

	public AndTokenEater(ImmutableList<TokenEater> tokenEaters) {
		this.andTokenEater = tokenEaters.stream().reduce(BinaryAndTokenEater::new);
	}
	
	@Override
	public ImmutableList<Cursor> eat(int fromIndex, String value, boolean includePartialMatchWhenNoMatch) {
		return andTokenEater.map(it ->it.eat(fromIndex, value, includePartialMatchWhenNoMatch)).orElse(ImmutableList.of());
	}
	
	private static class BinaryAndTokenEater implements TokenEater {
		
		final private TokenEater leftTokenEater;
		final private TokenEater rightTokenEater;
		
		public BinaryAndTokenEater(TokenEater leftTokenEater,
				TokenEater rightTokenEater) {
			this.leftTokenEater = leftTokenEater;
			this.rightTokenEater = rightTokenEater;
		}
		
		@Override
		public ImmutableList<Cursor> eat(int fromIndex, String value, boolean includePartialMatchWhenNoMatch) {
			ImmutableList<Cursor> leftCursors = leftTokenEater.eat(fromIndex, value, includePartialMatchWhenNoMatch);
			
			return leftCursors.stream().flatMap((Cursor leftCursor) -> {
				if (leftCursor.isPartialMatch()) {
					return Stream.of(leftCursor);
				} else {
					ImmutableList<Cursor> rightCursors = rightTokenEater.eat(leftCursor.getEndIndex(), 
							value, includePartialMatchWhenNoMatch);
					
					if (rightCursors.isEmpty()) {
						if (includePartialMatchWhenNoMatch) {
							return Stream.of(new Cursor(leftCursor.getPrevCursor(), leftCursor.getMatch(), true,
									leftCursor.getFromIndex(), leftCursor.getEndIndex()));
						} else {
							return Stream.of();
						}
					} else {
						return rightCursors.stream().map((Cursor rightCursor) -> 
							new Cursor(leftCursor, rightCursor.getMatch(), rightCursor.isPartialMatch(),
									rightCursor.getFromIndex(), rightCursor.getEndIndex()));
					}
					
				}
			}).collect(GuavaCollectors.toImmutableList());
		}
	}
}
