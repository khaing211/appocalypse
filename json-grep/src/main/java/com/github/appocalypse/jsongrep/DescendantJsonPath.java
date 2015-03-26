package com.github.appocalypse.jsongrep;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import java.util.LinkedList;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.Stack;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
            push(jsonValue);
        }

        @Override
        public boolean tryAdvance(Consumer<? super JsonValue> action) {
            if (jsonValues.empty()) {
                return false;
            }

            final JsonValue jsonValue = jsonValues.pop();
            push(jsonValue);

            action.accept(jsonValue);

            return true;
        }

        private void push(JsonValue jsonValue) {
            if (jsonValue instanceof JsonObject) {
                final JsonObject jsonObject = (JsonObject) jsonValue;

                // iterate in reverse for JsonObject
                jsonObject.values()
                        .stream()
                        .collect(Collectors.toCollection(LinkedList::new))
                        .descendingIterator()
                        .forEachRemaining(jsonValues::push);

            } else if (jsonValue instanceof JsonArray) {
                final JsonArray jsonArray = (JsonArray) jsonValue;

                // iterate in reverse for JsonArray
                IntStream.range(0, jsonArray.size())
                        .map(i -> jsonArray.size() - 1 - i)
                        .mapToObj(jsonArray::get)
                        .forEach(jsonValues::push);
            }
        }
    }
}
