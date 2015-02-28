package com.github.appocalypse.jsongrep.impl;

import com.github.appocalypse.jsongrep.JsonMatcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.json.*;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JsonBracketChildMatcherFactoryTest {

    @Mock
    private JsonMatcherFactory jsonMatcherFactory;

    @Mock
    private JsonMatcher jsonMatcher;

    @Before
    public void before() {
        when(jsonMatcherFactory.fromRoot(any(JsonValue.class))).thenReturn(jsonMatcher);
    }

    @Test
    public void testJsonObject() {
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("match", "this")
                .add("hello", "world")
                .build();

        when(jsonMatcher.find()).thenReturn(true).thenReturn(false);
        when(jsonMatcher.current()).thenReturn(jsonObject).thenReturn(null);

        JsonBracketChildMatcherFactory jsonChildMatcherFactory = new JsonBracketChildMatcherFactory(jsonMatcherFactory, "match");
        JsonMatcher jsonBracketChildMatcher = jsonChildMatcherFactory.fromRoot(JsonValue.NULL);

        assertTrue(jsonBracketChildMatcher.find());
        assertTrue(jsonBracketChildMatcher.current() instanceof JsonString);
        assertEquals("this", ((JsonString)jsonBracketChildMatcher.current()).getString());

        assertFalse(jsonBracketChildMatcher.find());
        assertNull(jsonBracketChildMatcher.current());

        verify(jsonMatcher, times(2)).find();
    }

    @Test
    public void testJsonObjectNonMatch() {
        JsonObject jsonObject = Json.createObjectBuilder()
                .add("match", "this")
                .add("hello", "world")
                .build();

        when(jsonMatcher.find()).thenReturn(true).thenReturn(false);
        when(jsonMatcher.current()).thenReturn(jsonObject).thenReturn(null);

        JsonBracketChildMatcherFactory jsonChildMatcherFactory = new JsonBracketChildMatcherFactory(jsonMatcherFactory, "nonmatch");
        JsonMatcher jsonBracketChildMatcher = jsonChildMatcherFactory.fromRoot(JsonValue.NULL);

        assertFalse(jsonBracketChildMatcher.find());
        assertNull(jsonBracketChildMatcher.current());

        verify(jsonMatcher, times(2)).find();
    }

    @Test
    public void testJsonArray() {
        JsonArray jsonArray = Json.createArrayBuilder()
                .add("string")
                .add(1)
                .build();

        when(jsonMatcher.find()).thenReturn(true).thenReturn(false);
        when(jsonMatcher.current()).thenReturn(jsonArray).thenReturn(null);

        JsonBracketChildMatcherFactory jsonChildMatcherFactory = new JsonBracketChildMatcherFactory(jsonMatcherFactory, "0");
        JsonMatcher jsonBracketChildMatcher = jsonChildMatcherFactory.fromRoot(JsonValue.NULL);

        assertTrue(jsonBracketChildMatcher.find());
        assertTrue(jsonBracketChildMatcher.current() instanceof JsonString);
        assertEquals("string", ((JsonString)jsonBracketChildMatcher.current()).getString());

        assertFalse(jsonBracketChildMatcher.find());
        assertNull(jsonBracketChildMatcher.current());

        verify(jsonMatcher, times(2)).find();
    }

    @Test
    public void testJsonArrayNonIndex() {
        JsonArray jsonArray = Json.createArrayBuilder()
                .add("string")
                .add(1)
                .build();

        when(jsonMatcher.find()).thenReturn(true).thenReturn(false);
        when(jsonMatcher.current()).thenReturn(jsonArray).thenReturn(null);

        JsonBracketChildMatcherFactory jsonChildMatcherFactory = new JsonBracketChildMatcherFactory(jsonMatcherFactory, "notindex");
        JsonMatcher jsonBracketChildMatcher = jsonChildMatcherFactory.fromRoot(JsonValue.NULL);

        assertFalse(jsonBracketChildMatcher.find());
        assertNull(jsonBracketChildMatcher.current());

        verify(jsonMatcher, times(2)).find();
    }

    @Test
    public void testJsonArrayIndexOutOfBound() {
        JsonArray jsonArray = Json.createArrayBuilder()
                .add("string")
                .add(1)
                .build();

        when(jsonMatcher.find()).thenReturn(true).thenReturn(false);
        when(jsonMatcher.current()).thenReturn(jsonArray).thenReturn(null);

        JsonBracketChildMatcherFactory jsonChildMatcherFactory = new JsonBracketChildMatcherFactory(jsonMatcherFactory, "4");
        JsonMatcher jsonBracketChildMatcher = jsonChildMatcherFactory.fromRoot(JsonValue.NULL);

        assertFalse(jsonBracketChildMatcher.find());
        assertNull(jsonBracketChildMatcher.current());

        verify(jsonMatcher, times(2)).find();
    }

    @Test
    public void testJsonArrayNegativeIndex() {
        JsonArray jsonArray = Json.createArrayBuilder()
                .add("string")
                .add(1)
                .build();

        when(jsonMatcher.find()).thenReturn(true).thenReturn(false);
        when(jsonMatcher.current()).thenReturn(jsonArray).thenReturn(null);

        JsonBracketChildMatcherFactory jsonChildMatcherFactory = new JsonBracketChildMatcherFactory(jsonMatcherFactory, "-1");
        JsonMatcher jsonBracketChildMatcher = jsonChildMatcherFactory.fromRoot(JsonValue.NULL);

        assertFalse(jsonBracketChildMatcher.find());
        assertNull(jsonBracketChildMatcher.current());

        verify(jsonMatcher, times(2)).find();
    }

    @Test
    public void testJsonNull() {
        when(jsonMatcher.find()).thenReturn(true).thenReturn(false);
        when(jsonMatcher.current()).thenReturn(JsonValue.NULL).thenReturn(null);

        JsonBracketChildMatcherFactory jsonChildMatcherFactory = new JsonBracketChildMatcherFactory(jsonMatcherFactory, "-1");
        JsonMatcher jsonBracketChildMatcher = jsonChildMatcherFactory.fromRoot(JsonValue.NULL);

        assertFalse(jsonBracketChildMatcher.find());
        assertNull(jsonBracketChildMatcher.current());

        verify(jsonMatcher, times(2)).find();
    }

    @Test
    public void testJsonTrue() {
        when(jsonMatcher.find()).thenReturn(true).thenReturn(false);
        when(jsonMatcher.current()).thenReturn(JsonValue.TRUE).thenReturn(null);

        JsonBracketChildMatcherFactory jsonChildMatcherFactory = new JsonBracketChildMatcherFactory(jsonMatcherFactory, "-1");
        JsonMatcher jsonBracketChildMatcher = jsonChildMatcherFactory.fromRoot(JsonValue.NULL);

        assertFalse(jsonBracketChildMatcher.find());
        assertNull(jsonBracketChildMatcher.current());

        verify(jsonMatcher, times(2)).find();
    }

    @Test
    public void testJsonFalse() {
        when(jsonMatcher.find()).thenReturn(true).thenReturn(false);
        when(jsonMatcher.current()).thenReturn(JsonValue.FALSE).thenReturn(null);

        JsonBracketChildMatcherFactory jsonChildMatcherFactory = new JsonBracketChildMatcherFactory(jsonMatcherFactory, "-1");
        JsonMatcher jsonBracketChildMatcher = jsonChildMatcherFactory.fromRoot(JsonValue.NULL);

        assertFalse(jsonBracketChildMatcher.find());
        assertNull(jsonBracketChildMatcher.current());

        verify(jsonMatcher, times(2)).find();
    }

    @Test
    public void testJsonNumber() {
        when(jsonMatcher.find()).thenReturn(true).thenReturn(false);
        when(jsonMatcher.current()).thenReturn(JsonNumbers.fromInt(1)).thenReturn(null);

        JsonBracketChildMatcherFactory jsonChildMatcherFactory = new JsonBracketChildMatcherFactory(jsonMatcherFactory, "-1");
        JsonMatcher jsonBracketChildMatcher = jsonChildMatcherFactory.fromRoot(JsonValue.NULL);

        assertFalse(jsonBracketChildMatcher.find());
        assertNull(jsonBracketChildMatcher.current());

        verify(jsonMatcher, times(2)).find();
    }
}
