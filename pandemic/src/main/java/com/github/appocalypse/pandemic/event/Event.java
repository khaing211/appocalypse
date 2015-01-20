package com.github.appocalypse.pandemic.event;

import com.github.appocalypse.pandemic.Scenario;

/**
 * Event is a game event, not a event card.
 * 
 * Each event will create a new scenario from a given scenario, since each scenario is immutable 
 */
public interface Event {
	public Scenario actUpon(Scenario scenario);
}
