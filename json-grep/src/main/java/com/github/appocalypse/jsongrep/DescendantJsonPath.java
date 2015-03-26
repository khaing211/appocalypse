package com.github.appocalypse.jsongrep;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.Stack;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class DescendantJsonPath implements JsonPath {

    final private JsonPath jsonPath;

    public DescendantJsonPath(JsonPath jsonPath) {
        this.jsonPath = jsonPath;
    }

    @Override
    public Stream<JsonValue> evaluate() {
        final Stream<JsonValue> source = jsonPath.evaluate();
        return source.flatMap(jsonValue -> {
            return StreamSupport.stream(new JsonValueSpliterator(jsonValue, Long.MAX_VALUE, Spliterator.ORDERED), false);
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

    private static class JsonValueSpliterator extends Spliterators.AbstractSpliterator<JsonValue> {
        final private Stack<JsonValue> jsonValues = new Stack<JsonValue>();

        public JsonValueSpliterator(JsonValue jsonValue, long est, int additionalCharacteristics) {
            super(est, additionalCharacteristics);
            jsonValues.push(jsonValue);
        }

        @Override
        public boolean tryAdvance(Consumer<? super JsonValue> action) {
            if (jsonValues.empty()) {
                return false;
            }

            final JsonValue jsonValue = jsonValues.pop();
            if (jsonValue instanceof JsonObject) {
                final JsonObject jsonObject = (JsonObject) jsonValue;
                // this mean the find() in reverse order
                jsonObject.values().stream().forEach(jsonValues::push);
            } else if (jsonValue instanceof JsonArray) {
                final JsonArray jsonArray = (JsonArray) jsonValue;
                // this mean the find() in reverse order
                jsonArray.forEach(jsonValues::push);
            }

            action.accept(jsonValue);

            return true;
        }
    }
}
