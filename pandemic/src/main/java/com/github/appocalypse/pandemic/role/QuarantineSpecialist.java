package com.github.appocalypse.pandemic.role;

import com.github.appocalypse.pandemic.Constant;

public class QuarantineSpecialist implements Role {

	@Override
	public String getLabel() {
		return Constant.RoleLabel.QUARANTINE_SPECIALIST;
	}

}
