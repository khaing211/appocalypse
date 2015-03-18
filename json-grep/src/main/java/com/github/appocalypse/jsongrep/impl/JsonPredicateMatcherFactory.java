package com.github.appocalypse.jsongrep.impl;

import java.util.function.Predicate;

import javax.json.JsonValue;

import com.github.appocalypse.jsongrep.JsonMatcher;

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
            while (jsonMatcher.find()) {
                final JsonValue previousValue = jsonMatcher.current();
                if (predicate.test(previousValue)) {
                    super.current = previousValue;
                    return true;
                }
            }

            super.current = null;
            return false;
        }
    }
}
