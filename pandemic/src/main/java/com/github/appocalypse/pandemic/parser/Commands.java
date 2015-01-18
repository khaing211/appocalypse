package com.github.appocalypse.pandemic.parser;

import com.google.common.collect.ImmutableList;

public class Commands {
	final static private ImmutableList<Command> commands;
	static {
		commands = new Builder()
			.build();
	}
	
	public static ImmutableList<Command> commands() {
		return commands;
	}
	
	
	public static class Command {
		final private String value;
		final private TokenEater tokenEater;
		
		public Command(String value, TokenEater tokenEater) {
			this.value = value;
			this.tokenEater = tokenEater;
		}
	}
	
	public static class Builder {
		private ImmutableList.Builder<Command> commands = ImmutableList.builder();
		
		public Builder add(String value, TokenEater tokenEater) {
			commands.add(new Command(value, tokenEater));
			return this;
		}
		
		public ImmutableList<Command> build() {
			return commands.build();
		}
	}
}
