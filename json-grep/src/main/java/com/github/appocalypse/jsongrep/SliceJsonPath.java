package com.github.appocalypse.jsongrep;

import javax.json.JsonArray;
import javax.json.JsonValue;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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
                final JsonArray jsonArray = (JsonArray) jsonValue;
                return StreamSupport.stream(new JsonArraySpliterator(jsonArray, Long.MAX_VALUE, Spliterator.ORDERED), false);
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

    private static int mod(int i, int modulo) {
        while (i < 0 && i < modulo) {
            i += modulo;
        }

        return i;
    }

    private class JsonArraySpliterator extends Spliterators.AbstractSpliterator<JsonValue> {

        final private JsonArray jsonArray;
        private int currentIndex;
        private int currentEndIndex;

        public JsonArraySpliterator(JsonArray jsonArray, long est, int additionalCharacteristics) {
            super(est, additionalCharacteristics);
            this.jsonArray = jsonArray;
            this.currentIndex = mod(start, jsonArray.size());
            this.currentEndIndex = mod(end, jsonArray.size());
        }

        @Override
        public boolean tryAdvance(Consumer<? super JsonValue> action) {
            if (currentIndex > jsonArray.size() || currentIndex > currentEndIndex) {
                return false;
            }

            final JsonValue jsonValue = jsonArray.get(currentIndex);
            currentIndex += step;

            action.accept(jsonValue);
            return true;
        }
    }
}
