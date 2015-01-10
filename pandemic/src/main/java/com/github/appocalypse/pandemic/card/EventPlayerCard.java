package com.github.appocalypse.pandemic.card;

public class EventPlayerCard implements PlayerCard {

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
