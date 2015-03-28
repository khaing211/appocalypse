package com.github.appocalypse.jsongrep;

import org.junit.Test;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class IndexJsonPathTest {
    @Test
    public void testArray() {
        final JsonArray jsonArray = Json.createArrayBuilder()
                .add(3)
                .add("string")
                .add(true)
                .addNull()
                .build();

        final List<JsonValue> results = new IndexJsonPath(new SourceJsonPath(jsonArray), 0)
                .evaluate()
                .collect(Collectors.toList());

        assertEquals(1, results.size());

        JsonTestHelper.assertJsonNumber(3, results.get(0));
    }

    @Test
    public void testObject() {
        final JsonObject jsonObject = Json.createObjectBuilder()
                .add("1", 3)
                .add("t2", "string")
                .build();

        final List<JsonValue> results = new IndexJsonPath(new SourceJsonPath(jsonObject), 1)
                .evaluate()
                .collect(Collectors.toList());

        assertEquals(1, results.size());

        JsonTestHelper.assertJsonNumber(3, results.get(0));
    }
}
