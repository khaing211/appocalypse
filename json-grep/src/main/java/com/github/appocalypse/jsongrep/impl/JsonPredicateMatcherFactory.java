package com.github.appocalypse.jsongrep.impl;

import com.github.appocalypse.jsongrep.JsonMatcher;

import javax.json.JsonValue;
import java.util.function.Predicate;

public class JsonPredicateMatcherFactory extends JsonChainMatcherFactory {
    final private Predicate<JsonValue> predicate;

    public JsonPredicateMatcherFactory(JsonMatcherFactory jsonMatcherFactory, Predicate<JsonValue> predicate) {
        super(jsonMatcherFactory);
        this.predicate = predicate;
    }

    @Override
    protected JsonMatcher fromPreviousMatcher(JsonMatcher previousMatcher) {
        return new JsonFilterMatcher(previousMatcher);
    }

    private class JsonFilterMatcher extends JsonChainMatcher {

        protected JsonFilterMatcher(JsonMatcher jsonMatcher) {
            super(jsonMatcher);
        }

        @Override
        public boolean find() {
            return false;
        }
    }
}
