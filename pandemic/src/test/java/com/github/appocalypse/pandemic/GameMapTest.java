package com.github.appocalypse.pandemic;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameMapTest {

	@Test
	public void test() {
		GameMap gameMap = GameMap.newGameMap();
		
		assertNotNull(gameMap.getInternalGameMap());
	}

}
