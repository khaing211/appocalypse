package com.github.appocalypse.pandemic.console;

import java.util.List;

import jline.console.completer.Completer;

public class TokenCompleter implements Completer {

	final private List<String> suggestions;
	
	public TokenCompleter(List<String> suggestions) {
		this.suggestions = suggestions;
	}
	
	@Override
	public int complete(String buffer, int cursor, List<CharSequence> candidates) {
		if (buffer == null) {
			candidates.addAll(suggestions);
			return 0;
		} else {
			// consider buffer from 0-index up to cursor
			// if cursor is at the end, it would be equals to buffer.length()
			// then substring is fine
			buffer = buffer.substring(0, cursor);
			
			// if new buffer endsWith space then split() would not pick up last token is an empty string
			if (buffer.endsWith(" ")) {
				// in which case add all suggestion
				candidates.addAll(suggestions);
				return cursor;
			} else {
				String[] tokens = buffer.split(" ");
				String lastToken = tokens[tokens.length - 1];
				suggestions.stream().filter(it -> it.startsWith(lastToken)).forEach(candidates::add);
				
				// found any candidates then returning insertion point at begining of last token
				return candidates.isEmpty() ? -1 : buffer.length() - lastToken.length();
			}
		}
	}

}
