package com.github.appocalypse.jsongrep.impl;

import com.github.appocalypse.jsongrep.JsonMatcher;

import javax.json.JsonValue;

public interface JsonMatcherFactory {
    public JsonMatcher fromRoot(JsonValue root);
}
