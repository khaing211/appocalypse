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
public class JsonArrayMatcherFactoryTest {

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

        JsonArrayMatcherFactory jsonArrayMatcherFactory = new JsonArrayMatcherFactory(jsonMatcherFactory, 0);
        JsonMatcher jsonArrayMatcher = jsonArrayMatcherFactory.fromRoot(JsonValue.NULL);

        assertFalse(jsonArrayMatcher.find());
        assertNull(jsonArrayMatcher.current());

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

        JsonArrayMatcherFactory jsonArrayMatcherFactory = new JsonArrayMatcherFactory(jsonMatcherFactory, 0);
        JsonMatcher jsonArrayMatcher = jsonArrayMatcherFactory.fromRoot(JsonValue.NULL);

        assertTrue(jsonArrayMatcher.find());
        assertTrue(jsonArrayMatcher.current() instanceof JsonString);
        assertEquals("string", ((JsonString)jsonArrayMatcher.current()).getString());

        assertTrue(jsonArrayMatcher.find());
        assertTrue(jsonArrayMatcher.current() instanceof JsonNumber);
        assertEquals(JsonNumbers.fromInt(1), ((JsonNumber)jsonArrayMatcher.current()));

        assertFalse(jsonArrayMatcher.find());
        assertNull(jsonArrayMatcher.current());

        verify(jsonMatcher, times(2)).find();
    }

    @Test
    public void testJsonArrayStep2() {
        JsonArray jsonArray = Json.createArrayBuilder()
                .add("string")
                .add(1)
                .add("2step")
                .build();

        when(jsonMatcher.find()).thenReturn(true).thenReturn(false);
        when(jsonMatcher.current()).thenReturn(jsonArray).thenReturn(null);

        JsonArrayMatcherFactory jsonArrayMatcherFactory = new JsonArrayMatcherFactory(jsonMatcherFactory, 0, -1, 2);
        JsonMatcher jsonArrayMatcher = jsonArrayMatcherFactory.fromRoot(JsonValue.NULL);

        assertTrue(jsonArrayMatcher.find());
        assertTrue(jsonArrayMatcher.current() instanceof JsonString);
        assertEquals("string", ((JsonString)jsonArrayMatcher.current()).getString());

        assertTrue(jsonArrayMatcher.find());
        assertTrue(jsonArrayMatcher.current() instanceof JsonString);
        assertEquals("2step", ((JsonString)jsonArrayMatcher.current()).getString());

        assertFalse(jsonArrayMatcher.find());
        assertNull(jsonArrayMatcher.current());

        verify(jsonMatcher, times(2)).find();
    }


    @Test
    public void testJsonNull() {
        when(jsonMatcher.find()).thenReturn(true).thenReturn(false);
        when(jsonMatcher.current()).thenReturn(JsonValue.NULL).thenReturn(null);

        JsonArrayMatcherFactory jsonArrayMatcherFactory = new JsonArrayMatcherFactory(jsonMatcherFactory, 0);
        JsonMatcher jsonArrayMatcher = jsonArrayMatcherFactory.fromRoot(JsonValue.NULL);

        assertFalse(jsonArrayMatcher.find());
        assertNull(jsonArrayMatcher.current());

        verify(jsonMatcher, times(2)).find();
    }

    @Test
    public void testJsonTrue() {
        when(jsonMatcher.find()).thenReturn(true).thenReturn(false);
        when(jsonMatcher.current()).thenReturn(JsonValue.TRUE).thenReturn(null);

        JsonArrayMatcherFactory jsonArrayMatcherFactory = new JsonArrayMatcherFactory(jsonMatcherFactory, 0);
        JsonMatcher jsonArrayMatcher = jsonArrayMatcherFactory.fromRoot(JsonValue.NULL);

        assertFalse(jsonArrayMatcher.find());
        assertNull(jsonArrayMatcher.current());

        verify(jsonMatcher, times(2)).find();
    }

    @Test
    public void testJsonFalse() {
        when(jsonMatcher.find()).thenReturn(true).thenReturn(false);
        when(jsonMatcher.current()).thenReturn(JsonValue.FALSE).thenReturn(null);

        JsonArrayMatcherFactory jsonArrayMatcherFactory = new JsonArrayMatcherFactory(jsonMatcherFactory, 0);
        JsonMatcher jsonArrayMatcher = jsonArrayMatcherFactory.fromRoot(JsonValue.NULL);

        assertFalse(jsonArrayMatcher.find());
        assertNull(jsonArrayMatcher.current());

        verify(jsonMatcher, times(2)).find();
    }

    @Test
    public void testJsonNumber() {
        when(jsonMatcher.find()).thenReturn(true).thenReturn(false);
        when(jsonMatcher.current()).thenReturn(JsonNumbers.fromInt(1)).thenReturn(null);

        JsonArrayMatcherFactory jsonArrayMatcherFactory = new JsonArrayMatcherFactory(jsonMatcherFactory, 0);
        JsonMatcher jsonArrayMatcher = jsonArrayMatcherFactory.fromRoot(JsonValue.NULL);

        assertFalse(jsonArrayMatcher.find());
        assertNull(jsonArrayMatcher.current());

        verify(jsonMatcher, times(2)).find();
    }
}
