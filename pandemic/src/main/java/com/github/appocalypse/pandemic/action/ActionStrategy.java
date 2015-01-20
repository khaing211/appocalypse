package com.github.appocalypse.pandemic.action;

import com.github.appocalypse.pandemic.event.Event;
import com.google.common.collect.ImmutableList;

@FunctionalInterface
public interface ActionStrategy {
	public ImmutableList<Event> toEvents(Action action);
	
	public static ActionStrategy singleEvent(Event event) {
		return i -> ImmutableList.of(event);
	}
}
