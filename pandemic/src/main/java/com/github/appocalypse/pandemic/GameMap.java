package com.github.appocalypse.pandemic;

import com.github.appocalypse.pandemic.Constant.City;
import com.github.appocalypse.pandemic.Constant.RegionColor;
import com.google.common.annotations.VisibleForTesting;
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
		
		Vertex losAngeles = createNewCity(City.LOS_ANGELES, RegionColor.YELLOW, gameMap);
		Vertex mexicoCity = createNewCity(City.MEXICO_CITY, RegionColor.YELLOW, gameMap);
		Vertex miami = createNewCity(City.MIAMI, RegionColor.YELLOW, gameMap);
		Vertex bogota = createNewCity(City.BOGOTA, RegionColor.YELLOW, gameMap);
		Vertex lima = createNewCity(City.LIMA, RegionColor.YELLOW, gameMap);
		Vertex santiago = createNewCity(City.SANTIAGO, RegionColor.YELLOW, gameMap);
		Vertex buenosAires = createNewCity(City.BUENOS_AIRES, RegionColor.YELLOW, gameMap);
		Vertex saoPaulo = createNewCity(City.SAO_PAULO, RegionColor.YELLOW, gameMap);
		Vertex lagos = createNewCity(City.LAGOS, RegionColor.YELLOW, gameMap);
		Vertex kinshasa = createNewCity(City.KINSHASA, RegionColor.YELLOW, gameMap);
		Vertex khartoum = createNewCity(City.KHARTOUM, RegionColor.YELLOW, gameMap);
		Vertex johannesburg = createNewCity(City.JOHANNESBURG, RegionColor.YELLOW, gameMap);
		
		Vertex algiers = createNewCity(City.ALGIERS, RegionColor.BLACK, gameMap);
		Vertex istanbul = createNewCity(City.ISTANBUL, RegionColor.BLACK, gameMap);
		Vertex moscow = createNewCity(City.MOSCOW, RegionColor.BLACK, gameMap);
		Vertex tehran = createNewCity(City.TEHRAN, RegionColor.BLACK, gameMap);
		Vertex baghdad = createNewCity(City.BAGHDAD, RegionColor.BLACK, gameMap);
		Vertex karachi = createNewCity(City.KARACHI, RegionColor.BLACK, gameMap);
		Vertex riyadh = createNewCity(City.RIYADH, RegionColor.BLACK, gameMap);
		Vertex mumbai = createNewCity(City.MUMBAI, RegionColor.BLACK, gameMap);
		Vertex delhi = createNewCity(City.DELHI, RegionColor.BLACK, gameMap);
		Vertex kolkata = createNewCity(City.KOLKATA, RegionColor.BLACK, gameMap);
		Vertex chennai = createNewCity(City.CHENNAI, RegionColor.BLACK, gameMap);
		Vertex cairo = createNewCity(City.CAIRO, RegionColor.BLACK, gameMap);
		
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
		connect(sanFrancisco, losAngeles);
		connect(losAngeles, mexicoCity);
		connect(mexicoCity, miami);
		connect(mexicoCity, chicago);
		connect(miami, atlanta);
		connect(miami, washington);
		connect(miami, bogota);
		connect(bogota, lima);
		connect(santiago, lima);
		connect(bogota, buenosAires);
		connect(bogota, saoPaulo);
		connect(saoPaulo, madrid);
		connect(saoPaulo, lagos);
		connect(lagos, kinshasa);
		connect(lagos, khartoum);
		connect(kinshasa, khartoum);
		connect(johannesburg, kinshasa);
		connect(johannesburg, khartoum);
		connect(khartoum, cairo);
		connect(milan, istanbul);
		connect(paris, algiers);
		connect(stPetersburg, moscow);
		connect(tehran, moscow);
		connect(istanbul, cairo);
		connect(cairo, algiers);
		connect(cairo, baghdad);
		connect(cairo, riyadh);
		connect(istanbul, moscow);
		connect(istanbul, stPetersburg);
		connect(istanbul, baghdad);
		connect(baghdad, riyadh);
		connect(baghdad, karachi);
		connect(baghdad, tehran);
		connect(tehran, karachi);
		connect(tehran, delhi);
		connect(karachi, delhi);
		connect(karachi, mumbai);
		connect(karachi, riyadh);
		connect(delhi, mumbai);
		connect(delhi, kolkata);
		connect(delhi, chennai);
		connect(mumbai, chennai);
		connect(kolkata, chennai);
		
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
