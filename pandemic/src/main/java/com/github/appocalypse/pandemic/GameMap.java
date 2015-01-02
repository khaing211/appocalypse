package com.github.appocalypse.pandemic;

import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.github.appocalypse.pandemic.Constant.City;
import com.github.appocalypse.pandemic.Constant.RegionColor;
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
		
		
		// connect cities together
		connect(atlanta, washington);
		connect(washington, montreal);
		connect(chicago, montreal);
		connect(chicago, atlanta);
		
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
}
