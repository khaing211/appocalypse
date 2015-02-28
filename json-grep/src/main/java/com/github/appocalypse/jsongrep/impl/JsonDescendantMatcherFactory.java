package com.github.appocalypse.jsongrep.impl;

import com.github.appocalypse.jsongrep.JsonMatcher;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import java.util.Stack;

public class JsonDescendantMatcherFactory extends JsonChainMatcherFactory {
    public JsonDescendantMatcherFactory(JsonMatcherFactory jsonMatcherFactory) {
        super(jsonMatcherFactory);
    }

    @Override
    protected JsonMatcher fromPreviousMatcher(JsonMatcher previousMatcher) {
        return new JsonDescendantChainMatcher(previousMatcher);
    }

    private static class JsonDescendantChainMatcher extends JsonChainMatcher {

        final private Stack<JsonValue> jsonValues = new Stack<JsonValue>();

        protected JsonDescendantChainMatcher(JsonMatcher jsonMatcher) {
            super(jsonMatcher);
        }

        @Override
        public boolean find() {
            if (jsonValues.isEmpty()) {
                if (jsonMatcher.find()) {
                    super.current = jsonMatcher.current();
                    transverse(super.current);
                    return true;
                } else {
                    super.current = null;
                    return false;
                }
            } else {
                super.current = jsonValues.pop();
                transverse(super.current);
                return true;
            }
        }

        private void transverse(JsonValue jsonValue) {
            if (jsonValue instanceof JsonObject) {
                final JsonObject jsonObject = (JsonObject) jsonValue;
                // this mean the find() in reverse order
                jsonObject.values().stream().forEach(jsonValues::push);
            } else if (jsonValue instanceof JsonArray) {
                final JsonArray jsonArray = (JsonArray) jsonValue;
                // this mean the find() in reverse order
                jsonArray.forEach(jsonValues::push);
            }
        }
    }
}
