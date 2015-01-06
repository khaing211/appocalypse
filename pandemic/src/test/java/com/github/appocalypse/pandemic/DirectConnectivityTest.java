package com.github.appocalypse.pandemic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.github.appocalypse.pandemic.Constant.CityName;

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
		assertNeighbors(CityName.ATLANTA, CityName.CHICAGO, CityName.WASHINGTON, CityName.MIAMI);
	}

	@Test
	public void testMiami() {
		assertNeighbors(CityName.MIAMI, CityName.ATLANTA, CityName.MEXICO_CITY, CityName.WASHINGTON, CityName.BOGOTA);
	}
	
	@Test
	public void testWashington() {
		assertNeighbors(CityName.WASHINGTON, CityName.ATLANTA, CityName.MIAMI, CityName.MONTREAL, CityName.NEW_YORK);
	}
	
	@Test
	public void testChicago() {
		assertNeighbors(CityName.CHICAGO, CityName.MEXICO_CITY, CityName.LOS_ANGELES, CityName.MONTREAL, CityName.ATLANTA, CityName.SAN_FRANCISCO);
	}
	
	@Test
	public void testLosAngeles() {
		assertNeighbors(CityName.LOS_ANGELES, CityName.CHICAGO, CityName.SAN_FRANCISCO, CityName.MEXICO_CITY, CityName.SYDNEY);
	}
	
	@Test
	public void testMontreal() {
		assertNeighbors(CityName.MONTREAL, CityName.CHICAGO, CityName.WASHINGTON, CityName.NEW_YORK);
	}
	
	@Test
	public void testNewYork() {
		assertNeighbors(CityName.NEW_YORK, CityName.MONTREAL, CityName.WASHINGTON, CityName.LONDON, CityName.MADRID);
	}
	
	@Test
	public void testMexicoCity() {
		assertNeighbors(CityName.MEXICO_CITY, CityName.MIAMI, CityName.LOS_ANGELES, CityName.CHICAGO, CityName.BOGOTA, CityName.LIMA);
	}
	
	@Test
	public void testSanFrancisco() {
		assertNeighbors(CityName.SAN_FRANCISCO, CityName.CHICAGO, CityName.LOS_ANGELES, CityName.TOKYO, CityName.MANILA);
	}
	
	@Test
	public void testLondon() {
		assertNeighbors(CityName.LONDON, CityName.NEW_YORK, CityName.MADRID, CityName.PARIS, CityName.ESSEN);
	}
	
	@Test
	public void testMadrid() {
		assertNeighbors(CityName.MADRID, CityName.LONDON, CityName.NEW_YORK, CityName.PARIS, CityName.SAO_PAULO, CityName.ALGIERS);
	}
	
	@Test
	public void testParis() {
		assertNeighbors(CityName.PARIS, CityName.LONDON, CityName.ESSEN, CityName.MILAN, CityName.MADRID, CityName.ALGIERS);
	}
	
	@Test
	public void testEssen() {
		assertNeighbors(CityName.ESSEN, CityName.LONDON, CityName.PARIS, CityName.MILAN, CityName.ST_PETERSBURG);
	}
	
	@Test
	public void testMilan() {
		assertNeighbors(CityName.MILAN, CityName.ESSEN, CityName.PARIS, CityName.ISTANBUL);
	}
	
	@Test
	public void testStPetersburg() {
		assertNeighbors(CityName.ST_PETERSBURG, CityName.MOSCOW, CityName.ESSEN, CityName.ISTANBUL);
	}
	
	@Test
	public void testMoscow() {
		assertNeighbors(CityName.MOSCOW, CityName.ST_PETERSBURG, CityName.TEHRAN, CityName.ISTANBUL);
	}
	
	@Test
	public void testIstanbul() {
		assertNeighbors(CityName.ISTANBUL, CityName.MILAN, CityName.ST_PETERSBURG, CityName.MOSCOW, CityName.BAGHDAD, CityName.ALGIERS, CityName.CAIRO);
	}
	
	@Test
	public void testAlgiers() {
		assertNeighbors(CityName.ALGIERS, CityName.ISTANBUL, CityName.CAIRO, CityName.PARIS, CityName.MADRID);
	}
	
	@Test
	public void testCairo() {
		assertNeighbors(CityName.CAIRO, CityName.ALGIERS, CityName.ISTANBUL, CityName.BAGHDAD, CityName.RIYADH, CityName.KHARTOUM);
	}
	
	@Test
	public void testRiyadh() {
		assertNeighbors(CityName.RIYADH, CityName.CAIRO, CityName.BAGHDAD, CityName.KARACHI);
	}
	
	@Test
	public void testBaghdad() {
		assertNeighbors(CityName.BAGHDAD, CityName.CAIRO, CityName.ISTANBUL, CityName.RIYADH, CityName.KARACHI, CityName.TEHRAN);
	}
	
	@Test
	public void testTehran() {
		assertNeighbors(CityName.TEHRAN, CityName.MOSCOW, CityName.BAGHDAD, CityName.DELHI, CityName.KARACHI);
	}
	
	@Test
	public void testKarachi() {
		assertNeighbors(CityName.KARACHI, CityName.RIYADH, CityName.BAGHDAD, CityName.TEHRAN, CityName.DELHI, CityName.MUMBAI);
	}
	
	@Test
	public void testDelhi() {
		assertNeighbors(CityName.DELHI, CityName.TEHRAN, CityName.KARACHI, CityName.MUMBAI, CityName.KOLKATA, CityName.CHENNAI);
	}
	
	@Test
	public void testMumbai() {
		assertNeighbors(CityName.MUMBAI, CityName.DELHI, CityName.KARACHI, CityName.CHENNAI);
	}
	
	@Test
	public void testChennai() {
		assertNeighbors(CityName.CHENNAI, CityName.DELHI, CityName.MUMBAI, CityName.KOLKATA, CityName.BANGKOK, CityName.JAKARTA);
	}
	
	@Test
	public void testKolkata() {
		assertNeighbors(CityName.KOLKATA, CityName.DELHI, CityName.CHENNAI, CityName.BANGKOK, CityName.HONG_KONG);
	}
	
	@Test
	public void testBangkok() {
		assertNeighbors(CityName.BANGKOK, CityName.KOLKATA, CityName.CHENNAI, CityName.HONG_KONG, CityName.HO_CHI_MINH_CITY, CityName.JAKARTA);
	}
	
	@Test
	public void testJakarta() {
		assertNeighbors(CityName.JAKARTA, CityName.BANGKOK, CityName.CHENNAI, CityName.HO_CHI_MINH_CITY, CityName.SYDNEY);
	}
	
	@Test
	public void testHongKong() {
		assertNeighbors(CityName.HONG_KONG, CityName.KOLKATA, CityName.HO_CHI_MINH_CITY, CityName.BANGKOK, CityName.SHANGHAI, CityName.TAIPEI, CityName.MANILA);
	}
	
	@Test
	public void testHoChiMinhCity() {
		assertNeighbors(CityName.HO_CHI_MINH_CITY, CityName.BANGKOK, CityName.HONG_KONG, CityName.JAKARTA, CityName.MANILA);
	}
	
	@Test
	public void testManila() {
		assertNeighbors(CityName.MANILA, CityName.HONG_KONG, CityName.TAIPEI, CityName.HO_CHI_MINH_CITY, CityName.SYDNEY, CityName.SAN_FRANCISCO);
	}
	
	@Test
	public void testSydney() {
		assertNeighbors(CityName.SYDNEY, CityName.MANILA, CityName.JAKARTA, CityName.LOS_ANGELES);
	}
	
	@Test
	public void testTaipei() {
		assertNeighbors(CityName.TAIPEI, CityName.MANILA, CityName.HONG_KONG, CityName.OSAKA, CityName.SHANGHAI);
	}
	
	@Test
	public void testShanghai() {
		assertNeighbors(CityName.SHANGHAI, CityName.HONG_KONG, CityName.TAIPEI, CityName.TOKYO, CityName.SEOUL, CityName.BEIJING);
	}
	
	@Test
	public void testOsaka() {
		assertNeighbors(CityName.OSAKA, CityName.TOKYO, CityName.TAIPEI);
	}
	
	@Test
	public void testTokyo() {
		assertNeighbors(CityName.TOKYO, CityName.SEOUL, CityName.SHANGHAI, CityName.OSAKA, CityName.SAN_FRANCISCO);
	}
	
	@Test
	public void testSeoul() {
		assertNeighbors(CityName.SEOUL, CityName.TOKYO, CityName.BEIJING, CityName.SHANGHAI);
	}
	
	@Test
	public void testBeijing() {
		assertNeighbors(CityName.BEIJING, CityName.SEOUL, CityName.SHANGHAI);
	}
	
	@Test
	public void testBogota() {
		assertNeighbors(CityName.BOGOTA, CityName.MIAMI, CityName.MEXICO_CITY, CityName.LIMA, CityName.SAO_PAULO, CityName.BUENOS_AIRES);
	}
	
	@Test
	public void testLima() {
		assertNeighbors(CityName.LIMA, CityName.BOGOTA, CityName.MEXICO_CITY, CityName.SANTIAGO);
	}
	
	@Test
	public void testSantiago() {
		assertNeighbors(CityName.SANTIAGO, CityName.LIMA);
	}
	
	@Test
	public void testBuenosAires() {
		assertNeighbors(CityName.BUENOS_AIRES, CityName.BOGOTA, CityName.SAO_PAULO);
	}
	
	@Test
	public void testSaoPaulo() {
		assertNeighbors(CityName.SAO_PAULO, CityName.BOGOTA, CityName.BUENOS_AIRES, CityName.MADRID, CityName.LAGOS);
	}
	
	@Test
	public void testLagos() {
		assertNeighbors(CityName.LAGOS, CityName.SAO_PAULO, CityName.KHARTOUM, CityName.KINSHASA);
	}
	
	@Test
	public void testKinshasa() {
		assertNeighbors(CityName.KINSHASA, CityName.LAGOS, CityName.KHARTOUM, CityName.JOHANNESBURG);
	}
	
	@Test
	public void testKhartoum() {
		assertNeighbors(CityName.KHARTOUM, CityName.LAGOS, CityName.KINSHASA, CityName.JOHANNESBURG, CityName.CAIRO);
	}
	
	@Test
	public void testJohannesburg() {
		assertNeighbors(CityName.JOHANNESBURG, CityName.KINSHASA, CityName.KHARTOUM);
	}
}
