package com.github.appocalypse.jsongrep.impl;

import com.github.appocalypse.jsongrep.JsonMatcher;

import javax.json.JsonStructure;
import javax.json.JsonValue;

public class DefaultJsonMatcher implements JsonMatcher {
    final private JsonStructure root;
    final private JsonSelector pattern;

    public DefaultJsonMatcher(JsonStructure root, JsonSelector pattern) {
        this.root = root;
        this.pattern = pattern;
    }

    @Override
    public boolean find() {
        return false;
    }

    @Override
    public JsonValue current() {
        return null;
    }
}
