package com.github.appocalypse.pandemic.console;

import java.util.List;

import jline.console.completer.Completer;

public class PandemicCompleter implements Completer {

	@Override
	public int complete(String buffer, int cursor, List<CharSequence> candidates) {
		candidates.add(buffer + " " + cursor);
		return candidates.isEmpty() ? -1 : 0;
	}

}
