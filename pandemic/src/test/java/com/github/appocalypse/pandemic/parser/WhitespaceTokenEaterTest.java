package com.github.appocalypse.pandemic.parser;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.appocalypse.pandemic.parser.TokenEater.Cursor;
import com.google.common.collect.ImmutableList;

public class WhitespaceTokenEaterTest {

	@Test
	public void testNonMatch() {
		TokenEater tokenEater = WhitespaceTokenEater.INSTANCE;
		
		ImmutableList<Cursor> cursors = tokenEater.eat(0, "abc");
		
		assertEquals(0, cursors.size());
	}
	
	@Test
	public void testOneWhiteSpace() {
		TokenEater tokenEater = WhitespaceTokenEater.INSTANCE;
		
		ImmutableList<Cursor> cursors = tokenEater.eat(0, " bc");
		
		assertEquals(1, cursors.size());		
		Cursor cursor = cursors.get(0);
		assertEquals(0, cursor.getFromIndex());
		assertEquals(1, cursor.getEndIndex());
		assertEquals(" ", cursor.getMatch());
		assertFalse(cursor.isPartialMatch());
	}
	
	@Test
	public void testTwoWhiteSpace() {
		TokenEater tokenEater = WhitespaceTokenEater.INSTANCE;
		
		ImmutableList<Cursor> cursors = tokenEater.eat(0, "  c");
		
		assertEquals(1, cursors.size());		
		Cursor cursor = cursors.get(0);
		assertEquals(0, cursor.getFromIndex());
		assertEquals(2, cursor.getEndIndex());
		assertEquals("  ", cursor.getMatch());
		assertFalse(cursor.isPartialMatch());
	}

	@Test
	public void testTab() {
		TokenEater tokenEater = WhitespaceTokenEater.INSTANCE;
		
		ImmutableList<Cursor> cursors = tokenEater.eat(0, "  \tc");
		
		assertEquals(1, cursors.size());		
		Cursor cursor = cursors.get(0);
		assertEquals(0, cursor.getFromIndex());
		assertEquals(3, cursor.getEndIndex());
		assertEquals("  \t", cursor.getMatch());
		assertFalse(cursor.isPartialMatch());
	}
	
	@Test
	public void testNewline() {
		TokenEater tokenEater = WhitespaceTokenEater.INSTANCE;
		
		ImmutableList<Cursor> cursors = tokenEater.eat(0, "  \t\nc");
		
		assertEquals(1, cursors.size());		
		Cursor cursor = cursors.get(0);
		assertEquals(0, cursor.getFromIndex());
		assertEquals(4, cursor.getEndIndex());
		assertEquals("  \t\n", cursor.getMatch());
		assertFalse(cursor.isPartialMatch());
	}
}
