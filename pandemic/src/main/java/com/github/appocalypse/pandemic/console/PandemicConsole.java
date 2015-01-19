package com.github.appocalypse.pandemic.console;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import jline.console.ConsoleReader;
import jline.console.completer.Completer;

import com.github.appocalypse.pandemic.Game;

public class PandemicConsole {

	public void run() throws IOException {
		final ConsoleReader reader = new ConsoleReader();
		reader.setPrompt("pandemic> ");

		completers().forEach(completer -> reader.addCompleter(completer));
		
		final PrintWriter out = new PrintWriter(reader.getOutput());
		
		final Game game = Game.newGame(); 
		
		String line = null;
		while (!game.getCurrentScenario().isGameOver() &&  
				((line = reader.readLine()) != null)) {
			
			out.println(line);
			
			out.flush();
		}
	}
	
	private List<Completer> completers() {
		final List<Completer> completers = new LinkedList<Completer>();
		completers.add(new PandemicCompleter());
		return completers;
	}
}
