package com.github.appocalypse.pandemic;

public class CityStats {
	final private int redDiseaseCount;
	final private int blueDiseaseCount;
	final private int blackDiseaseCount;
	final private int yellowDiseaseCount;
	final private boolean researchStation;
	
	private CityStats(int redDiseaseCount, int blueDiseaseCount, 
			int blackDiseaseCount, int yellowDiseaseCount, boolean researchStation) {
		this.redDiseaseCount = redDiseaseCount;
		this.blueDiseaseCount = blueDiseaseCount;
		this.blackDiseaseCount = blackDiseaseCount;
		this.yellowDiseaseCount = yellowDiseaseCount;
		this.researchStation = researchStation;
	}
	
	public int getRedDiseaseCount() {
		return redDiseaseCount;
	}

	public int getBlueDiseaseCount() {
		return blueDiseaseCount;
	}

	public int getBlackDiseaseCount() {
		return blackDiseaseCount;
	}

	public int getYellowDiseaseCount() {
		return yellowDiseaseCount;
	}

	public boolean hasResearchStation() {
		return researchStation;
	}
	
	public static CityStats empty() {
		return new CityStats(0, 0, 0, 0, false);
	}
	
	public static Builder copy(CityStats cityStats) {
		return builder();
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {
		private int redDiseaseCount;
		private int blueDiseaseCount;
		private int blackDiseaseCount;
		private int yellowDiseaseCount;
		private boolean researchStation;
		
		public Builder withRedDiseaseCount(int redDiseaseCount) {
			this.redDiseaseCount = redDiseaseCount;
			return this;
		}
		
		public Builder withBlueDiseaseCount(int blueDiseaseCount) {
			this.blueDiseaseCount = blueDiseaseCount;
			return this;
		}
		
		public Builder withBlackDiseaseCount(int blackDiseaseCount) {
			this.blackDiseaseCount = blackDiseaseCount;
			return this;
		}
		
		public Builder withYellowDiseaseCount(int yellowDieaseCount) {
			this.yellowDiseaseCount = yellowDieaseCount;
			return this;
		}
		
		public Builder withResearchStation() {
			this.researchStation = true;
			return this;
		}
		
		public CityStats build() {
			return new CityStats(redDiseaseCount, blueDiseaseCount, blackDiseaseCount, 
					yellowDiseaseCount, researchStation);
		}
	}
}
