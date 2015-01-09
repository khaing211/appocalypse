package com.github.appocalypse.pandemic;

public class Game {
	private Scenario currentScenario;

	private Game(Scenario currentScenario) {
		this.currentScenario = currentScenario;
	}
	
	public static Game newGame() {
		return null;
	}
}
