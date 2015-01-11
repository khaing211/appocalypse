package com.github.appocalypse.pandemic.role;

import com.github.appocalypse.pandemic.Constant;

public class Archivist implements Role {

	@Override
	public String getLabel() {
		return Constant.RoleLabel.ARCHIVIST;
	}

}
