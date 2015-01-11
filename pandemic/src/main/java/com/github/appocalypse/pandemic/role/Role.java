package com.github.appocalypse.pandemic.role;

public interface Role {
	public String getLabel();
	
	public static interface Visitor {
		public default void visit(Archivist archivist) {}
		public default void visit(ContainmentSpecialist containmentSpecialist) {}
		public default void visit(ContingencyPlanner contingencyPlanner) {}
		public default void visit(Dispatcher dispatcher) {}
		public default void visit(Epidemiologist epidemiologist) {}
		public default void visit(FieldOperative fieldOperative) {}
		public default void visit(Generalist generalist) {}
		public default void visit(Medic medic) {}
		public default void visit(OperationsExpert operationsExpert) {}
		public default void visit(QuarantineSpecialist quarantineSpecialist) {}
		public default void visit(Researcher researcher) {}
		public default void visit(Scientist scientist) {}
		public default void visit(Troubleshooter troubleshooter) {}
	} 
}
