package com.github.appocalypse.pandemic;

import java.util.Arrays;
import java.util.List;

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
	
	public List<City> getNeighborCities(City city) {
		return connectivity.get(city);
	}
	
	static public Board createPandemicBoard() {
		ImmutableList.Builder<City> cities = ImmutableList.<City>builder();

		Arrays.stream(Cities.values()).forEach(city -> cities.add(city));
		
		ImmutableListMultimap<City, City> connectivity = ImmutableListMultimap.<City, City>builder()
				.putAll(Cities.ATLANTA, Cities.WASHINGTON, Cities.CHICAGO, Cities.MIAMI)
				.putAll(Cities.CHICAGO, Cities.MONTREAL, Cities.ATLANTA, Cities.MEXICO_CITY, Cities.LOS_ANGELES, Cities.SAN_FRANCISCO)
				.build();
		
		return new Board(cities.build(), connectivity);
	}
}
