package com.github.appocalypse.pandemic.console;

public enum Keyword {
	QUIT("quit"),
	BUILD("build"),
	MOVE("move"),
	MOVE_PLAYER("move player"),
	DISCOVER("discover"),
	TREAT("treat"),
	DISCARD("discard"),
	INFECT("infect"),
	DRAW_INFECTION_DECK("draw infection deck"),
	DRAW_PLAYER_DECK("draw player deck"),
	;
	
	final private String val;
	
	private Keyword(String val) {
		this.val = val;
	}
	
	public String getVal() {
		return val;
	}
}
