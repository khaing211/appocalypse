package com.github.appocalypse.jsongrep;

import org.junit.Test;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class PropertyJsonPathTest {
    @Test
    public void testObject() {
        final JsonObject jsonInnerObject = Json.createObjectBuilder()
                .build();

        final JsonArray jsonInnerArray = Json.createArrayBuilder()
                .build();

        final JsonObject jsonObject = Json.createObjectBuilder()
                .add("t1", 3)
                .add("t2", "string")
                .add("t3", true)
                .addNull("t4")
                .add("t5", jsonInnerArray)
                .add("t6", jsonInnerObject)
                .build();

        final List<JsonValue> results = new PropertyJsonPath(new SourceJsonPath(jsonObject), "t1")
                .evaluate()
                .collect(Collectors.toList());

        assertEquals(1, results.size());

        JsonTestHelper.assertJsonNumber(3, results.get(0));
    }
}
