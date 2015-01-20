package com.github.appocalypse.pandemic.action;

import java.util.regex.Pattern;
import java.util.stream.Stream;

import com.github.appocalypse.guava.extra.GuavaCollectors;
import com.github.appocalypse.pandemic.event.Event;
import com.github.appocalypse.pandemic.token.Token;
import com.google.common.collect.ImmutableList;

public class Action {
	final private String representation;
	final private ActionStrategy actionStragety;
	final private ImmutableList<Token> tokens;
	
	public Action(String representation, ActionStrategy actionStragety, ImmutableList<Token> tokens) {
		this.representation = representation;
		this.actionStragety = actionStragety;
		this.tokens = tokens;
	}
	
	public String getRepresentation() {
		return representation;
	}

	public ImmutableList<Token> getTokens() {
		return tokens;
	}
	
	public ImmutableList<Event> toEvents(String buffer) {
		return actionStragety.toEvents(this, buffer);
	}
	
	// return true for complete match
	public boolean match(String buffer) {
		String[] stringTokens = buffer.split(" ");
		if (stringTokens.length != tokens.size()) {
			return false;
		} else {
			for (int i = 0; i < stringTokens.length; i++) {
				Token token = tokens.get(i);
				String stringToken = stringTokens[i];
				if (token.values(stringToken).size() != 1) {
					return false;
				}
			}
			
			return true;
		}
	}
	
	
	// return suggestions for partial match
	public ImmutableList<String> next(String buffer) {
		Stream<String> streamTokens = Pattern.compile(" ")
				.splitAsStream(buffer);
		
		if (buffer.endsWith(" ")) {
			streamTokens = Stream.concat(streamTokens, Stream.of(""));
		}
		
		ImmutableList<String> stringTokens = streamTokens
				.collect(GuavaCollectors.toImmutableList());
		
		if (stringTokens.size() > tokens.size()) {
			return ImmutableList.of();
		} else {
			
			for (int i = 0; i < stringTokens.size() - 1; i++) {
				Token token = tokens.get(i);
				String stringToken = stringTokens.get(i);
				
				ImmutableList<String> suggestions = token.values(stringToken);
				
				if (suggestions.size() != 1) {
					return ImmutableList.of();
				}
			}
			
			final int lastIndex = stringTokens.size() - 1;
			Token token = tokens.get(Math.max(0, lastIndex));
			String stringToken = lastIndex < 0 ? "" : stringTokens.get(lastIndex);
			
			return token.values(stringToken);
		}
	}
	
	@Override
	public String toString() {
		return "Action [" + representation + "]";
	}
}