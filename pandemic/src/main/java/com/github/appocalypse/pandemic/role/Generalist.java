package com.github.appocalypse.pandemic.role;

import com.github.appocalypse.pandemic.Constant;

public class Generalist implements Role {

	@Override
	public String getLabel() {
		return Constant.RoleLabel.GENERALIST;
	}

}
