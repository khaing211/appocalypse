package com.github.appocalypse.pandemic.console;

import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import com.github.appocalypse.pandemic.grammars.PandemicLexer;
import com.github.appocalypse.pandemic.grammars.PandemicParser;

import jline.console.completer.Completer;

public class AntlrPandemicCompleter implements Completer {

	@Override
	public int complete(final String buffer, final int cursor, final List<CharSequence> candidates) {
		if (candidates == null) return 0;
		
		final String newBuffer = buffer == null ? "" : buffer.substring(0, cursor);
		
		final ANTLRInputStream antlrInputStream = new ANTLRInputStream(newBuffer);
		
		final PandemicLexer lexer = new PandemicLexer(antlrInputStream);
		final CommonTokenStream commonsTokenStream = new CommonTokenStream(lexer);
		final PandemicParser parser = new PandemicParser(commonsTokenStream);
		
		parser.action();
		
        if (candidates.isEmpty()) {
        	return -1;
        }
		
		return 0;
	}

}
