package com.github.appocalypse.pandemic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMultimap;

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
	
	@Test
	public void testTopConnectedCities() {
		ImmutableList<City> cities = board.getMostConnectCities();
		assertEquals(2, cities.size());
		assertTrue(cities.contains(Cities.HONG_KONG));
		assertTrue(cities.contains(Cities.ISTANBUL));
	}
	
	@Test
	public void testConnectivityRank() {
		ImmutableMultimap<Integer, City> connectivityRank = board.getCityConnectivityRank();
		assertFalse("Should have no cities that has no connection", connectivityRank.containsKey(0));
		
		System.out.println(connectivityRank.get(1));
		System.out.println(connectivityRank.get(2));
		System.out.println(connectivityRank.get(3));
		System.out.println(connectivityRank.get(4));
		System.out.println(connectivityRank.get(5));
		System.out.println(connectivityRank.get(6));
	}
}
