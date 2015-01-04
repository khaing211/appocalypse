package com.github.appocalypse.pandemic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.github.appocalypse.pandemic.Constant.City;

public class DirectConnectivityTest {

	private GameMap gameMap = GameMap.newGameMap();
	
	private void assertNeighbors(String origin, String... neighbors) {
		List<String> neighborCities = gameMap.getNeighborCities(origin);
		assertEquals(origin + " should have " + neighbors.length + " neighbors, but actual neighbor cities: " + neighborCities, 
				neighbors.length, neighborCities.size());
		
		for (String neighbor : neighbors) {
			assertTrue(neighbor + " should be neighbor of " + origin, neighborCities.contains(neighbor));
		}
	}
	
	@Test
	public void testAtlanta() {
		assertNeighbors(City.ATLANTA, City.CHICAGO, City.WASHINGTON, City.MIAMI);
	}

	@Test
	public void testMiami() {
		assertNeighbors(City.MIAMI, City.ATLANTA, City.MEXICO_CITY, City.WASHINGTON, City.BOGOTA);
	}
	
	@Test
	public void testWashington() {
		assertNeighbors(City.WASHINGTON, City.ATLANTA, City.MIAMI, City.MONTREAL, City.NEW_YORK);
	}
	
	@Test
	public void testChicago() {
		assertNeighbors(City.CHICAGO, City.MEXICO_CITY, City.LOS_ANGELES, City.MONTREAL, City.ATLANTA, City.SAN_FRANCISCO);
	}
	
	@Test
	public void testLosAngeles() {
		assertNeighbors(City.LOS_ANGELES, City.CHICAGO, City.SAN_FRANCISCO, City.MEXICO_CITY, City.SYDNEY);
	}
	
	@Test
	public void testMontreal() {
		assertNeighbors(City.MONTREAL, City.CHICAGO, City.WASHINGTON, City.NEW_YORK);
	}
}
