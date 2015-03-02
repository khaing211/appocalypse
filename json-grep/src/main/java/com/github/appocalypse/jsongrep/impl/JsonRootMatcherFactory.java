package com.github.appocalypse.jsongrep.impl;

import com.github.appocalypse.jsongrep.JsonMatcher;

import javax.json.JsonStructure;
import javax.json.JsonValue;

public class JsonRootMatcherFactory implements JsonMatcherFactory {

    final private static int INIT_STATE = 0;
    final private static int FIND_CALLED = 1;
    final private static int FIND_CALLED2 = 2;

    @Override
    public JsonMatcher fromRoot(JsonValue root) {
        return new JsonRootMatcher(root);
    }

    private static class JsonRootMatcher implements JsonMatcher {
        final private JsonValue root;

        private int matcherState = INIT_STATE;

        public JsonRootMatcher(JsonValue root) {
            this.root = root;
        }

        @Override
        public boolean find() {
            matcherState++;
            if (matcherState >= FIND_CALLED2) {
                return false;
            } else {
                return true;
            }
        }

        @Override
        public JsonValue current() {
            if (matcherState != FIND_CALLED) {
                return null;
            } else {
                return root;
            }
        }
    }
}
