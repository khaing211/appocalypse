package com.github.appocalypse.pandemic.card;


public interface Card {
	
	public void accept(VoidVisitor visitor);
	public int accept(IntVisitor visitor);

	
	public static interface VoidVisitor {
		public default void visit(InfectionCard infectionCard) {}
		public default void visit(PlayerCityCard playerCard) {}
		public default void visit(EpidemicPlayerCard epidemicCard) {}
		public default void visit(EventPlayerCard eventPlayerCard) {}
		public default void visit(RedEpidemicPlayerCard redEpidemicard) {}
	}
	
	public static interface IntVisitor {
		public default int visit(InfectionCard infectionCard) { return 0; }
		public default int visit(PlayerCityCard playerCard) { return 0; }
		public default int visit(EpidemicPlayerCard epidemicCard) { return 0; }
		public default int visit(EventPlayerCard eventPlayerCard) { return 0; }
		public default int visit(RedEpidemicPlayerCard redEpidemicard) { return 0; }
	}
}
