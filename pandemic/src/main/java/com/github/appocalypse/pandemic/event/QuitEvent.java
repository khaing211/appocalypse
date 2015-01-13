package com.github.appocalypse.pandemic.event;

import com.github.appocalypse.pandemic.Scenario;



public class QuitEvent implements Event {
	
	@Override
	public Scenario actUpon(Scenario scenario) throws Exception {
		return scenario.quit();
	}
}
