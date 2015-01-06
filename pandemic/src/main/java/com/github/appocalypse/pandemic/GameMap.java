package com.github.appocalypse.pandemic;

import java.util.List;

import com.github.appocalypse.pandemic.Constant.CityName;
import com.github.appocalypse.pandemic.Constant.RegionColor;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Lists;
import com.tinkerpop.gremlin.process.T;
import com.tinkerpop.gremlin.structure.Graph;
import com.tinkerpop.gremlin.structure.Vertex;
import com.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;

public class GameMap {
	final private Graph internalGameMap;
	
	private GameMap(Graph gameMap) {
		this.internalGameMap = gameMap;
	}
	
	public long getResearchStationCount() {
		return internalGameMap.V().has(Constant.RESEARCH_STATION, Boolean.TRUE).count().next();
	}
	
	public long getCitiesCount() {
		return internalGameMap.V().count().next();
	}
	
	public long getCitiesCount(String color) {
		return internalGameMap.V().has(Constant.REGION, color).count().next();
	}
	
	public List<String> getNeighborCities(String city) {
		List<String> neighborCities = Lists.newArrayList();

		internalGameMap.v(city).outE(Constant.ADJACENT).inV().dedup().forEachRemaining(v -> neighborCities.add(v.label()));

		return neighborCities;
	}
	
	public void placeResearchStation(String city) {
		internalGameMap.v(city).value(Constant.RESEARCH_STATION , Boolean.TRUE);
	}
	
	public boolean hasResearchStation(String city) {
		return internalGameMap.v(city).<Boolean>value(Constant.RESEARCH_STATION);
	}
	
	static public GameMap newGameMap() {
		final Graph gameMap = TinkerGraph.open();

		Vertex atlanta = createNewCity(CityName.ATLANTA, RegionColor.BLUE, gameMap);
		Vertex chicago = createNewCity(CityName.CHICAGO, RegionColor.BLUE, gameMap);
		Vertex montreal = createNewCity(CityName.MONTREAL, RegionColor.BLUE, gameMap);
		Vertex washington = createNewCity(CityName.WASHINGTON, RegionColor.BLUE, gameMap);
		Vertex sanFrancisco = createNewCity(CityName.SAN_FRANCISCO, RegionColor.BLUE, gameMap);
		Vertex newYork = createNewCity(CityName.NEW_YORK, RegionColor.BLUE, gameMap);
		Vertex london = createNewCity(CityName.LONDON, RegionColor.BLUE, gameMap);
		Vertex essen = createNewCity(CityName.ESSEN, RegionColor.BLUE, gameMap);
		Vertex stPetersburg = createNewCity(CityName.ST_PETERSBURG, RegionColor.BLUE, gameMap);
		Vertex paris = createNewCity(CityName.PARIS, RegionColor.BLUE, gameMap);
		Vertex milan = createNewCity(CityName.MILAN, RegionColor.BLUE, gameMap);
		Vertex madrid = createNewCity(CityName.MADRID, RegionColor.BLUE, gameMap);
		
		Vertex losAngeles = createNewCity(CityName.LOS_ANGELES, RegionColor.YELLOW, gameMap);
		Vertex mexicoCity = createNewCity(CityName.MEXICO_CITY, RegionColor.YELLOW, gameMap);
		Vertex miami = createNewCity(CityName.MIAMI, RegionColor.YELLOW, gameMap);
		Vertex bogota = createNewCity(CityName.BOGOTA, RegionColor.YELLOW, gameMap);
		Vertex lima = createNewCity(CityName.LIMA, RegionColor.YELLOW, gameMap);
		Vertex santiago = createNewCity(CityName.SANTIAGO, RegionColor.YELLOW, gameMap);
		Vertex buenosAires = createNewCity(CityName.BUENOS_AIRES, RegionColor.YELLOW, gameMap);
		Vertex saoPaulo = createNewCity(CityName.SAO_PAULO, RegionColor.YELLOW, gameMap);
		Vertex lagos = createNewCity(CityName.LAGOS, RegionColor.YELLOW, gameMap);
		Vertex kinshasa = createNewCity(CityName.KINSHASA, RegionColor.YELLOW, gameMap);
		Vertex khartoum = createNewCity(CityName.KHARTOUM, RegionColor.YELLOW, gameMap);
		Vertex johannesburg = createNewCity(CityName.JOHANNESBURG, RegionColor.YELLOW, gameMap);
		
		Vertex algiers = createNewCity(CityName.ALGIERS, RegionColor.BLACK, gameMap);
		Vertex istanbul = createNewCity(CityName.ISTANBUL, RegionColor.BLACK, gameMap);
		Vertex moscow = createNewCity(CityName.MOSCOW, RegionColor.BLACK, gameMap);
		Vertex tehran = createNewCity(CityName.TEHRAN, RegionColor.BLACK, gameMap);
		Vertex baghdad = createNewCity(CityName.BAGHDAD, RegionColor.BLACK, gameMap);
		Vertex karachi = createNewCity(CityName.KARACHI, RegionColor.BLACK, gameMap);
		Vertex riyadh = createNewCity(CityName.RIYADH, RegionColor.BLACK, gameMap);
		Vertex mumbai = createNewCity(CityName.MUMBAI, RegionColor.BLACK, gameMap);
		Vertex delhi = createNewCity(CityName.DELHI, RegionColor.BLACK, gameMap);
		Vertex kolkata = createNewCity(CityName.KOLKATA, RegionColor.BLACK, gameMap);
		Vertex chennai = createNewCity(CityName.CHENNAI, RegionColor.BLACK, gameMap);
		Vertex cairo = createNewCity(CityName.CAIRO, RegionColor.BLACK, gameMap);
		
		Vertex bangkok = createNewCity(CityName.BANGKOK, RegionColor.RED, gameMap);
		Vertex jakarta = createNewCity(CityName.JAKARTA, RegionColor.RED, gameMap);
		Vertex hongKong = createNewCity(CityName.HONG_KONG, RegionColor.RED, gameMap);
		Vertex taipei = createNewCity(CityName.TAIPEI, RegionColor.RED, gameMap);
		Vertex hoChiMinhCity = createNewCity(CityName.HO_CHI_MINH_CITY, RegionColor.RED, gameMap);
		Vertex manila = createNewCity(CityName.MANILA, RegionColor.RED, gameMap);
		Vertex sydney = createNewCity(CityName.SYDNEY, RegionColor.RED, gameMap);
		Vertex osaka = createNewCity(CityName.OSAKA, RegionColor.RED, gameMap);
		Vertex shanghai = createNewCity(CityName.SHANGHAI, RegionColor.RED, gameMap);
		Vertex tokyo = createNewCity(CityName.TOKYO, RegionColor.RED, gameMap);
		Vertex beijing = createNewCity(CityName.BEIJING, RegionColor.RED, gameMap);
		Vertex seoul = createNewCity(CityName.SEOUL, RegionColor.RED, gameMap);
		
		// connect cities together
		connect(atlanta, washington);
		connect(washington, montreal);
		connect(chicago, montreal);
		connect(chicago, atlanta);
		connect(chicago, losAngeles);
		connect(sanFrancisco, chicago);
		connect(newYork, montreal);
		connect(newYork, washington);
		connect(newYork, london);
		connect(newYork, madrid);
		connect(london, paris);
		connect(london, madrid);
		connect(paris, essen);
		connect(paris, milan);
		connect(paris, madrid);
		connect(stPetersburg, essen);
		connect(essen, london);
		connect(essen, stPetersburg);
		connect(essen, milan);
		connect(sanFrancisco, losAngeles);
		connect(losAngeles, mexicoCity);
		connect(mexicoCity, miami);
		connect(mexicoCity, chicago);
		connect(mexicoCity, bogota);
		connect(mexicoCity, lima);
		connect(miami, atlanta);
		connect(miami, washington);
		connect(miami, bogota);
		connect(bogota, lima);
		connect(santiago, lima);
		connect(bogota, buenosAires);
		connect(bogota, saoPaulo);
		connect(saoPaulo, madrid);
		connect(saoPaulo, lagos);
		connect(saoPaulo, buenosAires);
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
		connect(algiers, madrid);
		connect(cairo, algiers);
		connect(cairo, baghdad);
		connect(cairo, riyadh);
		connect(istanbul, moscow);
		connect(istanbul, stPetersburg);
		connect(istanbul, baghdad);
		connect(istanbul, algiers);
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
		connect(kolkata, bangkok);
		connect(kolkata, hongKong);
		connect(chennai, jakarta);
		connect(chennai, bangkok);
		connect(sanFrancisco, tokyo);
		connect(sanFrancisco, manila);
		connect(losAngeles, sydney);
		connect(bangkok, jakarta);
		connect(bangkok, hongKong);
		connect(bangkok, hoChiMinhCity);
		connect(jakarta, hoChiMinhCity);
		connect(jakarta, sydney);
		connect(hoChiMinhCity, hongKong);
		connect(hoChiMinhCity, manila);
		connect(manila, sydney);
		connect(manila, taipei);
		connect(hongKong, manila);
		connect(hongKong, shanghai);
		connect(hongKong, taipei);
		connect(taipei, shanghai);
		connect(taipei, osaka);
		connect(osaka, tokyo);
		connect(tokyo, seoul);
		connect(tokyo, shanghai);
		connect(shanghai, beijing);
		connect(shanghai, seoul);
		connect(seoul, beijing);

		// add a research station at atlanta
		atlanta.value(Constant.RESEARCH_STATION, Boolean.TRUE);
		
		return new GameMap(gameMap);
	}
	
	static private void connect(Vertex cityA, Vertex cityB) {
		cityA.addEdge(Constant.ADJACENT, cityB);
		cityB.addEdge(Constant.ADJACENT, cityA);
	}
	
	static private Vertex createNewCity(String city, String regionColor, Graph gameMap) {
		Vertex vertex = gameMap.addVertex(T.id, city, T.label, city);
		vertex.property(Constant.RED_DISEASE, Integer.valueOf(0));
		vertex.property(Constant.BLACK_DISEASE, Integer.valueOf(0));
		vertex.property(Constant.BLUE_DISEASE, Integer.valueOf(0));
		vertex.property(Constant.YELLOW_DISASE, Integer.valueOf(0));
		vertex.property(Constant.REGION, regionColor);
		vertex.property(Constant.RESEARCH_STATION, Boolean.FALSE);
		return vertex;
	}

	@VisibleForTesting
	Graph getInternalGameMap() {
		return internalGameMap;
	}
}
