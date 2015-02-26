package com.github.appocalypse.jsongrep.impl;

import com.github.appocalypse.jsongrep.JsonMatcher;

import java.util.Optional;

/**
 * Handling
 *
 *
 *  x[-2:-1:1]
 *  x[-1:]
 *  x[3:]
 *  x[2:5]
 *  x[1:5:2]
 */
public class JsonArrayMatcherFactory extends JsonChainMatcherFactory {

    final private Integer startIndex;
    final private Optional<Integer> endIndex;
    final private Optional<Integer> step;

    public JsonArrayMatcherFactory(JsonMatcherFactory jsonMatcherFactory, Integer startIndex) {
        this(jsonMatcherFactory, startIndex, null, 1);
    }

    public JsonArrayMatcherFactory(JsonMatcherFactory jsonMatcherFactory, Integer startIndex, Integer endIndex) {
        this(jsonMatcherFactory, startIndex, endIndex, 1);
    }

    public JsonArrayMatcherFactory(JsonMatcherFactory jsonMatcherFactory, Integer startIndex, Integer endIndex, Integer step) {
        super(jsonMatcherFactory);
        this.startIndex = startIndex;
        this.endIndex = Optional.ofNullable(endIndex);
        this.step = Optional.of(Optional.ofNullable(step).orElse(1));
    }

    @Override
    protected JsonMatcher fromPreviousMatcher(JsonMatcher previousMatcher) {
        return new JsonArrayMatcher(previousMatcher);
    }

    private class JsonArrayMatcher extends JsonChainMatcher {

        protected JsonArrayMatcher(JsonMatcher jsonMatcher) {
            super(jsonMatcher);
        }

        @Override
        public boolean find() {
            return false;
        }
    }
}
