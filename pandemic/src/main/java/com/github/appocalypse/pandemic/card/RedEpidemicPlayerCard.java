package com.github.appocalypse.pandemic.card;



public class RedEpidemicPlayerCard extends EpidemicPlayerCard implements Card {
	@Override
	public void accept(VoidVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public int accept(IntVisitor visitor) {
		return visitor.visit(this);
	}
}
