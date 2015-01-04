package com.github.appocalypse.pandemic;

import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.github.appocalypse.pandemic.Constant.City;
import com.github.appocalypse.pandemic.Constant.RegionColor;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableMap;
import com.tinkerpop.gremlin.process.T;
import com.tinkerpop.gremlin.structure.Graph;
import com.tinkerpop.gremlin.structure.Vertex;
import com.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;

public class GameMap {
	final private Graph internalGameMap;
	
	private GameMap(Graph gameMap) {
		this.internalGameMap = gameMap;
	}
	
	public int getResearchStationCount() {
		// TODO:
		return 0;
	}
	
	static public GameMap newGameMap() {
		final Graph gameMap = TinkerGraph.open();

		Vertex atlanta = createNewCity(City.ATLANTA, RegionColor.BLUE, gameMap);
		Vertex chicago = createNewCity(City.CHICAGO, RegionColor.BLUE, gameMap);
		Vertex montreal = createNewCity(City.MONTREAL, RegionColor.BLUE, gameMap);
		Vertex washington = createNewCity(City.WASHINGTON, RegionColor.BLUE, gameMap);
		Vertex sanFrancisco = createNewCity(City.SAN_FRANCISCO, RegionColor.BLUE, gameMap);
		Vertex newYork = createNewCity(City.NEW_YORK, RegionColor.BLUE, gameMap);
		Vertex london = createNewCity(City.LONDON, RegionColor.BLUE, gameMap);
		Vertex essen = createNewCity(City.ESSEN, RegionColor.BLUE, gameMap);
		Vertex stPetersburg = createNewCity(City.ST_PETERSBURG, RegionColor.BLUE, gameMap);
		Vertex paris = createNewCity(City.PARIS, RegionColor.BLUE, gameMap);
		Vertex milan = createNewCity(City.MILAN, RegionColor.BLUE, gameMap);
		Vertex madrid = createNewCity(City.MADRID, RegionColor.BLUE, gameMap);
		
		// connect cities together
		connect(atlanta, washington);
		connect(washington, montreal);
		connect(chicago, montreal);
		connect(chicago, atlanta);
		connect(sanFrancisco, chicago);
		connect(newYork, montreal);
		connect(newYork, washington);
		connect(newYork, london);
		connect(newYork, madrid);
		connect(london, paris);
		connect(london, madrid);
		connect(paris, essen);
		connect(paris, milan);
		connect(stPetersburg, essen);
		
		// add a research station at atlanta
		atlanta.property(Constant.RESEARCH_STATION, Boolean.TRUE);
		
		return new GameMap(gameMap);
	}
	
	static private void connect(Vertex cityA, Vertex cityB) {
		cityA.addEdge(Constant.ADJACENT, cityB);
		cityB.addEdge(Constant.ADJACENT, cityA);
	}
	
	static private Vertex createNewCity(String city, String regionColor, Graph gameMap) {
		return gameMap.addVertex(T.label, city, 
				Constant.RED_DISEASE, Integer.valueOf(0), 
				Constant.BLACK_DISEASE, Integer.valueOf(0),
				Constant.BLUE_DISEASE, Integer.valueOf(0), 
				Constant.YELLOW_DISASE, Integer.valueOf(0),
				Constant.REGION, regionColor,
				Constant.RESEARCH_STATION, Boolean.FALSE);
	}

	@VisibleForTesting
	Graph getInternalGameMap() {
		return internalGameMap;
	}
}
