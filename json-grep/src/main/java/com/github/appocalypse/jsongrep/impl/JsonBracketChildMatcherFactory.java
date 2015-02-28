package com.github.appocalypse.jsongrep.impl;

import com.github.appcalypse.jdk.extra.function.IntPredicates;
import com.github.appcalypse.jdk.extra.function.Predicates;
import com.github.appcalypse.jdk.extra.util.Eithers;
import com.github.appocalypse.jsongrep.JsonMatcher;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import java.util.Optional;

/**
 * Deal with JsonObject and JsonArray if intValue is not null
 */
public class JsonBracketChildMatcherFactory extends JsonChainMatcherFactory {
    final private String name;
    final private Optional<Integer> index;

    public JsonBracketChildMatcherFactory(JsonMatcherFactory jsonMatcherFactory, String name) {
        super(jsonMatcherFactory);
        this.name = name;
        this.index = Eithers.wrap(() -> Integer.parseInt(name), NumberFormatException.class)
                .right()
                .filter(Predicates.boxed(IntPredicates.greaterThanOrEquals(0)));
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
            while (jsonMatcher.find()) {
                final JsonValue previousValue = jsonMatcher.current();
                if (previousValue instanceof JsonObject) {
                    final JsonObject jsonObject = (JsonObject)previousValue;
                    if (jsonObject.containsKey(name)) {
                        super.current = jsonObject.get(name);
                        return true;
                    }
                }

                if (index.isPresent() && previousValue instanceof JsonArray) {
                    final JsonArray jsonArray = (JsonArray)previousValue;
                    if (index.get() < jsonArray.size()) {
                        super.current = jsonArray.get(index.get());
                        return true;
                    }
                }
            }

            super.current = null;
            return false;
        }
    }
}
