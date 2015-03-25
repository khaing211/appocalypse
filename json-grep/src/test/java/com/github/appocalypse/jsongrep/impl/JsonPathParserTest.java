package com.github.appocalypse.jsongrep.impl;

import com.github.appocalypse.jsongrep.JsonPath;
import com.github.appocalypse.jsongrep.JsonPathParseException;
import org.junit.Test;

import javax.json.Json;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import java.io.StringReader;

public class JsonPathParserTest {
    @Test
    public void testParseProperty() {
        final JsonPath jsonPath = JsonPath.path("$.name");
        final JsonReader jsonReader = Json.createReader(new StringReader("{\"name\":\"hello\"}"));
        final JsonStructure root = jsonReader.read();
        jsonPath.source(root);
        jsonPath.evaluate().forEach(System.out::println);
    }

    @Test
    public void testParseDescendant() {
        final JsonPath jsonPath = JsonPath.path("$..name");
        final JsonReader jsonReader = Json.createReader(new StringReader("[{\"name\":\"hello\"}, {\"name\":\"world\"}]"));
        final JsonStructure root = jsonReader.read();
        jsonPath.source(root);
        jsonPath.evaluate().forEach(System.out::println);
    }

    @Test
    public void testParseAsteriskObject() {
        final JsonPath jsonPath = JsonPath.path("$.*");
        final JsonReader jsonReader = Json.createReader(new StringReader("{\"name\":\"test1\", \"test\":\"test2\"}"));
        final JsonStructure root = jsonReader.read();
        jsonPath.source(root);
        jsonPath.evaluate().forEach(System.out::println);
    }

    @Test(expected = JsonPathParseException.class)
    public void testBadProperty() {
        JsonPath.path("$.aaa ");
    }

    @Test(expected = JsonPathParseException.class)
    public void testBadDescendant() {
        JsonPath.path("$..");
    }
}
