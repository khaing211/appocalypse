package com.github.appocalypse.pandemic.card;

public class PlayerCityCard implements PlayerCard {

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
