package com.github.appocalypse.jsongrep;

import com.github.appocalypse.jsongrep.impl.DefaultJsonMatcher;
import com.github.appocalypse.jsongrep.impl.JsonRootSelector;
import com.github.appocalypse.jsongrep.impl.JsonSelector;

import javax.json.JsonStructure;

public class JsonPattern {
    final private JsonSelector selector;

    private JsonPattern(JsonSelector selector) {
        this.selector = selector;
    }

    public JsonMatcher matcher(JsonStructure root) {
        return new DefaultJsonMatcher(root, selector);
    }

    public static JsonPattern compile(String pattern) {
        if (pattern == null) throw new NullPointerException("pattern cannot be null");
        // parse pattern
        return new JsonPattern(new JsonRootSelector());
    }
}
