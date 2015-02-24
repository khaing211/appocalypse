package com.github.appocalypse.jsongrep;

import org.junit.Test;

import javax.json.JsonNumber;
import javax.json.JsonValue;

import static org.junit.Assert.assertEquals;

public class JsonMatcherTest {
    @Test
    public void testStream() {
        JsonMatcher example = new JsonMatcher() {

            final private JsonValue[] jsonValues = new JsonValue[] { JsonValue.TRUE, JsonValue.FALSE, JsonValue.NULL };
            private int count = -1;

            @Override
            public boolean find() {
                return ++count < jsonValues.length;
            }

            @Override
            public JsonValue current() {
                return jsonValues[count];
            }
        };

        assertEquals(3, example.stream().count());
    }
}
