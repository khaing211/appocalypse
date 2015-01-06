package com.github.appocalypse.pandemic;

import com.google.common.collect.ImmutableMap;

/**
 * Scenario is an immutable stage of a game. i.e. current snapshot of the game
 * including
 * 
 * cityStats = number of diseases per color per city
 * location = where all player are at
 * location.entries() = list of players
 * player = number of card holding and role 
 * discard player cards
 * discard infect cards
 * remove from play cards
 */
public class Scenario {
	final private ImmutableMap<City, CityStats> cityStats;
	final private ImmutableMap<Player, City> locations;
	final private Player activePlayer;
	
	public Scenario(ImmutableMap<City, CityStats> cityStats, 
			ImmutableMap<Player, City> locations,
			Player activePlayer) {
		this.cityStats = cityStats;
		this.locations = locations;
		this.activePlayer = activePlayer;
	}
	
	public long getResearchStationCount() {
		return cityStats.entrySet().stream().parallel()
				.filter(entry -> entry.getValue().hasResearchStation()).count();
	}
	
	public boolean hasResearchStation(City city) {
		return cityStats.getOrDefault(city, CityStats.empty()).hasResearchStation();
	}
	
	public int getRedDiseaseCount(City city) {
		return cityStats.getOrDefault(city, CityStats.empty()).getRedDiseaseCount();
	}
	
	public int getBlueDiseaseCount(City city) {
		return cityStats.getOrDefault(city, CityStats.empty()).getBlueDiseaseCount();
	}
	
	public int getBlackDiseaseCount(City city) {
		return cityStats.getOrDefault(city, CityStats.empty()).getBlackDiseaseCount();
	}
	
	public int getYellowDiseaseCount(City city) {
		return cityStats.getOrDefault(city, CityStats.empty()).getYellowDiseaseCount();
	}
	
}
