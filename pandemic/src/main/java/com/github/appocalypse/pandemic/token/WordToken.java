package com.github.appocalypse.pandemic.token;

import com.google.common.collect.ImmutableList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordToken implements Token {
	private final Pattern pattern = Pattern.compile("^(\\w+)");

	@Override
	public ImmutableList<String> values(String buffer) {
		Matcher matcher = pattern.matcher(buffer);
		if (matcher.find()) {
			return ImmutableList.of(matcher.group());
		}
		return ImmutableList.of();
	}

}
