package com.github.appocalypse.jsongrep.impl;

import com.github.appcalypse.jdk.extra.util.Eithers;
import com.github.appocalypse.jsongrep.JsonMatcher;

/**
 * Deal with JsonObject and JsonArray if intValue is not null
 */
public class JsonBracketChildMatcherFactory extends JsonChainMatcherFactory {
    final private String value;
    final private Integer intValue;

    public JsonBracketChildMatcherFactory(JsonMatcherFactory jsonMatcherFactory, String value) {
        super(jsonMatcherFactory);
        this.value = value;
        this.intValue = Eithers.wrap(() -> Integer.parseInt(value), NumberFormatException.class).orLeft(null);
    }

    @Override
    protected JsonMatcher fromPreviousMatcher(JsonMatcher previousMatcher) {
        return new JsonBracketChildMatcher(previousMatcher);
    }

    private class JsonBracketChildMatcher extends JsonChainMatcher {

        protected JsonBracketChildMatcher(JsonMatcher jsonMatcher) {
            super(jsonMatcher);
        }

        @Override
        public boolean find() {
            return false;
        }
    }
}
