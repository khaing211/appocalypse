package com.github.appocalypse.pandemic.console;

import static org.junit.Assert.*;

import java.util.LinkedList;

import jline.console.completer.Completer;

import org.junit.Test;

public class PandemicCompleterTest {

	@Test
	public void test() {
		Completer completer = new PandemicCompleter();
		LinkedList<CharSequence> candidates = new LinkedList<CharSequence>();
		
		completer.complete("build a research station", 0, candidates);
		
		candidates.stream().forEach(System.out::println);
	}

}
