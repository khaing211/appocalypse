package com.github.appocalypse.jsongrep;

import org.junit.Test;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

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

        results.stream().forEach(System.out::println);
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
    }
}
