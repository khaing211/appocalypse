package com.github.appocalypse.pandemic.card;

public class EpidemicPlayerCard implements PlayerCard {

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
