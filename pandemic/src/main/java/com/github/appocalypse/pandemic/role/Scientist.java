package com.github.appocalypse.pandemic.role;

import com.github.appocalypse.pandemic.Constant;

public class Scientist implements Role {

	@Override
	public String getLabel() {
		return Constant.RoleLabel.SCIENTIST;
	}

}
