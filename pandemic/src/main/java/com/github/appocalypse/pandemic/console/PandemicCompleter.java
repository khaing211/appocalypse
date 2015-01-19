package com.github.appocalypse.pandemic.console;

import java.util.List;

import jline.console.completer.Completer;

import com.github.appocalypse.pandemic.action.Actions;

public class PandemicCompleter implements Completer {

	@Override
	public int complete(String buffer, int cursor, List<CharSequence> candidates) {
		
		Actions.all()
			.stream()
			.flatMap(it -> it.getTokenEater().eat(0, buffer).stream())
			.filter(it -> it.getEndIndex() > 0)
			.forEach(it -> candidates.add(it.combineCursors().getMatch()));
		
		if (candidates.isEmpty()) {
			candidates.add("no candidate");
		}
		
		return candidates.isEmpty() ? -1 : 0;
	}

}
