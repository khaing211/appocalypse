package com.github.appocalypse.pandemic.card;

import com.github.appocalypse.pandemic.City;

public class InfectionCard implements Card {

	final private City city;
	
	private InfectionCard(City city) {
		this.city = city;
	}

	public static InfectionCard of(City city) {
		return new InfectionCard(city);
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
