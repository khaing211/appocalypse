package com.github.appocalypse.jsongrep.impl;

import com.github.appocalypse.jsongrep.JsonMatcher;

import javax.json.JsonStructure;
import javax.json.JsonValue;

public class JsonRootMatcherFactory implements JsonMatcherFactory {

    @Override
    public JsonMatcher fromRoot(JsonValue root) {
        return new JsonRootMatcher(root);
    }

    private static class JsonRootMatcher implements JsonMatcher {
        final private JsonValue root;

        public JsonRootMatcher(JsonValue root) {
            this.root = root;
        }

        @Override
        public boolean find() {
            return true;
        }

        @Override
        public JsonValue current() {
            return root;
        }
    }
}
