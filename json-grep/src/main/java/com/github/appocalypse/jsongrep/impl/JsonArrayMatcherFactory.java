package com.github.appocalypse.jsongrep.impl;

import com.github.appocalypse.jsongrep.JsonMatcher;

import javax.json.JsonArray;
import javax.json.JsonValue;
import java.util.Iterator;
import java.util.Optional;

/**
 * start = mandatory, inclusive, support negative index
 * end = optional, inclusive, default -1 i.e. end of array
 * step = optional, positive number, default 1
 *
 * Remember: start, end index are depending on actual given array
 *
 * example: [0::], [0:], [0::1], [-1::2], [-5:-1:2]
 */
public class JsonArrayMatcherFactory extends JsonChainMatcherFactory {

    final private int startIndex;
    final private int endIndex;
    final private int step;

    public JsonArrayMatcherFactory(JsonMatcherFactory jsonMatcherFactory, int startIndex) {
        this(jsonMatcherFactory, startIndex, -1, 1);
    }

    public JsonArrayMatcherFactory(JsonMatcherFactory jsonMatcherFactory, int startIndex, int endIndex) {
        this(jsonMatcherFactory, startIndex, -1, 1);
    }

    public JsonArrayMatcherFactory(JsonMatcherFactory jsonMatcherFactory, int startIndex, int endIndex, int step) {
        super(jsonMatcherFactory);

        if (step <= 0) {
            throw new IllegalArgumentException("step has to be positive");
        }

        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.step = step;
    }

    @Override
    protected JsonMatcher fromPreviousMatcher(JsonMatcher previousMatcher) {
        return new JsonArrayMatcher(previousMatcher);
    }

    private class JsonArrayMatcher extends JsonChainMatcher {
        private JsonArray previousJsonArray;
        private int currentIndex;
        private int currentEndIndex;

        protected JsonArrayMatcher(JsonMatcher jsonMatcher) {
            super(jsonMatcher);
        }

        @Override
        public boolean find() {
            while (true)  {
                if (checkPreviousJsonArray()) {
                    super.current = step();
                    return true;
                } else {
                    if (!jsonMatcher.find()) {
                        super.current = null;
                        return false;
                    }

                    final JsonValue previousValue = jsonMatcher.current();
                    if (previousValue instanceof JsonArray) {
                        previousJsonArray = (JsonArray) previousValue;
                        adjustInitialIndex();
                        adjustEndIndex();
                    }
                }
            }
        }

        private void adjustInitialIndex() {
            this.currentIndex = mod(startIndex, previousJsonArray.size());
        }

        private void adjustEndIndex() {
            this.currentEndIndex = mod(endIndex, previousJsonArray.size());
        }

        private JsonValue step() {
            final JsonValue value = previousJsonArray.get(currentIndex);
            currentIndex += step;
            return value;
        }

        private int mod(int i, int modulo) {
            while (i < 0 && i < modulo) {
                i += modulo;
            }

            return i;
        }

        private boolean checkPreviousJsonArray() {
            if (previousJsonArray == null) {
                return false;
            }

            if (currentIndex > previousJsonArray.size() || currentIndex > currentEndIndex) {
                return false;
            }

            return true;
        }
    }
}
