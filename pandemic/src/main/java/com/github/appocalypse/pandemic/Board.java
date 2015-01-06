package com.github.appocalypse.pandemic;

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
		ImmutableList<City> cities = ImmutableList.<City>builder()
				.add(Cities.ATLANTA)
				.build();
		
		ImmutableListMultimap<City, City> connectivity = ImmutableListMultimap.<City, City>builder()
				.putAll(Cities.ATLANTA, Cities.WASHINGTON, Cities.CHICAGO, Cities.MEXICO_CITY)
				.build();
		
		return new Board(cities, connectivity);
	}
}
