package com.github.appocalypse.jsongrep;

import javax.json.JsonObject;
import javax.json.JsonValue;
import java.util.stream.Stream;

public class PropertyJsonPath implements JsonPath {

    final private JsonPath jsonPath;
    final private String property;

    public PropertyJsonPath(JsonPath jsonPath, String property) {
        this.jsonPath = jsonPath;
        this.property = property;
    }

    @Override
    public Stream<JsonValue> evaluate() {
        final Stream<JsonValue> source = jsonPath.evaluate();
        return source.flatMap(jsonValue -> {
            if (jsonValue instanceof JsonObject) {
                final JsonObject jsonObject = (JsonObject)jsonValue;
                if (jsonObject.containsKey(property)) {
                    return Stream.of(jsonObject.get(property));
                } else {
                    return Stream.empty();
                }
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
