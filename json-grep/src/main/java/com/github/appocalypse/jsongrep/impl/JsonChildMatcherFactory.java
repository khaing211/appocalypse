package com.github.appocalypse.jsongrep.impl;

import com.github.appocalypse.jsongrep.JsonMatcher;

import javax.json.JsonObject;
import javax.json.JsonValue;

public class JsonChildMatcherFactory extends JsonChainMatcherFactory {

    final private String name;

    public JsonChildMatcherFactory(JsonMatcherFactory jsonMatcherFactory, String name) {
        super(jsonMatcherFactory);
        this.name = name;
    }

    @Override
    protected JsonMatcher fromPreviousMatcher(JsonMatcher previousMatcher) {
        return new JsonChildChainMatcher(previousMatcher);
    }

    private class JsonChildChainMatcher extends JsonChainMatcher {

        protected JsonChildChainMatcher(JsonMatcher jsonMatcher) {
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
            }

            super.current = null;
            return false;
        }
    }
}
