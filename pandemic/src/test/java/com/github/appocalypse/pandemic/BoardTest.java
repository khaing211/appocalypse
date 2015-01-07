package com.github.appocalypse.pandemic;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BoardTest {

	private Board board = Board.createPandemicBoard();
	
	@Test
	public void testTotalNumberOfCities() {
		assertEquals("Total number cities should be 48", 48, board.getCitiesCount());
	}
	
	@Test
	public void testNumberOfBlueCities() {
		assertEquals("Number of blue cities should be 12", 12, board.getCitiesCount(RegionColor.BLUE));
	}

	@Test
	public void testNumberOfYellowCities() {
		assertEquals("Number of yellow cities should be 12", 12, board.getCitiesCount(RegionColor.YELLOW));
	}
	
	@Test
	public void testNumberOfBlackCities() {
		assertEquals("Number of black cities should be 12", 12, board.getCitiesCount(RegionColor.BLACK));
	}
	
	@Test
	public void testNumberOfRedCities() {
		assertEquals("Number of red cities should be 12", 12, board.getCitiesCount(RegionColor.RED));
	}
}
