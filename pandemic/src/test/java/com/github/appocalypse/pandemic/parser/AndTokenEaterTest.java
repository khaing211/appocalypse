package com.github.appocalypse.pandemic.parser;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.appocalypse.pandemic.parser.TokenEater.Cursor;
import com.google.common.collect.ImmutableList;

public class AndTokenEaterTest {

	@Test
	public void testMatch() {
		TokenEater whitespaceTokenEater = WhitespaceTokenEater.INSTANCE;
		TokenEater abcTokenEater = new StringTokenEater("abc");
		
		TokenEater andTokenEater = new AndTokenEater(ImmutableList.of(whitespaceTokenEater, abcTokenEater));
		
		ImmutableList<Cursor> cursors = andTokenEater.eat(0, "  abc  abc  abc  abc  abc");
		
		assertEquals(1, cursors.size());	
		Cursor cursor = cursors.get(0);
		assertEquals(2, cursor.getFromIndex());
		assertEquals(5, cursor.getEndIndex());
		assertEquals("abc", cursor.getMatch());
		assertFalse(cursor.isPartialMatch());
		assertNotNull(cursor.getPrevCursor());
		
		Cursor combineCursor = cursor.combineCursors();
		assertEquals(0, combineCursor.getFromIndex());
		assertEquals(5, combineCursor.getEndIndex());
		assertEquals("  abc", combineCursor.getMatch());
		assertFalse(combineCursor.isPartialMatch());
		assertNull(combineCursor.getPrevCursor());
		
	}
	
	@Test
	public void testPartialMatch() {
		TokenEater whitespaceTokenEater = WhitespaceTokenEater.INSTANCE;
		TokenEater abcTokenEater = new StringTokenEater("abc");
		
		TokenEater andTokenEater = new AndTokenEater(ImmutableList.of(abcTokenEater, whitespaceTokenEater));
		
		ImmutableList<Cursor> cursors = andTokenEater.eat(0, "abcabc");
		
		assertEquals(1, cursors.size());	
		Cursor cursor = cursors.get(0);
		Cursor combineCursor = cursor.combineCursors();
		assertEquals(0, combineCursor.getFromIndex());
		assertEquals(3, combineCursor.getEndIndex());
		assertEquals("abc ", combineCursor.getMatch());
		assertTrue(combineCursor.isPartialMatch());
		assertNull(combineCursor.getPrevCursor());
		
	}
}
