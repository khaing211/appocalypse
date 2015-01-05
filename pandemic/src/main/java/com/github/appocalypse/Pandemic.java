package com.github.appocalypse;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import jline.console.ConsoleReader;
import jline.console.completer.Completer;

public class Pandemic {

	public static void main(String[] args) throws Exception {
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
