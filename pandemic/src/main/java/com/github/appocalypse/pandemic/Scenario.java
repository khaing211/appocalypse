package com.github.appocalypse.pandemic;

import com.google.common.collect.ImmutableList;
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
 * infection rate
 * outbreak count
 */
public class Scenario {
	final private Board board;
	final private ImmutableMap<City, CityStats> cityStats;
	final private ImmutableMap<Player, City> locations;
	final private ImmutableList<City> discardInfectedCard;
	final private TurnKeeper turnKeeper;
	final private InfectionRateCounter infectionRateCounter;
	final private OutbreakCounter outbreakCounter;
	
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
	
	public int getDieaseCubeCount(RegionColor regionColor) {
		return Constant.MAX_COUNT_PER_DISEASE - getDiseaseCount(regionColor);
	}
	
	public int getDiseaseCount(RegionColor regionColor) {
		// TODO:
		return 0;
	}
	
	public int getUndrawnCityPlayerCardCount(RegionColor regionColor) {
		// TODO:
		return 0;
	}
	
	public Player getCurrentPlayer() {
		return turnKeeper.current();
	}
	
	public int getCurrentInfectionRate() {
		return infectionRateCounter.current();
	}
	
	public int getCurrentOutbreakCount() {
		return outbreakCounter.current();
	}
	
	// Immutable change
	public Scenario increaseOutbreakCount() {
		// TODO:
		return this;
	}
	
	public Scenario(Builder builder) {
		this.board = builder.board;
		this.cityStats = builder.cityStats;
		this.locations = builder.locations;
		this.turnKeeper = builder.turnKeeper;
		this.infectionRateCounter = builder.infectionRateCounter;
		this.outbreakCounter = builder.outbreakCounter;
		this.discardInfectedCard = builder.discardInfectedCard;
	}
	
	public static class Builder {
		private Board board;
		private ImmutableMap<City, CityStats> cityStats;
		private ImmutableMap<Player, City> locations;
		private ImmutableList<City> discardInfectedCard;
		private TurnKeeper turnKeeper;
		private InfectionRateCounter infectionRateCounter;
		private OutbreakCounter outbreakCounter;
	}
}
