package com.github.appocalypse.pandemic;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

public class CityStats {
	final private ImmutableMap<DiseaseColor, Integer> diseaseCount;
	final private boolean researchStation;
	
	private CityStats(ImmutableMap<DiseaseColor, Integer> diseaseCount, boolean researchStation) {
		this.diseaseCount = diseaseCount;
		this.researchStation = researchStation;
	}
	
	public ImmutableMap<DiseaseColor, Integer> getDiseaseCount() {
		return diseaseCount;
	}

	public int getCount(DiseaseColor diseaseColor) {
		return diseaseCount.getOrDefault(diseaseColor, Integer.valueOf(0));
	}

	public boolean hasResearchStation() {
		return researchStation;
	}
	
	public static CityStats empty() {
		return new CityStats(ImmutableMap.of(), false);
	}
	
	public static Builder copy(CityStats cityStats) {
		return builder()
				.withDiseaseCount(cityStats.getDiseaseCount())
				.withResearchStation(cityStats.hasResearchStation());
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {
		private boolean researchStation;
		private ImmutableMap.Builder<DiseaseColor, Integer> diseaseCount = ImmutableMap.builder();
		
		public Builder withDiseaseCount(DiseaseColor color, int count) {
			diseaseCount.put(color, count);
			return this;
		}
		
		public Builder withDiseaseCount(Map<DiseaseColor, Integer> diseaseCount) {
			this.diseaseCount.putAll(diseaseCount);
			return this;
		}
		
		public Builder withResearchStation(boolean researchStation) {
			this.researchStation = researchStation;
			return this;
		}
		
		public CityStats build() {
			return new CityStats(diseaseCount.build(), researchStation);
		}
	}
}
