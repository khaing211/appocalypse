package com.github.appocalypse.jsongrep;

import javax.json.JsonArray;
import javax.json.JsonValue;
import java.util.stream.Stream;

public class SliceJsonPath implements JsonPath {

    final private JsonPath jsonPath;
    final private int start;
    final private int end;
    final private int step;

    public SliceJsonPath(JsonPath jsonPath, int start, int end, int step) {
        this.jsonPath = jsonPath;
        this.start = start;
        this.end = end;
        this.step = step;
    }

    @Override
    public Stream<JsonValue> evaluate() {
        final Stream<JsonValue> source = jsonPath.evaluate();
        return source.flatMap(jsonValue -> {
            if (jsonValue instanceof JsonArray) {
                JsonArray jsonArray = (JsonArray)jsonValue;
                // TODO:
                return Stream.of(jsonArray.get(start));
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
