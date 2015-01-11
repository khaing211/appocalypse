package com.github.appocalypse.pandemic.role;

import com.github.appocalypse.pandemic.Constant;

public class ContingencyPlanner implements Role {

	@Override
	public String getLabel() {
		return Constant.RoleLabel.CONTINGENCY_PLANNER;
	}

}
