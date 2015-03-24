package com.github.appocalypse.jsongrep;

import javax.json.JsonValue;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Fluent API for JsonPath
 */
public class JsonPaths {

    private JsonPath jsonPath;

    private JsonPaths(JsonPath source) {
        this.jsonPath = source;
    }

    public static JsonPaths source(JsonValue source) {
        return new JsonPaths(new SourceJsonPath(source));
    }

    public JsonPaths property(String property) {
        jsonPath = new PropertyJsonPath(jsonPath, property);
        return this;
    }

    public JsonPaths index(int index) {
        jsonPath = new IndexJsonPath(jsonPath, index);
        return this;
    }

    public JsonPaths any() {
        jsonPath = new AnyJsonPath(jsonPath);
        return this;
    }

    public JsonPaths descendant() {
        jsonPath = new DescendantJsonPath(jsonPath);
        return this;
    }

    public JsonPaths slice(int start) {
        jsonPath = new SliceJsonPath(jsonPath, start, -1, 1);
        return this;
    }

    public JsonPaths slice(int start, int end) {
        jsonPath = new SliceJsonPath(jsonPath, start, end, 1);
        return this;
    }

    public JsonPaths slice(int start, int end, int step) {
        jsonPath = new SliceJsonPath(jsonPath, start, end, step);
        return this;
    }

    public JsonPaths filter(Predicate<JsonContext> predicate) {
        jsonPath = new PredicateJsonPath(jsonPath, predicate);
        return this;
    }

    public Stream<JsonValue> stream() {
        return jsonPath.evaluate();
    }
}
