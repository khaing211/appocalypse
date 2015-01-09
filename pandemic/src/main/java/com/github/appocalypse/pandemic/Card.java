package com.github.appocalypse.pandemic;

public interface Card<T extends Card<?>> {
	public T as();
}
