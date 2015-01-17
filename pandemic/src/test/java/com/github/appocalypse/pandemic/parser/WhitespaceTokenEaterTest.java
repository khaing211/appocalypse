package com.github.appocalypse.pandemic.parser;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.appocalypse.pandemic.parser.TokenEater.Cursor;
import com.google.common.collect.ImmutableList;

public class WhitespaceTokenEaterTest {

	@Test
	public void testNonMatch() {
		TokenEater tokenEater = new WhitespaceTokenEater();
		
		ImmutableList<Cursor> cursors = tokenEater.eat(0, "abc");
		
		assertEquals(0, cursors.size());		
	}

}
