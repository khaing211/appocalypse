package com.github.appocalypse.pandemic;

import java.util.Arrays;
import java.util.Optional;

import com.google.common.collect.ImmutableList;

public class TurnKeeper {
	final private Player[] players;
	final private int counter;
	
	private TurnKeeper(int counter, Player... players) {
		this.counter = counter;
		this.players = players;
	}
	
	public static TurnKeeper inOrder(Player... players) {
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
	
	public Optional<Player> getPlayerByName(String name) {
		return Arrays.stream(players).filter(player -> player.getName().equals(name)).findAny();
	}
}
