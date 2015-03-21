package com.github.appocalypse.jsongrep;

import javax.json.JsonArray;
import javax.json.JsonValue;
import java.util.stream.Stream;

public class IndexJsonPath implements JsonPath {
    final private JsonPath jsonPath;
    final private int index;

    public IndexJsonPath(JsonPath jsonPath, int index) {
        this.jsonPath = jsonPath;
        this.index = index;
    }

    @Override
    public Stream<JsonValue> evaluate() {
        final Stream<JsonValue> source = jsonPath.evaluate();
        return source.flatMap(jsonValue -> {
           if (jsonValue instanceof JsonArray) {
               JsonArray jsonArray = (JsonArray)jsonValue;
               return Stream.of(jsonArray.get(index));
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
    public void source(JsonValue source) {
        jsonPath.source(source);
    }
}
