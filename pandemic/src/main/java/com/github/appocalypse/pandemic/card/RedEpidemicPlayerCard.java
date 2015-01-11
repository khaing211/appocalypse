package com.github.appocalypse.pandemic.card;


public class RedEpidemicPlayerCard extends EpidemicPlayerCard implements Card {
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
