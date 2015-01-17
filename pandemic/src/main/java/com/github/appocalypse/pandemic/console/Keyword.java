package com.github.appocalypse.pandemic.console;

public enum Keyword {
	QUIT("quit"),
	BUILD("build"),
	MOVE("move"),
	PLAYER("player"),
	DISCOVER("discover"),
	TREAT("treat"),
	DISCARD("discard"),
	INFECT("infect"),
	DRAW("draw"),
	INFECTION("infection"),
	DECK("deck"),
	;
	
	final private String val;
	
	private Keyword(String val) {
		this.val = val;
	}
	
	public String getVal() {
		return val;
	}
}
