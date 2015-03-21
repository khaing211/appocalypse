package com.github.appocalypse.jsongrep;

import javax.json.JsonValue;
import java.util.stream.Stream;

public class DescendantJsonPath implements JsonPath {

    final private JsonPath jsonPath;

    public DescendantJsonPath(JsonPath jsonPath) {
        this.jsonPath = jsonPath;
    }

    @Override
    public Stream<JsonValue> evaluate() {
        return null;
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
