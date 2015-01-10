package com.github.appocalypse.pandemic.card;

import com.github.appocalypse.pandemic.Card;

public class RedEpidemicPlayerCard extends EpidemicPlayerCard implements Card {
	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

}
