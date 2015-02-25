package com.github.appocalypse.jsongrep.impl;

import com.github.appocalypse.jsongrep.JsonMatcher;

public class JsonArrayMatcherFactory extends JsonChainMatcherFactory {

    public JsonArrayMatcherFactory(JsonMatcherFactory jsonMatcherFactory) {
        super(jsonMatcherFactory);
    }

    @Override
    protected JsonMatcher fromPreviousMatcher(JsonMatcher previousMatcher) {
        return null;
    }
}
