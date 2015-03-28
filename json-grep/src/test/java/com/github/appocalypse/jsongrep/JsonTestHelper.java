package com.github.appocalypse.jsongrep;

import javax.json.JsonNumber;
import javax.json.JsonString;
import javax.json.JsonValue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public interface JsonTestHelper {
    public static void assertJsonString(String expected, JsonValue actual) {
        assertTrue(actual.toString() + " is not string", actual.getValueType() == JsonValue.ValueType.STRING);
        final JsonString json = (JsonString)actual;
        assertEquals(expected, json.getString());
    }

    public static void assertJsonNumber(int expected, JsonValue actual) {
        assertTrue(actual + " is not number", actual.getValueType() == JsonValue.ValueType.NUMBER);
        final JsonNumber json = (JsonNumber)actual;
        assertEquals(expected, json.intValue());
    }

    public static void assertJsonNumber(long expected, JsonValue actual) {
        assertTrue(actual + " is not number", actual.getValueType() == JsonValue.ValueType.NUMBER);
        final JsonNumber json = (JsonNumber)actual;
        assertEquals(expected, json.longValue());
    }

    public static void assertJsonNull(JsonValue actual) {
        assertTrue(actual + " is not null", actual.getValueType() == JsonValue.ValueType.NULL);
        assertEquals(JsonValue.NULL, actual);
    }

    public static void assertJsonTrue(JsonValue actual) {
        assertTrue(actual + " is not true", actual.getValueType() == JsonValue.ValueType.TRUE);
        assertEquals(JsonValue.TRUE, actual);
    }

    public static void assertJsonFalse(JsonValue actual) {
        assertTrue(actual + " is not false", actual.getValueType() == JsonValue.ValueType.FALSE);
        assertEquals(JsonValue.FALSE, actual);
    }

    public static void assertJsonArray(JsonValue actual) {
        assertTrue(actual + " is not array", actual.getValueType() == JsonValue.ValueType.ARRAY);
    }

    public static void assertJsonObject(JsonValue actual) {
        assertTrue(actual + " is not array", actual.getValueType() == JsonValue.ValueType.OBJECT);
    }
}
