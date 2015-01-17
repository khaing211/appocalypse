package com.github.appocalypse.pandemic.parser;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.appocalypse.pandemic.parser.TokenEater.Cursor;
import com.google.common.collect.ImmutableList;

public class StringTokenEaterTest {

	@Test
	public void testMatchEmptyString() {
		TokenEater tokenEater = new StringTokenEater("abc");
		ImmutableList<Cursor> cursors = tokenEater.eat(0, "");
		
		assertEquals(0, cursors.size());
	}
	
	@Test
	public void testEmptyStringIsMatch() {
		TokenEater tokenEater = new StringTokenEater("");
		ImmutableList<Cursor> cursors = tokenEater.eat(0, "abc");
		
		assertEquals(1, cursors.size());
		
		Cursor cursor = cursors.get(0);
		assertEquals(0, cursor.getFromIndex());
		assertEquals(0, cursor.getEndIndex());
		assertEquals("", cursor.getMatch());
		assertFalse(cursor.isPartialMatch());
	}
	
	@Test
	public void testNonMatch() {
		TokenEater tokenEater = new StringTokenEater("abc");
		ImmutableList<Cursor> cursors = tokenEater.eat(0, " abc");
		
		assertEquals(0, cursors.size());
	}
	
	@Test
	public void testFullMatch() {
		TokenEater tokenEater = new StringTokenEater("abc");
		ImmutableList<Cursor> cursors = tokenEater.eat(0, "abc");
		
		assertEquals(1, cursors.size());
		
		Cursor cursor = cursors.get(0);
		assertEquals(0, cursor.getFromIndex());
		assertEquals(3, cursor.getEndIndex());
		assertEquals("abc", cursor.getMatch());
		assertFalse(cursor.isPartialMatch());
	}
	
	@Test
	public void testFullMatchWithTrailing() {
		TokenEater tokenEater = new StringTokenEater("abc");
		ImmutableList<Cursor> cursors = tokenEater.eat(0, "abcdfg");
		
		assertEquals(1, cursors.size());
		
		Cursor cursor = cursors.get(0);
		assertEquals(0, cursor.getFromIndex());
		assertEquals(3, cursor.getEndIndex());
		assertEquals("abc", cursor.getMatch());
		assertFalse(cursor.isPartialMatch());
	}
	
	@Test
	public void testTwoStringTokenEater() {
		String haystack = "abcdfg";
		TokenEater tokenEater1 = new StringTokenEater("abc");
		TokenEater tokenEater2 = new StringTokenEater("d");
		TokenEater tokenEater3 = new StringTokenEater("fg");

		ImmutableList<Cursor> cursors1 = tokenEater1.eat(0, haystack);
		
		assertEquals(1, cursors1.size());
		
		Cursor cursor1 = cursors1.get(0);
		assertEquals(0, cursor1.getFromIndex());
		assertEquals(3, cursor1.getEndIndex());
		assertEquals("abc", cursor1.getMatch());
		assertFalse(cursor1.isPartialMatch());
		
		ImmutableList<Cursor> cursors2 = tokenEater2.eat(cursor1.getEndIndex(), haystack);
		
		assertEquals(1, cursors2.size());
		
		Cursor cursor2 = cursors2.get(0);
		assertEquals(3, cursor2.getFromIndex());
		assertEquals(4, cursor2.getEndIndex());
		assertEquals("d", cursor2.getMatch());
		assertFalse(cursor2.isPartialMatch());
		
		
		ImmutableList<Cursor> cursors3 = tokenEater3.eat(cursor2.getEndIndex(), haystack);
		
		assertEquals(1, cursors3.size());
		
		Cursor cursor3 = cursors3.get(0);
		assertEquals(4, cursor3.getFromIndex());
		assertEquals(6, cursor3.getEndIndex());
		assertEquals("fg", cursor3.getMatch());
		assertFalse(cursor3.isPartialMatch());
	}

	@Test
	public void testPartialMatch() {
		TokenEater tokenEater = new StringTokenEater("abcgh");
		ImmutableList<Cursor> cursors = tokenEater.eat(0, "abcdfg");
		
		assertEquals(1, cursors.size());
		
		Cursor cursor = cursors.get(0);
		assertEquals(0, cursor.getFromIndex());
		assertEquals(3, cursor.getEndIndex());
		assertEquals("abcgh", cursor.getMatch());
		assertTrue(cursor.isPartialMatch());
	}
}
