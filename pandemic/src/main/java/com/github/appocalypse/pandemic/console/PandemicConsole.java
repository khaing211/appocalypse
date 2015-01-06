package com.github.appocalypse.pandemic.console;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import jline.console.ConsoleReader;
import jline.console.completer.Completer;

public class PandemicConsole {

	public void run() throws IOException {
		final ConsoleReader reader = new ConsoleReader();
		reader.setPrompt("pandemic> ");
		
		final List<Completer> completors = new LinkedList<Completer>();
		
		completors.forEach(completer -> reader.addCompleter(completer));
		
		final PrintWriter out = new PrintWriter(reader.getOutput());
		
		String line = null;
		while ((line = reader.readLine()) != null) {
			out.println(line);
			
			out.flush();
		}
	}
}
