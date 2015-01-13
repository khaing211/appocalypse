package com.github.appocalypse.pandemic.card;


public class EpidemicPlayerCard implements PlayerCard {

	@Override
	public void accept(VoidVisitor visitor) {
		visitor.visit(this);
	}
	
	@Override
	public int accept(IntVisitor visitor) {
		return visitor.visit(this);
	}

}
