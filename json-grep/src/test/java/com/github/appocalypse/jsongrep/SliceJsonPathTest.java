package com.github.appocalypse.jsongrep;

import org.junit.Test;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class SliceJsonPathTest {
    @Test
    public void testArray() {
        final JsonObject jsonInnerObject = Json.createObjectBuilder()
                .build();

        final JsonArray jsonInnerArray = Json.createArrayBuilder()
                .build();

        final JsonArray jsonArray = Json.createArrayBuilder()
                .add(3)
                .add("string")
                .add(true)
                .addNull()
                .add(jsonInnerArray)
                .add(jsonInnerObject)
                .build();

        final List<JsonValue> results = new SliceJsonPath(new SourceJsonPath(jsonArray), 0, -1, 1)
                .evaluate()
                .collect(Collectors.toList());

        assertEquals(6, results.size());

        JsonTestHelper.assertJsonNumber(3, results.get(0));

        JsonTestHelper.assertJsonString("string", results.get(1));

        JsonTestHelper.assertJsonTrue(results.get(2));

        JsonTestHelper.assertJsonNull(results.get(3));

        JsonTestHelper.assertJsonArray(results.get(4));

        JsonTestHelper.assertJsonObject(results.get(5));
    }
}
