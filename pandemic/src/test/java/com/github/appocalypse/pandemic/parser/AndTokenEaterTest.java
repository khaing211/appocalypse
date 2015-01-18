package com.github.appocalypse.pandemic.parser;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.appocalypse.pandemic.parser.TokenEater.Cursor;
import com.google.common.collect.ImmutableList;

public class AndTokenEaterTest {

	@Test
	public void test() {
		TokenEater whitespaceTokenEater = new WhitespaceTokenEater();
		TokenEater abcTokenEater = new StringTokenEater("abc");
		
		TokenEater andTokenEater = new AndTokenEater(ImmutableList.of(whitespaceTokenEater, abcTokenEater));
		
		ImmutableList<Cursor> cursors = andTokenEater.eat(0, "  abc  abc  abc  abc  abc");
		
		assertEquals(1, cursors.size());	
		Cursor cursor = cursors.get(0);
		assertEquals(0, cursor.getFromIndex());
		assertEquals(5, cursor.getEndIndex());
		assertEquals("  abc", cursor.getMatch());
		assertFalse(cursor.isPartialMatch());
	}

}
