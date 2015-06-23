package com.github.appocalypse.pandemic.action;

import static com.github.appocalypse.pandemic.token.Tokens.tokens;
import static com.github.appocalypse.pandemic.token.Tokens.of;
import static com.github.appocalypse.pandemic.token.Tokens.cities;
import static com.github.appocalypse.pandemic.action.ActionStrategy.events;

import com.github.appocalypse.pandemic.Keyword;
import com.github.appocalypse.pandemic.event.BuildEvent;
import com.github.appocalypse.pandemic.event.QuitEvent;
import com.github.appocalypse.pandemic.token.Token;
import com.google.common.collect.ImmutableList;

public class Actions {
	private final static ImmutableList<Action> actions;
	static {
		actions = new Builder()
			.add("build a research station", 
					events(BuildEvent.INSTANCE), 
					tokens(of(Keyword.BUILD), of(Keyword.A), of(Keyword.RESEARCH), of(Keyword.STATION)))
			.add("build a research station by discard <city>", 
					tokens(of(Keyword.BUILD), of(Keyword.A), of(Keyword.RESEARCH), of(Keyword.STATION),
							of(Keyword.BY), of(Keyword.DISCARD), cities()))
			.add("direct flight to <city>", tokens(of(Keyword.DIRECT), of(Keyword.FLIGHT), of(Keyword.TO), cities()))
			.add("charter flight from <city>", tokens(of(Keyword.CHARTER), of(Keyword.FLIGHT), of(Keyword.TO), cities()))
			.add("discover a cure by discard <city> <city> <city> <city> <city>", 
					tokens(of(Keyword.DISCOVER), of(Keyword.A), of(Keyword.CURE), of(Keyword.BY), of(Keyword.DISCARD),
							cities(), cities(), cities(), cities(), cities()))
			.add("discover a cure by discard <city> <city> <city> <city>", 
					tokens(of(Keyword.DISCOVER), of(Keyword.A), of(Keyword.CURE), of(Keyword.BY), of(Keyword.DISCARD),
							cities(), cities(), cities(), cities()))
			.add("quit", events(QuitEvent.INSTANCE), tokens(of(Keyword.QUIT)))
			.build();
	}
	
	public static ImmutableList<Action> all() {
		return actions;
	}
	
	private static class Builder {
		private final ImmutableList.Builder<Action> actions = ImmutableList.builder();
		
		public Builder add(String value, ImmutableList<Token> tokens) {
			actions.add(new Action(value, ActionStrategy.nullActionStragety(), tokens));
			return this;
		}
		
		public Builder add(String value, ActionStrategy actionStrategy, ImmutableList<Token> tokens) {
			actions.add(new Action(value, actionStrategy, tokens));
			return this;
		}
		
		public ImmutableList<Action> build() {
			return actions.build();
		}
	}
}
