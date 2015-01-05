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
	
	@Test
	public void testNewYork() {
		assertNeighbors(City.NEW_YORK, City.MONTREAL, City.WASHINGTON, City.LONDON, City.MADRID);
	}
	
	@Test
	public void testMexicoCity() {
		assertNeighbors(City.MEXICO_CITY, City.MIAMI, City.LOS_ANGELES, City.CHICAGO, City.BOGOTA, City.LIMA);
	}
	
	@Test
	public void testSanFrancisco() {
		assertNeighbors(City.SAN_FRANCISCO, City.CHICAGO, City.LOS_ANGELES, City.TOKYO, City.MANILA);
	}
	
	@Test
	public void testLondon() {
		assertNeighbors(City.LONDON, City.NEW_YORK, City.MADRID, City.PARIS, City.ESSEN);
	}
	
	@Test
	public void testMadrid() {
		assertNeighbors(City.MADRID, City.LONDON, City.NEW_YORK, City.PARIS, City.SAO_PAULO, City.ALGIERS);
	}
	
	@Test
	public void testParis() {
		assertNeighbors(City.PARIS, City.LONDON, City.ESSEN, City.MILAN, City.MADRID, City.ALGIERS);
	}
	
	@Test
	public void testEssen() {
		assertNeighbors(City.ESSEN, City.LONDON, City.PARIS, City.MILAN, City.ST_PETERSBURG);
	}
	
	@Test
	public void testMilan() {
		assertNeighbors(City.MILAN, City.ESSEN, City.PARIS, City.ISTANBUL);
	}
	
	@Test
	public void testStPetersburg() {
		assertNeighbors(City.ST_PETERSBURG, City.MOSCOW, City.ESSEN, City.ISTANBUL);
	}
	
	@Test
	public void testMoscow() {
		assertNeighbors(City.MOSCOW, City.ST_PETERSBURG, City.TEHRAN, City.ISTANBUL);
	}
	
	@Test
	public void testIstanbul() {
		assertNeighbors(City.ISTANBUL, City.MILAN, City.ST_PETERSBURG, City.MOSCOW, City.BAGHDAD, City.ALGIERS, City.CAIRO);
	}
	
	@Test
	public void testAlgiers() {
		assertNeighbors(City.ALGIERS, City.ISTANBUL, City.CAIRO, City.PARIS, City.MADRID);
	}
	
	@Test
	public void testCairo() {
		assertNeighbors(City.CAIRO, City.ALGIERS, City.ISTANBUL, City.BAGHDAD, City.RIYADH, City.KHARTOUM);
	}
	
	@Test
	public void testRiyadh() {
		assertNeighbors(City.RIYADH, City.CAIRO, City.BAGHDAD, City.KARACHI);
	}
	
	@Test
	public void testBaghdad() {
		assertNeighbors(City.BAGHDAD, City.CAIRO, City.ISTANBUL, City.RIYADH, City.KARACHI, City.TEHRAN);
	}
	
	@Test
	public void testTehran() {
		assertNeighbors(City.TEHRAN, City.MOSCOW, City.BAGHDAD, City.DELHI, City.KARACHI);
	}
	
	@Test
	public void testKarachi() {
		assertNeighbors(City.KARACHI, City.RIYADH, City.BAGHDAD, City.TEHRAN, City.DELHI, City.MUMBAI);
	}
	
	@Test
	public void testDelhi() {
		assertNeighbors(City.DELHI, City.TEHRAN, City.KARACHI, City.MUMBAI, City.KOLKATA, City.CHENNAI);
	}
	
	@Test
	public void testMumbai() {
		assertNeighbors(City.MUMBAI, City.DELHI, City.KARACHI, City.CHENNAI);
	}
	
	@Test
	public void testChennai() {
		assertNeighbors(City.CHENNAI, City.DELHI, City.MUMBAI, City.KOLKATA, City.BANGKOK, City.JAKARTA);
	}
	
	@Test
	public void testKolkata() {
		assertNeighbors(City.KOLKATA, City.DELHI, City.CHENNAI, City.BANGKOK, City.HONG_KONG);
	}
	
	@Test
	public void testBangkok() {
		assertNeighbors(City.BANGKOK, City.KOLKATA, City.CHENNAI, City.HONG_KONG, City.HO_CHI_MINH_CITY, City.JAKARTA);
	}
	
	@Test
	public void testJakarta() {
		assertNeighbors(City.JAKARTA, City.BANGKOK, City.CHENNAI, City.HO_CHI_MINH_CITY, City.SYDNEY);
	}
	
	@Test
	public void testHongKong() {
		assertNeighbors(City.HONG_KONG, City.KOLKATA, City.HO_CHI_MINH_CITY, City.BANGKOK, City.SHANGHAI, City.TAIPEI, City.MANILA);
	}
	
	@Test
	public void testHoChiMinhCity() {
		assertNeighbors(City.HO_CHI_MINH_CITY, City.BANGKOK, City.HONG_KONG, City.JAKARTA, City.MANILA);
	}
	
	@Test
	public void testManila() {
		assertNeighbors(City.MANILA, City.HONG_KONG, City.TAIPEI, City.HO_CHI_MINH_CITY, City.SYDNEY, City.SAN_FRANCISCO);
	}
	
	@Test
	public void testSydney() {
		assertNeighbors(City.SYDNEY, City.MANILA, City.JAKARTA, City.LOS_ANGELES);
	}
	
	@Test
	public void testTaipei() {
		assertNeighbors(City.TAIPEI, City.MANILA, City.HONG_KONG, City.OSAKA, City.SHANGHAI);
	}
	
	@Test
	public void testShanghai() {
		assertNeighbors(City.SHANGHAI, City.HONG_KONG, City.TAIPEI, City.TOKYO, City.SEOUL, City.BEIJING);
	}
	
	@Test
	public void testOsaka() {
		assertNeighbors(City.OSAKA, City.TOKYO, City.TAIPEI);
	}
	
	@Test
	public void testTokyo() {
		assertNeighbors(City.TOKYO, City.SEOUL, City.SHANGHAI, City.OSAKA, City.SAN_FRANCISCO);
	}
	
	@Test
	public void testSeoul() {
		assertNeighbors(City.SEOUL, City.TOKYO, City.BEIJING, City.SHANGHAI);
	}
	
	@Test
	public void testBeijing() {
		assertNeighbors(City.BEIJING, City.SEOUL, City.SHANGHAI);
	}
	
	@Test
	public void testBogota() {
		assertNeighbors(City.BOGOTA, City.MIAMI, City.MEXICO_CITY, City.LIMA, City.SAO_PAULO, City.BUENOS_AIRES);
	}
	
	@Test
	public void testLima() {
		assertNeighbors(City.LIMA, City.BOGOTA, City.MEXICO_CITY, City.SANTIAGO);
	}
	
	@Test
	public void testSantiago() {
		assertNeighbors(City.SANTIAGO, City.LIMA);
	}
	
	@Test
	public void testBuenosAires() {
		assertNeighbors(City.BUENOS_AIRES, City.BOGOTA, City.SAO_PAULO);
	}
	
	@Test
	public void testSaoPaulo() {
		assertNeighbors(City.SAO_PAULO, City.BOGOTA, City.BUENOS_AIRES, City.MADRID, City.LAGOS);
	}
	
	@Test
	public void testLagos() {
		assertNeighbors(City.LAGOS, City.SAO_PAULO, City.KHARTOUM, City.KINSHASA);
	}
	
	@Test
	public void testKinshasa() {
		assertNeighbors(City.KINSHASA, City.LAGOS, City.KHARTOUM, City.JOHANNESBURG);
	}
	
	@Test
	public void testKhartoum() {
		assertNeighbors(City.KHARTOUM, City.LAGOS, City.KINSHASA, City.JOHANNESBURG, City.CAIRO);
	}
	
	@Test
	public void testJohannesburg() {
		assertNeighbors(City.JOHANNESBURG, City.KINSHASA, City.KHARTOUM);
	}
}
