package com.github.appocalypse.jsongrep.impl.predicate;

import java.util.Comparator;
import java.util.function.Predicate;

import javax.json.JsonValue;

public interface JsonValuePredicateFactory {
	public static Predicate<JsonValue> create(final JsonValueExtractor left, final String operator, final JsonValueExtractor right) {
		
		final Comparator<JsonValue> comparator = new JsonValueComparator();
		
		return new Predicate<JsonValue>() {
			@Override
			public boolean test(JsonValue source) {
				final JsonValue leftValue = left.apply(source);
				final JsonValue rightValue = right.apply(source);
				
				final int ret = comparator.compare(leftValue, rightValue);
				
				if ("!=".equals(operator)) {
					return ret != 0;
				} else if (">".equals(operator)) {
					return ret > 0;
				} else if (">=".equals(operator)) {
					return ret >= 0;
				} else if ("<".equals(operator)) {
					return ret < 0;
				} else if ("<=".equals(operator)) {
					return ret <= 0;
				} else {
					return ret == 0;
				}
			}
		};
	}
}
