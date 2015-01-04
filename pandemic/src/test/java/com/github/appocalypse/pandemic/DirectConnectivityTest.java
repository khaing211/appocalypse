package com.github.appocalypse.pandemic;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.github.appocalypse.pandemic.Constant.City;

public class DirectConnectivityTest {

	private GameMap gameMap = GameMap.newGameMap();
	
	@Test
	public void testAtlanta() {
		List<String> neighborCities = gameMap.getNeighborCities(City.ATLANTA);
		assertEquals("Atlanta should have 3 neighbors", 3, neighborCities.size());
		assertTrue("Chicago should be neighbor of Atlanta", neighborCities.contains(City.CHICAGO));
		assertTrue("Washington should be neighbor of Atlanta", neighborCities.contains(City.WASHINGTON));
		assertTrue("Miami should be neighbor of Atlanta", neighborCities.contains(City.MIAMI));
	}

	@Test
	public void testMiami() {
		
	}
	
}
