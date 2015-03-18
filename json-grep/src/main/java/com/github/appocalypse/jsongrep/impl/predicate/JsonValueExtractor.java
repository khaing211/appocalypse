package com.github.appocalypse.jsongrep.impl.predicate;

import javax.json.JsonValue;

public interface JsonValueExtractor {
	public JsonValue apply(JsonValue source);
}
