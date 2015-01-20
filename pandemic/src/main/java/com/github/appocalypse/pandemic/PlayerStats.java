package com.github.appocalypse.pandemic;

import com.github.appocalypse.pandemic.card.PlayerCard;
import com.google.common.collect.ImmutableList;

public class PlayerStats {
	final private Role role;
	final private ImmutableList<PlayerCard> hand;
	final private City location;
	
	public PlayerStats(Role role, ImmutableList<PlayerCard> hand, City location) {
		this.hand = hand;
		this.role = role;
		this.location = location;
	}
	
	public int getCardInHandCount() {
		return hand.size();
	}
	
	public Role getRole() {
		return role;
	}
	
	public City getLocation() {
		return location;
	}
	
	public PlayerStats swapRole(Role newRole) {
		return new PlayerStats(newRole, hand, location);
	}
	
	public PlayerStats move(City newLocation) {
		return new PlayerStats(role, hand, newLocation);
	}
}
