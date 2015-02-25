package com.github.appocalypse.jsongrep.impl;

import com.github.appocalypse.jsongrep.JsonMatcher;

public class JsonDescendantMatcherFactory extends JsonChainMatcherFactory {
    public JsonDescendantMatcherFactory(JsonMatcherFactory jsonMatcherFactory) {
        super(jsonMatcherFactory);
    }

    @Override
    protected JsonMatcher fromPreviousMatcher(JsonMatcher previousMatcher) {
        return new JsonDescendantMatcher(previousMatcher);
    }

    private static class JsonDescendantMatcher extends ChainedJsonMatcher {
        protected JsonDescendantMatcher(JsonMatcher jsonMatcher) {
            super(jsonMatcher);
        }

        @Override
        public boolean find() {
            // TODO:
            return false;
        }
    }
}
