package com.github.appocalypse.pandemic.parser;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.appocalypse.pandemic.Cities;
import com.github.appocalypse.pandemic.parser.TokenEater.Cursor;
import com.google.common.collect.ImmutableList;

public class CitesTokenEaterTest {

	@Test
	public void test() {
		TokenEater citesTokenEater = CitesTokenEater.INSTANCE;
		
		ImmutableList<Cursor> cursors = citesTokenEater.eat(0, Cities.ATLANTA.getName());

		System.out.println(cursors);

		assertEquals(2, cursors.size());	
		
		Cursor cursor = cursors.get(0);
		assertEquals(0, cursor.getFromIndex());
		assertEquals(Cities.ATLANTA.getName().length(), cursor.getEndIndex());
		assertEquals(Cities.ATLANTA.getName(), cursor.getMatch());
		assertFalse(cursor.isPartialMatch());
	}

}
