package com.github.appocalypse.jsongrep;

import org.junit.Test;

import javax.json.Json;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;

public class JsonPatternTest {

    @Test
    public void testRoot() {
        final JsonReader jsonReader = Json.createReader(new StringReader("[]"));
        final JsonStructure root = jsonReader.read();
        JsonPattern.compile("$").matcher(root);
    }

    @Test
    public void testNonDollarSignPattern() {
        try {
            JsonPattern.compile("[");
        } catch (JsonPatternParseException e) {
            assertEquals(0, e.getErrorIndex());
        }
    }

    @Test
    public void testEndWithDescendant() {
        try {
            JsonPattern.compile("$..");
        } catch (JsonPatternParseException e) {
            assertEquals(2, e.getErrorIndex());
        }
    }

    @Test(expected = NullPointerException.class)
    public void testNullRoot() {
        JsonPattern.compile("$").matcher(null);
    }
}
