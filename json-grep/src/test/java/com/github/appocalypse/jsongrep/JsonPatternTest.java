package com.github.appocalypse.jsongrep;

import org.junit.Test;

import javax.json.Json;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import java.io.StringReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class JsonPatternTest {

    @Test
    public void testRoot() {
        final JsonReader jsonReader = Json.createReader(new StringReader("[]"));
        final JsonStructure root = jsonReader.read();
        JsonPattern.compile("$").matcher(root).stream().forEach(System.out::println);
    }

    @Test
    public void testObjectMatch() {
        final JsonReader jsonReader = Json.createReader(new StringReader("{\"name\":\"hello\"}"));
        final JsonStructure root = jsonReader.read();
        JsonPattern.compile("$.name").matcher(root).stream().forEach(System.out::println);
    }

    @Test
    public void testNonDollarSignPattern() {
        try {
            JsonPattern.compile("[");
            fail("should throw exception");
        } catch (JsonPathParseException e) {
            assertEquals(0, e.getErrorIndex());
        }
    }

    @Test
    public void testEndWithDescendant() {
        try {
            JsonPattern.compile("$..");
            fail("should throw exception");
        } catch (JsonPathParseException e) {
            assertEquals(2, e.getErrorIndex());
        }
    }

    @Test
    public void testEndWithDescendant2() {
        try {
            JsonPattern.compile("$[a]..");
            fail("should throw exception");
        } catch (JsonPathParseException e) {
            assertEquals(5, e.getErrorIndex());
        }
    }

    @Test
    public void testEndWithDescendant3() {
        try {
            JsonPattern.compile("$.a..");
            fail("should throw exception");
        } catch (JsonPathParseException e) {
            assertEquals(4, e.getErrorIndex());
        }
    }

    @Test
    public void testNoClosingBracket() {
        try {
            JsonPattern.compile("$[abc");
            fail("should throw exception");
        } catch (JsonPathParseException e) {
            assertEquals(1, e.getErrorIndex());
        }
    }

    @Test
    public void testEmptyBracket() {
        try {
            JsonPattern.compile("$[]");
            fail("should throw exception");
        } catch (JsonPathParseException e) {
            assertEquals(1, e.getErrorIndex());
        }
    }

    @Test(expected = NullPointerException.class)
    public void testNullRoot() {
        JsonPattern.compile("$").matcher(null);
    }
}
