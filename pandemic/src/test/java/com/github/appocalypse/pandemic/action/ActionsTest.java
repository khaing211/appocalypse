package com.github.appocalypse.pandemic.action;

import static org.junit.Assert.*;

import org.junit.Test;

public class ActionsTest {

	@Test
	public void test() {
		Actions.all()
			.stream()
			.peek(System.out::println)
			.flatMap(it -> it.getTokenEater().eat(0, "build a research station", true).stream())
			.forEach(System.out::println);
		
		//Actions.all().get(1).getTokenEater().eat(0, "build", true).stream().forEach(System.out::println);
	}

}
