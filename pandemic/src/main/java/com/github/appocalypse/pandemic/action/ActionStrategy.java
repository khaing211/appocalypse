package com.github.appocalypse.pandemic.action;

import java.util.Arrays;

import com.github.kn.commons.guava.extra.GuavaCollectors;
import com.github.appocalypse.pandemic.event.Event;
import com.google.common.collect.ImmutableList;

@FunctionalInterface
public interface ActionStrategy {
	public ImmutableList<Event> toEvents(Action action, String buffer);
	
	public static ActionStrategy events(Event ... events) {
		return (action, buffer) -> Arrays.stream(events).collect(GuavaCollectors.toImmutableList());
	}
	
	public static ActionStrategy nullActionStragety() {
		return NullActionStrategy.INSTANCE;
	}
	
	public static enum NullActionStrategy implements ActionStrategy {
		INSTANCE;

		@Override
		public ImmutableList<Event> toEvents(Action action, String buffer) {
			return ImmutableList.of();
		}
	}
}
