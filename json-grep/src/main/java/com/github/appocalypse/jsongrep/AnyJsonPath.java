package com.github.appocalypse.jsongrep;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import java.util.stream.Stream;

public class AnyJsonPath implements JsonPath {

    final private JsonPath jsonPath;

    public AnyJsonPath(JsonPath jsonPath) {
        this.jsonPath = jsonPath;
    }

    @Override
    public Stream<JsonValue> evaluate() {
        final Stream<JsonValue> source = jsonPath.evaluate();
        return source.flatMap(jsonValue -> {
            if (jsonValue instanceof JsonObject) {
                final JsonObject jsonObject = (JsonObject) jsonValue;
                return jsonObject.values().stream();
            } else if (jsonValue instanceof JsonArray) {
                final JsonArray jsonArray = (JsonArray) jsonValue;
                return jsonArray.stream();
            } else {
                return Stream.empty();
            }
        });
    }

    @Override
    public JsonContext context() {
        return jsonPath.context();
    }

    @Override
    public JsonPath source(JsonValue source) {
        jsonPath.source(source);
        return this;
    }
}
