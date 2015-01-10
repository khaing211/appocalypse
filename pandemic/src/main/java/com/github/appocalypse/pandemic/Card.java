package com.github.appocalypse.pandemic;

import com.github.appocalypse.pandemic.card.EventPlayerCard;
import com.github.appocalypse.pandemic.card.EpidemicPlayerCard;
import com.github.appocalypse.pandemic.card.InfectionCard;
import com.github.appocalypse.pandemic.card.PlayerCityCard;
import com.github.appocalypse.pandemic.card.RedEpidemicPlayerCard;

public interface Card {
	
	public void accept(Visitor visitor);
	
	public static interface Visitor {
		public default void visit(InfectionCard infectionCard) {}
		public default void visit(PlayerCityCard playerCard) {}
		public default void visit(EpidemicPlayerCard epidemicCard) {}
		public default void visit(EventPlayerCard eventPlayerCard) {}
		public default void visit(RedEpidemicPlayerCard redEpidemicard) {}
	}
}
