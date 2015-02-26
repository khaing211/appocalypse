package com.github.appocalypse.jsongrep.impl;

import com.github.appocalypse.jsongrep.JsonMatcher;

public class JsonDescendantMatcherFactory extends JsonChainMatcherFactory {
    public JsonDescendantMatcherFactory(JsonMatcherFactory jsonMatcherFactory) {
        super(jsonMatcherFactory);
    }

    @Override
    protected JsonMatcher fromPreviousMatcher(JsonMatcher previousMatcher) {
        return new JsonDescendantChainMatcher(previousMatcher);
    }

    private static class JsonDescendantChainMatcher extends JsonChainMatcher {
        protected JsonDescendantChainMatcher(JsonMatcher jsonMatcher) {
            super(jsonMatcher);
        }

        @Override
        public boolean find() {
            // TODO:
            return false;
        }
    }
}
