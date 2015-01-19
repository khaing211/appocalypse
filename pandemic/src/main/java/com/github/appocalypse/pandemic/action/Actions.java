package com.github.appocalypse.pandemic.action;

import static com.github.appocalypse.pandemic.parser.TokenEaters.action;
import static com.github.appocalypse.pandemic.parser.TokenEaters.of;

import com.github.appocalypse.pandemic.Keyword;
import com.github.appocalypse.pandemic.parser.TokenEater;
import com.google.common.collect.ImmutableList;

public class Actions {
	final static private ImmutableList<Action> actions;
	static {
		actions = new Builder()
			.add("build a research station", action(of(Keyword.BUILD), of(Keyword.A), of(Keyword.RESEARCH), of(Keyword.STATION)))
			.add("discover a cure", action(of(Keyword.DISCOVER), of(Keyword.A), of(Keyword.CURE)))
			.add("quit", action(of(Keyword.QUIT)))
			.build();
	}
	
	public static ImmutableList<Action> all() {
		return actions;
	}
	
	
	public static class Action {
		final private String representation;
		final private TokenEater tokenEater;
		
		public Action(String representation, TokenEater tokenEater) {
			this.representation = representation;
			this.tokenEater = tokenEater;
		}
		
		public String getRepresentation() {
			return representation;
		}

		public TokenEater getTokenEater() {
			return tokenEater;
		}
		
		@Override
		public String toString() {
			return "Action [" + representation + "]";
		}
	}
	
	private static class Builder {
		private ImmutableList.Builder<Action> commands = ImmutableList.builder();
		
		public Builder add(String value, TokenEater tokenEater) {
			commands.add(new Action(value, tokenEater));
			return this;
		}
		
		public ImmutableList<Action> build() {
			return commands.build();
		}
	}
}
