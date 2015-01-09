package com.github.appocalypse.pandemic;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.github.appocalypse.guava.extra.GuavaCollectors;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableListMultimap;

public class Board {
	final private ImmutableListMultimap<City, City> connectivity;
	final private ImmutableList<City> cities;
	
	public Board(ImmutableList<City> cities, ImmutableListMultimap<City, City> connectivity) {
		this.cities = cities;
		this.connectivity = connectivity;
	}
	
	public long getCitiesCount() {
		return cities.size();
	}
	
	public long getCitiesCount(final RegionColor regionColor) {
		return cities.stream().parallel().filter(city -> city.getRegionColor().equals(regionColor)).count();
	}
	
	public ImmutableList<City> getNeighborCities(City city) {
		return connectivity.get(city);
	}
	
	public ImmutableList<City> getMostConnectCities() {
		Map<City, Integer> connectityRank = connectivity.asMap()
				.entrySet()
				.parallelStream()
				.collect(Collectors.toMap(Entry::getKey, entry -> entry.getValue().size()));
		
		Integer topConnectivityRank = connectityRank.entrySet()
				.parallelStream()
				.sorted(Comparator.comparing(Entry::getValue))
				.findFirst()
				.map(Entry::getValue)
				.orElse(0);
		
		return connectityRank.entrySet()
			.parallelStream()
			.filter(entry -> { return entry.getValue() > topConnectivityRank; })
			.map(Entry::getKey)
			.collect(GuavaCollectors.immutableList());
	}
	
	static public Board createPandemicBoard() {
		ImmutableList.Builder<City> cities = ImmutableList.<City>builder();

		Arrays.stream(Cities.values()).forEach(city -> cities.add(city));
		
		ImmutableListMultimap<City, City> connectivity = ImmutableListMultimap.<City, City>builder()
				.putAll(Cities.ATLANTA, Cities.WASHINGTON, Cities.CHICAGO, Cities.MIAMI)
				.putAll(Cities.CHICAGO, Cities.MONTREAL, Cities.ATLANTA, Cities.MEXICO_CITY, Cities.LOS_ANGELES, Cities.SAN_FRANCISCO)
				.putAll(Cities.SAN_FRANCISCO, Cities.CHICAGO, Cities.LOS_ANGELES, Cities.TOKYO, Cities.MANILA)
				.putAll(Cities.WASHINGTON, Cities.ATLANTA, Cities.MONTREAL, Cities.NEW_YORK, Cities.MIAMI)
				.putAll(Cities.MONTREAL, Cities.CHICAGO, Cities.NEW_YORK, Cities.WASHINGTON)
				.putAll(Cities.NEW_YORK, Cities.MONTREAL, Cities.WASHINGTON, Cities.LONDON, Cities.MADRID)
				.putAll(Cities.LONDON, Cities.NEW_YORK, Cities.MADRID, Cities.PARIS, Cities.ESSEN)
				.putAll(Cities.MADRID, Cities.LONDON, Cities.NEW_YORK, Cities.PARIS, Cities.ALGIERS, Cities.SAO_PAULO)
				.putAll(Cities.PARIS, Cities.LONDON, Cities.MADRID, Cities.ESSEN, Cities.MILAN, Cities.ALGIERS)
				.putAll(Cities.ESSEN, Cities.LONDON, Cities.PARIS, Cities.MILAN, Cities.ST_PETERSBURG)
				.putAll(Cities.MILAN, Cities.ESSEN, Cities.PARIS, Cities.ISTANBUL)
				.putAll(Cities.ST_PETERSBURG, Cities.ESSEN, Cities.MOSCOW, Cities.ISTANBUL)
				.putAll(Cities.ALGIERS, Cities.PARIS, Cities.MADRID, Cities.ISTANBUL, Cities.CAIRO)
				.putAll(Cities.CAIRO, Cities.ISTANBUL, Cities.ALGIERS, Cities.BAGHDAD, Cities.RIYADH, Cities.KHARTOUM)
				.putAll(Cities.MOSCOW, Cities.ST_PETERSBURG, Cities.ISTANBUL, Cities.TEHRAN)
				.putAll(Cities.ISTANBUL, Cities.ST_PETERSBURG, Cities.MILAN, Cities.ALGIERS, Cities.CAIRO, Cities.MOSCOW, Cities.BAGHDAD)
				.putAll(Cities.BAGHDAD, Cities.ISTANBUL, Cities.TEHRAN, Cities.CAIRO, Cities.RIYADH, Cities.KARACHI)
				.putAll(Cities.TEHRAN, Cities.MOSCOW, Cities.DELHI, Cities.BAGHDAD, Cities.KARACHI)
				.putAll(Cities.RIYADH, Cities.CAIRO, Cities.BAGHDAD, Cities.KARACHI)
				.putAll(Cities.KARACHI, Cities.BAGHDAD, Cities.RIYADH, Cities.TEHRAN, Cities.DELHI, Cities.MUMBAI)
				.putAll(Cities.DELHI, Cities.TEHRAN, Cities.KARACHI, Cities.MUMBAI, Cities.KOLKATA, Cities.CHENNAI)
				.putAll(Cities.MUMBAI, Cities.KARACHI, Cities.DELHI, Cities.CHENNAI)
				.putAll(Cities.CHENNAI, Cities.MUMBAI, Cities.DELHI, Cities.KOLKATA, Cities.BANGKOK, Cities.JAKARTA)
				.putAll(Cities.KOLKATA, Cities.DELHI, Cities.CHENNAI, Cities.HONG_KONG, Cities.BANGKOK)
				.putAll(Cities.JAKARTA, Cities.BANGKOK, Cities.CHENNAI, Cities.HO_CHI_MINH_CITY, Cities.SYDNEY)
				.putAll(Cities.SYDNEY, Cities.JAKARTA, Cities.MANILA, Cities.LOS_ANGELES)
				.putAll(Cities.HO_CHI_MINH_CITY, Cities.JAKARTA, Cities.BANGKOK, Cities.HONG_KONG, Cities.MANILA)
				.putAll(Cities.BANGKOK, Cities.KOLKATA, Cities.CHENNAI, Cities.HONG_KONG, Cities.JAKARTA, Cities.HO_CHI_MINH_CITY)
				.putAll(Cities.MANILA, Cities.TAIPEI, Cities.SYDNEY, Cities.HO_CHI_MINH_CITY, Cities.HONG_KONG, Cities.SAN_FRANCISCO)
				.putAll(Cities.HONG_KONG, Cities.KOLKATA, Cities.BANGKOK, Cities.TAIPEI, Cities.MANILA, Cities.HO_CHI_MINH_CITY, Cities.SHANGHAI)
				.putAll(Cities.TAIPEI, Cities.MANILA, Cities.HONG_KONG, Cities.OSAKA, Cities.SHANGHAI)
				.putAll(Cities.OSAKA, Cities.TAIPEI, Cities.TOKYO)
				.putAll(Cities.SHANGHAI, Cities.HONG_KONG, Cities.TAIPEI, Cities.TOKYO, Cities.BEIJING, Cities.SEOUL)
				.putAll(Cities.TOKYO, Cities.OSAKA, Cities.SHANGHAI, Cities.SEOUL, Cities.SAN_FRANCISCO)
				.putAll(Cities.BEIJING, Cities.SHANGHAI, Cities.SEOUL)
				.putAll(Cities.SEOUL, Cities.BEIJING, Cities.TOKYO, Cities.SHANGHAI)
				.putAll(Cities.LOS_ANGELES, Cities.SAN_FRANCISCO, Cities.SYDNEY, Cities.CHICAGO, Cities.MEXICO_CITY)
				.putAll(Cities.MEXICO_CITY, Cities.LOS_ANGELES, Cities.CHICAGO, Cities.MIAMI, Cities.BOGOTA, Cities.LIMA)
				.putAll(Cities.MIAMI, Cities.ATLANTA, Cities.MEXICO_CITY, Cities.BOGOTA, Cities.WASHINGTON)
				.putAll(Cities.LIMA, Cities.MEXICO_CITY, Cities.BOGOTA, Cities.SANTIAGO)
				.putAll(Cities.SANTIAGO, Cities.LIMA)
				.putAll(Cities.BOGOTA, Cities.MEXICO_CITY, Cities.MIAMI, Cities.LIMA, Cities.BUENOS_AIRES, Cities.SAO_PAULO)
				.putAll(Cities.BUENOS_AIRES, Cities.BOGOTA, Cities.SAO_PAULO)
				.putAll(Cities.SAO_PAULO, Cities.BOGOTA, Cities.BUENOS_AIRES, Cities.MADRID, Cities.LAGOS)
				.putAll(Cities.LAGOS, Cities.SAO_PAULO, Cities.KHARTOUM, Cities.KINSHASA)
				.putAll(Cities.KINSHASA, Cities.LAGOS, Cities.KHARTOUM, Cities.JOHANNESBURG)
				.putAll(Cities.JOHANNESBURG, Cities.KINSHASA, Cities.KHARTOUM)
				.putAll(Cities.KHARTOUM, Cities.CAIRO, Cities.LAGOS, Cities.KINSHASA, Cities.JOHANNESBURG)
				.build();
		
		return new Board(cities.build(), connectivity);
	}
}
