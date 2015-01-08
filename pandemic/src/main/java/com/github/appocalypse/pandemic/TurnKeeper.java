package com.github.appocalypse.pandemic;

import com.github.appocalypse.pandemic.role.Role;
import com.google.common.collect.ImmutableList;

public class TurnKeeper {
	final private Player[] players;
	final private int counter;
	
	private TurnKeeper(int counter, Player... players) {
		this.counter = counter;
		this.players = players;
	}
	
	public static TurnKeeper inOrder(Player... players) {
		if (players.length == 0) throw new IllegalArgumentException("Number of players cannot be zero");
		return new TurnKeeper(0, players);
	}
	
	public Player current() {
		return players[counter];
	}
	
	public TurnKeeper next() {
		return new TurnKeeper((counter + 1) % players.length, players);
	}
	
	public ImmutableList<Player> all() {
		return ImmutableList.copyOf(players);
	}
	
	public TurnKeeper swapRole(Role oldRole, Role newRole) {
		Player[] newPlayers = new Player[players.length];
		for (int i = 0; i < players.length; i++) {
			if (players[i].getRole().getLabel().equals(oldRole.getLabel())) {
				newPlayers[i] = players[i].swapRole(newRole);
			} else {
				newPlayers[i] = players[i];
			}
		}
		return new TurnKeeper(counter, newPlayers);
	}
}
