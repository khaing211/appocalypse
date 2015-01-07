package com.github.appocalypse.pandemic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class DirectConnectivityTest {

	private Board board = Board.createPandemicBoard();
	
	private void assertNeighbors(City origin, City... neighbors) {
		List<City> neighborCities = board.getNeighborCities(origin);
		assertEquals(origin + " should have " + neighbors.length + " neighbors, but actual neighbor cities: " + neighborCities, 
				neighbors.length, neighborCities.size());
		
		for (City neighbor : neighbors) {
			assertTrue(neighbor + " should be neighbor of " + origin, neighborCities.contains(neighbor));
		}
	}
	
	@Test
	public void testAtlanta() {
		assertNeighbors(Cities.ATLANTA, Cities.CHICAGO, Cities.WASHINGTON, Cities.MIAMI);
	}

	@Test
	public void testMiami() {
		assertNeighbors(Cities.MIAMI, Cities.ATLANTA, Cities.MEXICO_CITY, Cities.WASHINGTON, Cities.BOGOTA);
	}
	
	@Test
	public void testWashington() {
		assertNeighbors(Cities.WASHINGTON, Cities.ATLANTA, Cities.MIAMI, Cities.MONTREAL, Cities.NEW_YORK);
	}
	
	@Test
	public void testChicago() {
		assertNeighbors(Cities.CHICAGO, Cities.MEXICO_CITY, Cities.LOS_ANGELES, Cities.MONTREAL, Cities.ATLANTA, Cities.SAN_FRANCISCO);
	}
	
	@Test
	public void testLosAngeles() {
		assertNeighbors(Cities.LOS_ANGELES, Cities.CHICAGO, Cities.SAN_FRANCISCO, Cities.MEXICO_CITY, Cities.SYDNEY);
	}
	
	@Test
	public void testMontreal() {
		assertNeighbors(Cities.MONTREAL, Cities.CHICAGO, Cities.WASHINGTON, Cities.NEW_YORK);
	}
	
	@Test
	public void testNewYork() {
		assertNeighbors(Cities.NEW_YORK, Cities.MONTREAL, Cities.WASHINGTON, Cities.LONDON, Cities.MADRID);
	}
	
	@Test
	public void testMexicoCity() {
		assertNeighbors(Cities.MEXICO_CITY, Cities.MIAMI, Cities.LOS_ANGELES, Cities.CHICAGO, Cities.BOGOTA, Cities.LIMA);
	}
	
	@Test
	public void testSanFrancisco() {
		assertNeighbors(Cities.SAN_FRANCISCO, Cities.CHICAGO, Cities.LOS_ANGELES, Cities.TOKYO, Cities.MANILA);
	}
	
	@Test
	public void testLondon() {
		assertNeighbors(Cities.LONDON, Cities.NEW_YORK, Cities.MADRID, Cities.PARIS, Cities.ESSEN);
	}
	
	@Test
	public void testMadrid() {
		assertNeighbors(Cities.MADRID, Cities.LONDON, Cities.NEW_YORK, Cities.PARIS, Cities.SAO_PAULO, Cities.ALGIERS);
	}
	
	@Test
	public void testParis() {
		assertNeighbors(Cities.PARIS, Cities.LONDON, Cities.ESSEN, Cities.MILAN, Cities.MADRID, Cities.ALGIERS);
	}
	
	@Test
	public void testEssen() {
		assertNeighbors(Cities.ESSEN, Cities.LONDON, Cities.PARIS, Cities.MILAN, Cities.ST_PETERSBURG);
	}
	
	@Test
	public void testMilan() {
		assertNeighbors(Cities.MILAN, Cities.ESSEN, Cities.PARIS, Cities.ISTANBUL);
	}
	
	@Test
	public void testStPetersburg() {
		assertNeighbors(Cities.ST_PETERSBURG, Cities.MOSCOW, Cities.ESSEN, Cities.ISTANBUL);
	}
	
	@Test
	public void testMoscow() {
		assertNeighbors(Cities.MOSCOW, Cities.ST_PETERSBURG, Cities.TEHRAN, Cities.ISTANBUL);
	}
	
	@Test
	public void testIstanbul() {
		assertNeighbors(Cities.ISTANBUL, Cities.MILAN, Cities.ST_PETERSBURG, Cities.MOSCOW, Cities.BAGHDAD, Cities.ALGIERS, Cities.CAIRO);
	}
	
	@Test
	public void testAlgiers() {
		assertNeighbors(Cities.ALGIERS, Cities.ISTANBUL, Cities.CAIRO, Cities.PARIS, Cities.MADRID);
	}
	
	@Test
	public void testCairo() {
		assertNeighbors(Cities.CAIRO, Cities.ALGIERS, Cities.ISTANBUL, Cities.BAGHDAD, Cities.RIYADH, Cities.KHARTOUM);
	}
	
	@Test
	public void testRiyadh() {
		assertNeighbors(Cities.RIYADH, Cities.CAIRO, Cities.BAGHDAD, Cities.KARACHI);
	}
	
	@Test
	public void testBaghdad() {
		assertNeighbors(Cities.BAGHDAD, Cities.CAIRO, Cities.ISTANBUL, Cities.RIYADH, Cities.KARACHI, Cities.TEHRAN);
	}
	
	@Test
	public void testTehran() {
		assertNeighbors(Cities.TEHRAN, Cities.MOSCOW, Cities.BAGHDAD, Cities.DELHI, Cities.KARACHI);
	}
	
	@Test
	public void testKarachi() {
		assertNeighbors(Cities.KARACHI, Cities.RIYADH, Cities.BAGHDAD, Cities.TEHRAN, Cities.DELHI, Cities.MUMBAI);
	}
	
	@Test
	public void testDelhi() {
		assertNeighbors(Cities.DELHI, Cities.TEHRAN, Cities.KARACHI, Cities.MUMBAI, Cities.KOLKATA, Cities.CHENNAI);
	}
	
	@Test
	public void testMumbai() {
		assertNeighbors(Cities.MUMBAI, Cities.DELHI, Cities.KARACHI, Cities.CHENNAI);
	}
	
	@Test
	public void testChennai() {
		assertNeighbors(Cities.CHENNAI, Cities.DELHI, Cities.MUMBAI, Cities.KOLKATA, Cities.BANGKOK, Cities.JAKARTA);
	}
	
	@Test
	public void testKolkata() {
		assertNeighbors(Cities.KOLKATA, Cities.DELHI, Cities.CHENNAI, Cities.BANGKOK, Cities.HONG_KONG);
	}
	
	@Test
	public void testBangkok() {
		assertNeighbors(Cities.BANGKOK, Cities.KOLKATA, Cities.CHENNAI, Cities.HONG_KONG, Cities.HO_CHI_MINH_CITY, Cities.JAKARTA);
	}
	
	@Test
	public void testJakarta() {
		assertNeighbors(Cities.JAKARTA, Cities.BANGKOK, Cities.CHENNAI, Cities.HO_CHI_MINH_CITY, Cities.SYDNEY);
	}
	
	@Test
	public void testHongKong() {
		assertNeighbors(Cities.HONG_KONG, Cities.KOLKATA, Cities.HO_CHI_MINH_CITY, Cities.BANGKOK, Cities.SHANGHAI, Cities.TAIPEI, Cities.MANILA);
	}
	
	@Test
	public void testHoChiMinhCity() {
		assertNeighbors(Cities.HO_CHI_MINH_CITY, Cities.BANGKOK, Cities.HONG_KONG, Cities.JAKARTA, Cities.MANILA);
	}
	
	@Test
	public void testManila() {
		assertNeighbors(Cities.MANILA, Cities.HONG_KONG, Cities.TAIPEI, Cities.HO_CHI_MINH_CITY, Cities.SYDNEY, Cities.SAN_FRANCISCO);
	}
	
	@Test
	public void testSydney() {
		assertNeighbors(Cities.SYDNEY, Cities.MANILA, Cities.JAKARTA, Cities.LOS_ANGELES);
	}
	
	@Test
	public void testTaipei() {
		assertNeighbors(Cities.TAIPEI, Cities.MANILA, Cities.HONG_KONG, Cities.OSAKA, Cities.SHANGHAI);
	}
	
	@Test
	public void testShanghai() {
		assertNeighbors(Cities.SHANGHAI, Cities.HONG_KONG, Cities.TAIPEI, Cities.TOKYO, Cities.SEOUL, Cities.BEIJING);
	}
	
	@Test
	public void testOsaka() {
		assertNeighbors(Cities.OSAKA, Cities.TOKYO, Cities.TAIPEI);
	}
	
	@Test
	public void testTokyo() {
		assertNeighbors(Cities.TOKYO, Cities.SEOUL, Cities.SHANGHAI, Cities.OSAKA, Cities.SAN_FRANCISCO);
	}
	
	@Test
	public void testSeoul() {
		assertNeighbors(Cities.SEOUL, Cities.TOKYO, Cities.BEIJING, Cities.SHANGHAI);
	}
	
	@Test
	public void testBeijing() {
		assertNeighbors(Cities.BEIJING, Cities.SEOUL, Cities.SHANGHAI);
	}
	
	@Test
	public void testBogota() {
		assertNeighbors(Cities.BOGOTA, Cities.MIAMI, Cities.MEXICO_CITY, Cities.LIMA, Cities.SAO_PAULO, Cities.BUENOS_AIRES);
	}
	
	@Test
	public void testLima() {
		assertNeighbors(Cities.LIMA, Cities.BOGOTA, Cities.MEXICO_CITY, Cities.SANTIAGO);
	}
	
	@Test
	public void testSantiago() {
		assertNeighbors(Cities.SANTIAGO, Cities.LIMA);
	}
	
	@Test
	public void testBuenosAires() {
		assertNeighbors(Cities.BUENOS_AIRES, Cities.BOGOTA, Cities.SAO_PAULO);
	}
	
	@Test
	public void testSaoPaulo() {
		assertNeighbors(Cities.SAO_PAULO, Cities.BOGOTA, Cities.BUENOS_AIRES, Cities.MADRID, Cities.LAGOS);
	}
	
	@Test
	public void testLagos() {
		assertNeighbors(Cities.LAGOS, Cities.SAO_PAULO, Cities.KHARTOUM, Cities.KINSHASA);
	}
	
	@Test
	public void testKinshasa() {
		assertNeighbors(Cities.KINSHASA, Cities.LAGOS, Cities.KHARTOUM, Cities.JOHANNESBURG);
	}
	
	@Test
	public void testKhartoum() {
		assertNeighbors(Cities.KHARTOUM, Cities.LAGOS, Cities.KINSHASA, Cities.JOHANNESBURG, Cities.CAIRO);
	}
	
	@Test
	public void testJohannesburg() {
		assertNeighbors(Cities.JOHANNESBURG, Cities.KINSHASA, Cities.KHARTOUM);
	}
}
