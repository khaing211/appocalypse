package com.github.appocalypse.jsongrep;

import javax.json.JsonValue;
import java.util.stream.Stream;

public class SourceJsonPath implements JsonPath {

    private JsonValue source;

    public SourceJsonPath(JsonValue source) {
        this.source = source;
    }

    public SourceJsonPath() { }

    @Override
    public Stream<JsonValue> evaluate() {
        return Stream.<JsonValue>builder()
                .add(source)
                .build();
    }

    @Override
    public JsonContext context() {
        return JsonContext.newBuilder()
                .withRoot(source)
                .build();
    }

    @Override
    public JsonPath source(JsonValue source) {
        this.source = source;
        return this;
    }
}
