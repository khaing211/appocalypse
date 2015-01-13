package com.github.appocalypse.pandemic.card;

import com.github.appocalypse.pandemic.City;

public class PlayerCityCard implements PlayerCard {
	final private City city;
	
	public PlayerCityCard(City city) {
		this.city = city;
	}
	
	public City getCity() {
		return city;
	}
	
	@Override
	public void accept(VoidVisitor visitor) {
		visitor.visit(this);
	}
	
	@Override
	public int accept(IntVisitor visitor) {
		return visitor.visit(this);
	}

}
