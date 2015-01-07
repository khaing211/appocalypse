package com.github.appocalypse.pandemic.console;

import java.util.Arrays;
import java.util.stream.Collectors;

import jline.console.completer.StringsCompleter;

import com.github.appocalypse.pandemic.Cities;

public class CitesCompleter extends StringsCompleter {
	public CitesCompleter() {
		super(Arrays.stream(Cities.values()).map(city -> city.getName()).collect(Collectors.toList()));
	}
}
