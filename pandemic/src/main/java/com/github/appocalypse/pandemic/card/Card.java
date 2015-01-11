package com.github.appocalypse.pandemic.card;


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
