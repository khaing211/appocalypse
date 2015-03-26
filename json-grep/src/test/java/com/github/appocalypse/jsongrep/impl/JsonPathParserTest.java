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

    @Test
    public void testParseSliceStartOnly() {
        final JsonPath jsonPath = JsonPath.path("$[1:]");
    }

    @Test
    public void testParseSliceEndOnly() {
        final JsonPath jsonPath = JsonPath.path("$[1:-3]");
    }

    @Test
    public void testParseSliceStepOnly() {
        final JsonPath jsonPath = JsonPath.path("$[1:-1:1]");
    }

    @Test
    public void testParseIndex() {
        final JsonPath jsonPath = JsonPath.path("$[1]");
    }

    @Test
    public void testParseQuote() {
        final JsonPath jsonPath = JsonPath.path("$['1']");
    }

    @Test
    public void testParseSimplePredicate() {
        final JsonPath jsonPath = JsonPath.path("$[?(1=1)]");
    }

    @Test
    public void testParseSimplePredicateWithSpace() {
        final JsonPath jsonPath = JsonPath.path("$[?(1 =1)]");
    }

    @Test
    public void testParseSimplePredicateWithSpace1() {
        final JsonPath jsonPath = JsonPath.path("$[?(1 = 1)]");
    }

    @Test
    public void testParseCurrentPredicate() {
        final JsonPath jsonPath = JsonPath.path("$[?(@[0]=1)]");
    }

    @Test
    public void testParseCurrentPredicateWithSpace() {
        final JsonPath jsonPath = JsonPath.path("$[?(@[0] =1)]");
    }

    @Test
    public void testParseCurrentPredicateWithSpace1() {
        final JsonPath jsonPath = JsonPath.path("$[?(@[0] = 1)]");
    }

    @Test
    public void testParseCurrentQuotePredicate() {
        final JsonPath jsonPath = JsonPath.path("$[?(@['a']=1)]");
    }

    @Test
    public void testParseCurrentQuotePredicateWithSpace() {
        final JsonPath jsonPath = JsonPath.path("$[?(@['a'] =1)]");
    }

    @Test
    public void testParseCurrentQuotePredicateWithSpace2() {
        final JsonPath jsonPath = JsonPath.path("$[?(@['a'] = 1)]");
    }

    @Test
    public void testParseRootQuotePredicate() {
        final JsonPath jsonPath = JsonPath.path("$[?(@['a']=$.aa)]");
    }

    @Test
    public void testParseRootQuotePredicateWithSpace() {
        final JsonPath jsonPath = JsonPath.path("$[?(@['a'] =$.aa)]");
    }

    @Test
    public void testParseRootQuotePredicateWithSpace1() {
        final JsonPath jsonPath = JsonPath.path("$[?(@['a'] = $.aa)]");
    }

    @Test
    public void testParseRootQuoteGreaterThanOrEqualPredicateWithSpace1() {
        final JsonPath jsonPath = JsonPath.path("$[?(@['a'] >= $.aa)]");
    }

    @Test(expected = JsonPathParseException.class)
    public void testBadProperty() {
        JsonPath.path("$.aaa ");
    }

    @Test(expected = JsonPathParseException.class)
    public void testBadDescendant() {
        JsonPath.path("$..");
    }

    @Test(expected = JsonPathParseException.class)
    public void testBadUnclosedBracket() {
        JsonPath.path("$[");
    }

    @Test(expected = JsonPathParseException.class)
    public void testBadUnclosedBracket2() {
        JsonPath.path("$[aaa");
    }

    @Test(expected = JsonPathParseException.class)
    public void testBadUnclosedBracket3() {
        JsonPath.path("$[1");
    }

    @Test(expected = JsonPathParseException.class)
    public void testBadUnclosedBracket4() {
        JsonPath.path("$['1'");
    }

    @Test(expected = JsonPathParseException.class)
    public void testBadUnclosedQuote() {
        JsonPath.path("$['1");
    }

    @Test(expected = JsonPathParseException.class)
    public void testBadSlice() {
        JsonPath.path("$[1::]");
    }

    @Test(expected = JsonPathParseException.class)
    public void testBadSlice2() {
        JsonPath.path("$[1:1:]");
    }

}
