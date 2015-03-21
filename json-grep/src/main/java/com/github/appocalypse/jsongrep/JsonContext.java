package com.github.appocalypse.jsongrep;

import javax.json.JsonValue;

public class JsonContext {
    final private JsonValue root;
    final private JsonValue current;

    public JsonValue root() {
        return root;
    }

    public JsonValue current() {
        return current;
    }

    private JsonContext(Builder builder) {
        this.root = builder.root;
        this.current = builder.current;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder copy(JsonContext documentContext) {
        return newBuilder()
                .withRoot(documentContext.root())
                .withCurrent(documentContext.current());
    }

    public static class Builder {
        private JsonValue root;
        private JsonValue current;

        public Builder withRoot(JsonValue root) {
            this.root = root;
            return this;
        }

        public Builder withCurrent(JsonValue current) {
            this.current = current;
            return this;
        }

        public JsonContext build() {
            return new JsonContext(this);
        }

    }
}
