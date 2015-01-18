package com.github.appocalypse.pandemic.parser;

import com.github.appocalypse.guava.extra.GuavaCollectors;
import com.google.common.collect.ImmutableList;

public class AndTokenEater implements TokenEater {
	
	final private TokenEater andTokenEater;

	public AndTokenEater(ImmutableList<TokenEater> tokenEaters) {
		this.andTokenEater = tokenEaters.stream().reduce(new IdentityTokenEater(), 
				BinaryAndTokenEater::new);
	}
	
	@Override
	public ImmutableList<Cursor> eat(int fromIndex, String value) {
		return andTokenEater.eat(fromIndex, value);
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
		public ImmutableList<Cursor> eat(int fromIndex, String value) {
			ImmutableList<Cursor> leftCursors = leftTokenEater.eat(fromIndex, value);
			
			return leftCursors.stream().flatMap((Cursor leftCursor) -> {
				if (leftCursor.isPartialMatch()) {
					return ImmutableList.of(leftCursor).stream();
				} else {
					ImmutableList<Cursor> rightCursors = rightTokenEater.eat(leftCursor.getEndIndex(), value);
					
					return rightCursors.stream().map((Cursor rightCursor) -> 
						new Cursor(leftCursor.getMatch() + rightCursor.getMatch(),
							leftCursor.getFromIndex(), rightCursor.getEndIndex()));
					
				}
			}).collect(GuavaCollectors.toImmutableList());
		}
	}

}
