package com.github.appocalypse.jsongrep;

import javax.json.JsonValue;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public interface JsonMatcher {
    /**
     * Call find() in order to find next match
     *
     * @return true if match is found. false otherwise.
     */
    public boolean find();

    /**
     * @return current match if find() return true; null otherwise.
     */
    public JsonValue current();

    public default Stream<JsonValue> stream() {
        return StreamSupport.stream(new Spliterators.AbstractSpliterator<JsonValue>(Long.MAX_VALUE, Spliterator.ORDERED) {
            @Override
            public boolean tryAdvance(Consumer<? super JsonValue> action) {
                final boolean found = find();
                if (found) {
                    action.accept(current());
                }
                return found;
            }
        } ,false);
    }
}
