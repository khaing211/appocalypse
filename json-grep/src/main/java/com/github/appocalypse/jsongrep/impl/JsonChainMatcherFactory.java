package com.github.appocalypse.jsongrep.impl;

import com.github.appocalypse.jsongrep.JsonMatcher;

import javax.json.JsonValue;

public abstract class JsonChainMatcherFactory implements JsonMatcherFactory {

    final private JsonMatcherFactory jsonMatcherFactory;

    public JsonChainMatcherFactory(JsonMatcherFactory jsonMatcherFactory) {
        this.jsonMatcherFactory = jsonMatcherFactory;
    }

    @Override
    public JsonMatcher fromRoot(JsonValue root) {
        return fromPreviousMatcher(jsonMatcherFactory.fromRoot(root));
    }

    abstract protected JsonMatcher fromPreviousMatcher(JsonMatcher previousMatcher);

    protected abstract static class ChainedJsonMatcher implements JsonMatcher {
        final protected JsonMatcher jsonMatcher;

        protected JsonValue current;

        protected ChainedJsonMatcher(JsonMatcher jsonMatcher) {
            this.jsonMatcher = jsonMatcher;
        }

        @Override
        public JsonValue current() {
            return current;
        }
    }
}
