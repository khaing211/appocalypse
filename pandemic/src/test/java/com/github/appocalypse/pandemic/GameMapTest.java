package com.github.appocalypse.pandemic;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.appocalypse.pandemic.Constant.CityName;
import com.github.appocalypse.pandemic.Constant.RegionColor;

public class GameMapTest {

	private GameMap gameMap = GameMap.newGameMap();
	
	@Test
	public void testInternalGameMap() {
		assertNotNull("Internal game map should not be null", gameMap.getInternalGameMap());
	}

	@Test
	public void testInitialNumberOfResearchStation() {
		assertEquals("Initial number of research station should be 1", 1, gameMap.getResearchStationCount());
	}

	@Test
	public void testTotalNumberOfCities() {
		assertEquals("Total number cities should be 48", 48, gameMap.getCitiesCount());
	}
	
	@Test
	public void testNumberOfBlueCities() {
		assertEquals("Number of blue cities should be 12", 12, gameMap.getCitiesCount(RegionColor.BLUE));
	}

	@Test
	public void testNumberOfYellowCities() {
		assertEquals("Number of yellow cities should be 12", 12, gameMap.getCitiesCount(RegionColor.YELLOW));
	}
	
	@Test
	public void testNumberOfBlackCities() {
		assertEquals("Number of black cities should be 12", 12, gameMap.getCitiesCount(RegionColor.BLACK));
	}
	
	@Test
	public void testNumberOfRedCities() {
		assertEquals("Number of red cities should be 12", 12, gameMap.getCitiesCount(RegionColor.RED));
	}
	
	@Test
	public void testHasResearchStation() {
		assertTrue("Atlanta should have a research station", gameMap.hasResearchStation(CityName.ATLANTA));
	}
}
