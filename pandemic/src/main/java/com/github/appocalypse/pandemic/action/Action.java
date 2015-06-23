package com.github.appocalypse.pandemic.action;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import com.github.kn.commons.guava.extra.GuavaCollectors;
import com.github.appocalypse.pandemic.event.Event;
import com.github.appocalypse.pandemic.token.Token;
import com.google.common.collect.ImmutableList;

public class Action {
    private final static Pattern WHITESPACE = Pattern.compile("^(\\s+)");

    private final String representation;
	private final ActionStrategy actionStragety;
	private final ImmutableList<Token> tokens;
	
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
	
	/**
	 * @param buffer
	 * @return true for complete match
	 */
	public boolean match(String buffer) {
        for (int i = 0; i < tokens.size() - 1 ; i++) {
            final Token token = tokens.get(i);
            final ImmutableList<String> values = token.values(buffer);
            //  match only one
            if (values.size() != 1) {
                return false;
            }

            // remove token
            final String value = values.get(0);
            if (value.length() > buffer.length()) {
                return false;
            }

            buffer = buffer.substring(value.length());

            // find whitespace
            final Matcher matcher = WHITESPACE.matcher(buffer);
            if (!matcher.find()) {
                return false;
            }

            // remove whtiespace
            buffer = buffer.substring(matcher.group().length());
        }

        // remove the check in !matcher.find() by checking lastToken specifically
        final Token lastToken = tokens.get(tokens.size() - 1);
        final ImmutableList<String> values = lastToken.values(buffer);
        return values.size() == 1;
	}
	
	/**
	 * @param buffer
	 * @return suggestions for partial match
	 */
	public ImmutableList<String> next(String buffer) {
        ImmutableList<String> ret  = ImmutableList.of();

        int tokenIndex = 0;

        while (!buffer.isEmpty() && tokenIndex < tokens.size()) {
            // we don't handle tree of prefixes
            if (ret.size() > 1) {
                return ImmutableList.of();
            }

            final Token token = tokens.get(tokenIndex);
            ret = token.values(buffer);
            //  should match more than 1
            if (ret.size() == 0) {
                return ImmutableList.of();
            }

            // remove token
            final String value = ret.get(0);
            if (value.length() > buffer.length()) break;

            // value is longer than buffer
            buffer = buffer.substring(value.length());

            // find whitespace
            final Matcher matcher = WHITESPACE.matcher(buffer);
            if (!matcher.find()) {
                if (!buffer.isEmpty()) {
                    return ImmutableList.of();
                }
            } else {
                // remove whtiespace
                buffer = buffer.substring(matcher.group().length());
            }

            tokenIndex++;
        }

        return ret;
	}
	
	@Override
	public String toString() {
		return "Action [" + representation + "]";
	}
}