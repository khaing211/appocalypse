package com.github.appocalypse.jsongrep;

import org.junit.Test;

import javax.json.Json;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import java.io.StringReader;

public class JsonPatternTest {

    @Test
    public void testRoot() {
        final JsonReader jsonReader = Json.createReader(new StringReader("[]"));
        final JsonStructure root = jsonReader.read();
        JsonPattern.compile("$").matcher(root);
    }

    @Test(expected = NullPointerException.class)
    public void testNullRoot() {
        JsonPattern.compile("$").matcher(null);
    }
}
