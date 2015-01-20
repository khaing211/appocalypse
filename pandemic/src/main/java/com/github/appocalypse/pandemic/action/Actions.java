package com.github.appocalypse.pandemic.action;

import static com.github.appocalypse.pandemic.token.Tokens.action;
import static com.github.appocalypse.pandemic.token.Tokens.of;
import static com.github.appocalypse.pandemic.action.ActionStrategy.events;

import com.github.appocalypse.pandemic.Keyword;
import com.github.appocalypse.pandemic.event.BuildEvent;
import com.github.appocalypse.pandemic.event.QuitEvent;
import com.github.appocalypse.pandemic.token.Token;
import com.google.common.collect.ImmutableList;

public class Actions {
	final static private ImmutableList<Action> actions;
	static {
		actions = new Builder()
			.add("build a research station", events(BuildEvent.INSTANCE), action(of(Keyword.BUILD), of(Keyword.A), of(Keyword.RESEARCH), of(Keyword.STATION)))
			.add("discover a cure", action(of(Keyword.DISCOVER), of(Keyword.A), of(Keyword.CURE)))
			.add("quit", events(QuitEvent.INSTANCE), action(of(Keyword.QUIT)))
			.build();
	}
	
	public static ImmutableList<Action> all() {
		return actions;
	}
	
	private static class Builder {
		private ImmutableList.Builder<Action> commands = ImmutableList.builder();
		
		public Builder add(String value, ImmutableList<Token> tokens) {
			commands.add(new Action(value, ActionStrategy.nullActionStragety(), tokens));
			return this;
		}
		
		public Builder add(String value, ActionStrategy actionStrategy, ImmutableList<Token> tokens) {
			commands.add(new Action(value, actionStrategy, tokens));
			return this;
		}
		
		public ImmutableList<Action> build() {
			return commands.build();
		}
	}
}
