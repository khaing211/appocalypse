package com.github.appocalypse.pandemic.console;

import java.util.List;

import com.github.appocalypse.pandemic.action.Actions;

import jline.console.completer.Completer;

public class PandemicCompleter implements Completer {

	@Override
	public int complete(String buffer, int cursor, List<CharSequence> candidates) {
		if (buffer == null) {
			Actions.all()
				.stream()
				.flatMap(it -> it.next("").stream())
				.forEach(candidates::add);
			
			return 0;
		} else {
			final String newBuffer = buffer.substring(0, cursor);
			
			Actions.all()
				.stream()
				.flatMap(it -> it.next(newBuffer).stream())
				.distinct()
				.forEach(candidates::add);
			
	        if (candidates.size() == 1) {
	            candidates.set(0, candidates.get(0) + " ");
	        }
			
	        if (candidates.isEmpty()) {
	        	return -1;
	        } else {
				return newBuffer.lastIndexOf(' ') + 1;
	        }
		}
	}
}
