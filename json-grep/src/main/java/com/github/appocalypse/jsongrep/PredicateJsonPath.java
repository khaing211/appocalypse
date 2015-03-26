package com.github.appocalypse.jsongrep;

import javax.json.JsonValue;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class PredicateJsonPath implements JsonPath {

    final private JsonPath jsonPath;
    final private Predicate<JsonContext> predicate;

    public PredicateJsonPath(JsonPath jsonPath, Predicate<JsonContext> predicate) {
        this.jsonPath = jsonPath;
        this.predicate = predicate;
    }

    @Override
    public Stream<JsonValue> evaluate() {
        final Stream<JsonValue> source = jsonPath.evaluate();
        return source.flatMap(jsonValue -> {
            final JsonContext currentContext = JsonContext.copy(context())
                    .withCurrent(jsonValue)
                    .build();

            if (predicate.test(currentContext)) {
                return Stream.of(jsonValue);
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
