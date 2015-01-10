package com.github.appocalypse.pandemic;

import java.util.Optional;

import com.github.appocalypse.pandemic.card.InfectionCard;
import com.github.appocalypse.pandemic.card.PlayerCard;
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
	final private ImmutableList<InfectionCard> discardInfectedCards;
	final private ImmutableList<PlayerCard> discardPlayerCards;
	final private ImmutableList<Card> removeFromPlayCards;
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
	public Scenario outbreakAt(City city) {
		// TODO:
		return this;
	}
	
	public Board getBoard() {
		return board;
	}

	public ImmutableMap<City, CityStats> getCityStats() {
		return cityStats;
	}

	public ImmutableMap<Player, City> getLocations() {
		return locations;
	}

	public ImmutableList<InfectionCard> getDiscardInfectedCards() {
		return discardInfectedCards;
	}

	public ImmutableList<Card> getRemoveFromPlayCards() {
		return removeFromPlayCards;
	}

	public TurnKeeper getTurnKeeper() {
		return turnKeeper;
	}

	public InfectionRateCounter getInfectionRateCounter() {
		return infectionRateCounter;
	}

	public OutbreakCounter getOutbreakCounter() {
		return outbreakCounter;
	}
	
	private Scenario(Builder builder) {
		this.board = Optional.ofNullable(builder.board).orElse(Board.createPandemicBoard());
		this.cityStats = Optional.ofNullable(builder.cityStats).orElse(ImmutableMap.of()) ;
		this.locations = Optional.ofNullable(builder.locations).orElse(ImmutableMap.of());
		this.turnKeeper = Optional.ofNullable(builder.turnKeeper).orElse(TurnKeeper.inOrder());
		this.infectionRateCounter = Optional.ofNullable(builder.infectionRateCounter).orElse(InfectionRateCounter.initial());
		this.outbreakCounter = Optional.ofNullable(builder.outbreakCounter).orElse(OutbreakCounter.zero());
		this.discardInfectedCards = Optional.ofNullable(builder.discardInfectedCards).orElse(ImmutableList.of());
		this.discardPlayerCards = Optional.ofNullable(builder.discardPlayerCards).orElse(ImmutableList.of());
		this.removeFromPlayCards = Optional.ofNullable(builder.removeFromPlayCards).orElse(ImmutableList.of());
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static Builder copyOf(Scenario scenario) {
		return new Builder();
	}
	
	/**
	 * The zero scenario
	 * + just the board
	 * + infection rate initial
	 * + outbreak counter at zero
	 */
	public static Scenario zero() {
		return builder().build();
	}
	
	public static class Builder {
		private Board board;
		private ImmutableMap<City, CityStats> cityStats;
		private ImmutableMap<Player, City> locations;
		private ImmutableList<InfectionCard> discardInfectedCards;
		private ImmutableList<PlayerCard> discardPlayerCards;
		private ImmutableList<Card> removeFromPlayCards;
		private TurnKeeper turnKeeper;
		private InfectionRateCounter infectionRateCounter;
		private OutbreakCounter outbreakCounter;
		
		public Builder withBoard(Board board) {
			this.board = board;
			return this;
		}
		
		public Builder withCityStats(ImmutableMap<City, CityStats> cityStats) {
			this.cityStats = cityStats;
			return this;
		}
		
		public Builder withLocations(ImmutableMap<Player, City> locations) {
			this.locations = locations;
			return this;
		}
		
		public Builder withDiscardInfectCards(ImmutableList<InfectionCard> discardInfectCards) {
			this.discardInfectedCards = discardInfectCards;
			return this;
		}
		
		public Builder withRemoveFromPlayCards(ImmutableList<Card> removeFromPlayCards) {
			this.removeFromPlayCards = removeFromPlayCards;
			return this;
		}
		
		public Builder withTurnKeeper(TurnKeeper turnKeeper) {
			this.turnKeeper = turnKeeper;
			return this;
		}
		
		public Builder withInfectionRateCounter(InfectionRateCounter infectionRateCounter) {
			this.infectionRateCounter = infectionRateCounter;
			return this;
		}
		
		public Builder withOutbreakCounter(OutbreakCounter outbreakCounter) {
			this.outbreakCounter = outbreakCounter;
			return this;
		}
		
		public Scenario build() {
			return new Scenario(this);
		}
	}
}
