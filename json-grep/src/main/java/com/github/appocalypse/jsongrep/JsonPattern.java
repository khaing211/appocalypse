package com.github.appocalypse.jsongrep;

import com.github.appocalypse.jsongrep.impl.JsonMatcherFactory;
import com.github.appocalypse.jsongrep.impl.JsonRootMatcherFactory;

import javax.json.JsonStructure;

public class JsonPattern {
    final private JsonMatcherFactory jsonMatcherFactory;

    public JsonPattern(JsonMatcherFactory jsonMatcherFactory) {
        this.jsonMatcherFactory = jsonMatcherFactory;
    }

    public JsonMatcher matcher(JsonStructure root) {
        if (root == null) {
            throw new NullPointerException("root cannot be null");
        }

        return jsonMatcherFactory.fromRoot(root);
    }

    public static JsonPattern compile(String pattern) {
        if (pattern == null) throw new NullPointerException("pattern cannot be null");
        // parse pattern
        return new JsonPattern(new JsonRootMatcherFactory());
    }
}
