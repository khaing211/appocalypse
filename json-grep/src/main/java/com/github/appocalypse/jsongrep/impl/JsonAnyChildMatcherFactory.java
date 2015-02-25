package com.github.appocalypse.jsongrep.impl;

import com.github.appocalypse.jsongrep.JsonMatcher;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import java.util.Iterator;

public class JsonAnyChildMatcherFactory extends JsonChainMatcherFactory {

    public JsonAnyChildMatcherFactory(JsonMatcherFactory jsonMatcherFactory) {
        super(jsonMatcherFactory);
    }

    @Override
    protected JsonMatcher fromPreviousMatcher(JsonMatcher previousMatcher) {
        return new JsonAnyChildMatcher(previousMatcher);
    }

    private static class JsonAnyChildMatcher extends ChainedJsonMatcher {

        private Iterator<JsonValue> previousValueChildren;

        protected JsonAnyChildMatcher(JsonMatcher jsonMatcher) {
            super(jsonMatcher);
        }

        @Override
        public boolean find() {
            while (true)  {
                if (checkPreviousValueChildren()) {
                    super.current = previousValueChildren.next();
                    return true;
                } else {
                    if (!jsonMatcher.find()) {
                        super.current = null;
                        return false;
                    }

                    final JsonValue previousValue = jsonMatcher.current();
                    if (previousValue instanceof JsonObject) {
                        final JsonObject jsonObject = (JsonObject) previousValue;
                        previousValueChildren = jsonObject.values().iterator();
                    }

                    if (previousValue instanceof JsonArray) {
                        final JsonArray jsonArray = (JsonArray) previousValue;
                        previousValueChildren = jsonArray.iterator();
                    }
                }
            }
        }

        private boolean checkPreviousValueChildren() {
            if (previousValueChildren == null) {
                return false;
            }

            if (!previousValueChildren.hasNext()) {
                return false;
            }

            return true;
        }
    }
}
