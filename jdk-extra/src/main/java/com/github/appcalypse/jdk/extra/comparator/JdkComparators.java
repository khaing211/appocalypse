package com.github.appcalypse.jdk.extra.comparator;

import java.util.Comparator;

public interface JdkComparators {
	public static <T> Comparator<T> allIdentical() {
		return (i, j) -> 0;
	}
}
