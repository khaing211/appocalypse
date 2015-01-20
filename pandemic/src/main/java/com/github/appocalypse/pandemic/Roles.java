package com.github.appocalypse.pandemic;

public enum Roles implements Role {
	MEDIC("Medic"),
	OPERATIONS_EXPERT("Operations Expert"),
	DISPATCHER("Dispatcher"),
	SCIENTIST("Scientist"),
	RESEARCHER("Researcher"),
	CONTINGENCY_PLANNER("Contingency Planner"),
	QUARANTINE_SPECIALIST("Quarantine Specialist"),
	FIELD_OPERATIVE("Field Operative"),
	GENERALIST("Generalist"),
	CONTAINMENT_SPECIALIST("Containment Specialist"),
	EPIDEMIOLOGIST("Epidemiologist"),
	ARCHIVIST( "Archivist"),
	TROUBLESHOOTER("Troubleshooter"),
	;
	
	final private String name;

	private Roles(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}
}
