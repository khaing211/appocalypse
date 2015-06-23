package com.github.appocalypse.pandemic.action;

import java.util.Arrays;

import com.github.kn.commons.guava.extra.GuavaCollectors;
import com.github.appocalypse.pandemic.event.Event;
import com.google.common.collect.ImmutableList;

@FunctionalInterface
public interface ActionStrategy {
	ImmutableList<Event> toEvents(Action action, String buffer);
	
	static ActionStrategy events(Event ... events) {
		return (action, buffer) -> Arrays.stream(events).collect(GuavaCollectors.toImmutableList());
	}
	
	static ActionStrategy nullActionStragety() {
		return NullActionStrategy.INSTANCE;
	}
	
	enum NullActionStrategy implements ActionStrategy {
		INSTANCE;

		@Override
		public ImmutableList<Event> toEvents(Action action, String buffer) {
			return ImmutableList.of();
		}
	}
}
