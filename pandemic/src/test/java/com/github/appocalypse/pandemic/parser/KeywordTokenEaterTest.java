package com.github.appocalypse.pandemic.parser;

import static org.junit.Assert.*;

import org.junit.Test;

import com.github.appocalypse.pandemic.Keyword;

public class KeywordTokenEaterTest {

	@Test
	public void test() {
		//TokenEaters.of(Keyword.RESEARCH).eat(0, "rese", true).stream().forEach(System.out::println);
		
		new CaseInsensitiveStringTokenEater("test").eat(0, "tes", true).stream().forEach(System.out::println);
	}

}
