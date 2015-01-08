package com.github.appocalypse.pandemic;

import com.github.appocalypse.pandemic.role.Role;
import com.google.common.collect.ImmutableList;

public class Player {
	final private Role role;
	final private ImmutableList<PlayerCard> hand;
	
	public Player(Role role, ImmutableList<PlayerCard> hand) {
		this.hand = hand;
		this.role = role;
	}
	
	public int getCardInHandCount() {
		return hand.size();
	}
	
	public Role getRole() {
		return role;
	}
	
	public Player swapRole(Role newRole) {
		return this;
	}
}
