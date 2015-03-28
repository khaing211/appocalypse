package com.github.appocalypse.jsongrep;

import org.junit.Test;

import javax.json.*;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DescendantJsonPathTest {
    @Test
    public void testArray() {
        final JsonObject jsonInnerObject = Json.createObjectBuilder()
                .add("n1", "inner")
                .build();

        final JsonArray jsonInnerArray = Json.createArrayBuilder()
                .add("inner")
                .build();

        final JsonArray jsonArray = Json.createArrayBuilder()
                .add(3)
                .add("string")
                .add(true)
                .addNull()
                .add(jsonInnerArray)
                .add(jsonInnerObject)
                .build();

        final List<JsonValue> results = new DescendantJsonPath(new SourceJsonPath(jsonArray))
                .evaluate()
                .collect(Collectors.toList());

        assertEquals(8, results.size());

        JsonTestHelper.assertJsonNumber(3, results.get(0));

        JsonTestHelper.assertJsonString("string", results.get(1));

        JsonTestHelper.assertJsonTrue(results.get(2));

        JsonTestHelper.assertJsonNull(results.get(3));

        JsonTestHelper.assertJsonArray(results.get(4));

        JsonTestHelper.assertJsonString("inner", results.get(5));

        JsonTestHelper.assertJsonObject(results.get(6));

        JsonTestHelper.assertJsonString("inner", results.get(7));
    }

    @Test
    public void testObject() {
        final JsonObject jsonInnerObject = Json.createObjectBuilder()
                .add("n1", "inner")
                .build();

        final JsonArray jsonInnerArray = Json.createArrayBuilder()
                .add("inner")
                .build();

        final JsonObject jsonObject = Json.createObjectBuilder()
                .add("t1", 3)
                .add("t2", "string")
                .add("t3", true)
                .addNull("t4")
                .add("t5", jsonInnerArray)
                .add("t6", jsonInnerObject)
                .build();

        final List<JsonValue> results = new DescendantJsonPath(new SourceJsonPath(jsonObject))
                .evaluate()
                .collect(Collectors.toList());

        assertEquals(8, results.size());

        JsonTestHelper.assertJsonNumber(3, results.get(0));

        JsonTestHelper.assertJsonString("string", results.get(1));

        JsonTestHelper.assertJsonTrue(results.get(2));

        JsonTestHelper.assertJsonNull(results.get(3));

        JsonTestHelper.assertJsonArray(results.get(4));

        JsonTestHelper.assertJsonString("inner", results.get(5));

        JsonTestHelper.assertJsonObject(results.get(6));

        JsonTestHelper.assertJsonString("inner", results.get(7));
    }
}
