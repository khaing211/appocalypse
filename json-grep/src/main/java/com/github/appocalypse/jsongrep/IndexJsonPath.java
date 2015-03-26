package com.github.appocalypse.jsongrep;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import java.util.stream.Stream;

public class IndexJsonPath implements JsonPath {
    final private JsonPath jsonPath;
    final private int index;
    final private String property;

    public IndexJsonPath(JsonPath jsonPath, int index) {
        this.jsonPath = jsonPath;
        this.index = index;
        this.property = Integer.toString(index);
    }

    @Override
    public Stream<JsonValue> evaluate() {
        final Stream<JsonValue> source = jsonPath.evaluate();
        return source.flatMap(jsonValue -> {
           if (jsonValue instanceof JsonArray) {
               final JsonArray jsonArray = (JsonArray)jsonValue;
               return Stream.of(jsonArray.get(index));
           }

            if (jsonValue instanceof JsonObject) {
                final JsonObject jsonObject = (JsonObject)jsonValue;
                if (jsonObject.containsKey(property)) {
                    return Stream.of(jsonObject.get(property));
                }
            }

            return Stream.empty();
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
