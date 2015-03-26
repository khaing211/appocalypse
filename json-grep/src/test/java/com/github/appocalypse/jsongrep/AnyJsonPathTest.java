package com.github.appocalypse.jsongrep;

import org.junit.Test;

import javax.json.*;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AnyJsonPathTest {
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

        final List<JsonValue> results = new AnyJsonPath(new SourceJsonPath(jsonArray))
                .evaluate()
                .collect(Collectors.toList());

        assertEquals(6, results.size());

        assertTrue(results.get(0).getValueType() == JsonValue.ValueType.NUMBER);
        final JsonNumber jsonNumber = (JsonNumber)results.get(0);
        assertEquals(3, jsonNumber.intValue());

        assertTrue(results.get(1).getValueType() == JsonValue.ValueType.STRING);
        final JsonString jsonString = (JsonString)results.get(1);
        assertEquals("string", jsonString.getString());

        assertTrue(results.get(2).getValueType() == JsonValue.ValueType.TRUE);
        assertEquals(JsonValue.TRUE, results.get(2));

        assertTrue(results.get(3).getValueType() == JsonValue.ValueType.NULL);
        assertEquals(JsonValue.NULL, results.get(3));

        assertTrue(results.get(4).getValueType() == JsonValue.ValueType.ARRAY);

        assertTrue(results.get(5).getValueType() == JsonValue.ValueType.OBJECT);
    }

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

        final List<JsonValue> results = new AnyJsonPath(new SourceJsonPath(jsonObject))
                .evaluate()
                .collect(Collectors.toList());

        assertEquals(6, results.size());

        assertTrue(results.get(0).getValueType() == JsonValue.ValueType.NUMBER);
        final JsonNumber jsonNumber = (JsonNumber)results.get(0);
        assertEquals(3, jsonNumber.intValue());

        assertTrue(results.get(1).getValueType() == JsonValue.ValueType.STRING);
        final JsonString jsonString = (JsonString)results.get(1);
        assertEquals("string", jsonString.getString());

        assertTrue(results.get(2).getValueType() == JsonValue.ValueType.TRUE);
        assertEquals(JsonValue.TRUE, results.get(2));

        assertTrue(results.get(3).getValueType() == JsonValue.ValueType.NULL);
        assertEquals(JsonValue.NULL, results.get(3));

        assertTrue(results.get(4).getValueType() == JsonValue.ValueType.ARRAY);

        assertTrue(results.get(5).getValueType() == JsonValue.ValueType.OBJECT);
    }
}
