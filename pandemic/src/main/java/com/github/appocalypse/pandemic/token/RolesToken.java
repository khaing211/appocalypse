package com.github.appocalypse.pandemic.token;

import java.util.Arrays;

import com.github.kn.commons.guava.extra.GuavaCollectors;
import com.github.appocalypse.pandemic.Role;
import com.github.appocalypse.pandemic.Roles;
import com.google.common.collect.ImmutableList;

public enum RolesToken implements Token {
	INSTANCE;

	final private Token delegate;

	private RolesToken() {
		delegate = new GroupToken(Arrays.stream(Roles.values())
				.map(Role::getName)
				.collect(GuavaCollectors.toImmutableList()));
	}
	
	@Override
	public ImmutableList<String> values(String prefix) {
		return delegate.values(prefix);
	}

}
