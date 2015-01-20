package com.github.appocalypse.pandemic.event;

import com.github.appocalypse.pandemic.Scenario;


public enum QuitEvent implements Event {
	INSTANCE;
	
	@Override
	public Scenario actUpon(Scenario scenario) {
		return scenario.quit();
	}
}
