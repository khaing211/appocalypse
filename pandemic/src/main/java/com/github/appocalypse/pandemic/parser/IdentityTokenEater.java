package com.github.appocalypse.pandemic.parser;

import com.google.common.collect.ImmutableList;

public class IdentityTokenEater implements TokenEater {

	/**
	 * Match nothing
	 */
	@Override
	public ImmutableList<Cursor> eat(int fromIndex, String value) {
		return ImmutableList.of(new Cursor("", fromIndex, fromIndex));
	}
}
