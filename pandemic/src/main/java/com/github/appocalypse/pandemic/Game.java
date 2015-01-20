package com.github.appocalypse.pandemic;

import com.github.appocalypse.pandemic.event.Event;

public class Game {
	private Scenario currentScenario;

	public Scenario getCurrentScenario() {
		return currentScenario;
	}
	
	public void process(Event event) {
		currentScenario = event.actUpon(currentScenario);
	}
	
	private Game(Scenario currentScenario) {
		this.currentScenario = currentScenario;
	}
	
	public static Game newGame() {
		return new Game(Scenario.zero());
	}

}
