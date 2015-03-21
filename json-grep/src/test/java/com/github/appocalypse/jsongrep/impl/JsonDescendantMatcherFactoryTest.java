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
public class JsonDescendantMatcherFactoryTest {

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

        JsonDescendantMatcherFactory jsonDescendantMatcherFactory = new JsonDescendantMatcherFactory(jsonMatcherFactory);
        JsonMatcher jsonDescendantMatcher = jsonDescendantMatcherFactory.fromPreviousMatcher(jsonMatcher);

        assertTrue(jsonDescendantMatcher.find());
        assertTrue(jsonDescendantMatcher.current() instanceof JsonObject);

        assertTrue(jsonDescendantMatcher.find());
        assertTrue(jsonDescendantMatcher.current() instanceof JsonString);
        assertEquals("world", ((JsonString) jsonDescendantMatcher.current()).getString());

        assertTrue(jsonDescendantMatcher.find());
        assertTrue(jsonDescendantMatcher.current() instanceof JsonString);
        assertEquals("this", ((JsonString) jsonDescendantMatcher.current()).getString());

        assertFalse(jsonDescendantMatcher.find());
        assertNull(jsonDescendantMatcher.current());

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

        JsonDescendantMatcherFactory jsonDescendantMatcherFactory = new JsonDescendantMatcherFactory(jsonMatcherFactory);
        JsonMatcher jsonDescendantMatcher = jsonDescendantMatcherFactory.fromPreviousMatcher(jsonMatcher);

        assertTrue(jsonDescendantMatcher.find());
        assertTrue(jsonDescendantMatcher.current() instanceof JsonArray);

        assertTrue(jsonDescendantMatcher.find());
        assertTrue(jsonDescendantMatcher.current() instanceof JsonNumber);
        assertEquals(1, ((JsonNumber) jsonDescendantMatcher.current()).intValue());

        assertTrue(jsonDescendantMatcher.find());
        assertTrue(jsonDescendantMatcher.current() instanceof JsonString);
        assertEquals("string", ((JsonString) jsonDescendantMatcher.current()).getString());

        assertFalse(jsonDescendantMatcher.find());
        assertNull(jsonDescendantMatcher.current());

        verify(jsonMatcher, times(2)).find();
    }

    @Test
    public void testJsonNull() {
        when(jsonMatcher.find()).thenReturn(true).thenReturn(false);
        when(jsonMatcher.current()).thenReturn(JsonValue.NULL).thenReturn(null);

        JsonDescendantMatcherFactory jsonDescendantMatcherFactory = new JsonDescendantMatcherFactory(jsonMatcherFactory);
        JsonMatcher jsonDescendantMatcher = jsonDescendantMatcherFactory.fromPreviousMatcher(jsonMatcher);

        assertTrue(jsonDescendantMatcher.find());
        assertEquals(JsonValue.NULL, jsonDescendantMatcher.current());

        assertFalse(jsonDescendantMatcher.find());
        assertNull(jsonDescendantMatcher.current());

        verify(jsonMatcher, times(2)).find();
    }

    @Test
    public void testJsonTrue() {
        when(jsonMatcher.find()).thenReturn(true).thenReturn(false);
        when(jsonMatcher.current()).thenReturn(JsonValue.TRUE).thenReturn(null);

        JsonDescendantMatcherFactory jsonDescendantMatcherFactory = new JsonDescendantMatcherFactory(jsonMatcherFactory);
        JsonMatcher jsonDescendantMatcher = jsonDescendantMatcherFactory.fromPreviousMatcher(jsonMatcher);

        assertTrue(jsonDescendantMatcher.find());
        assertEquals(JsonValue.TRUE, jsonDescendantMatcher.current());

        assertFalse(jsonDescendantMatcher.find());
        assertNull(jsonDescendantMatcher.current());

        verify(jsonMatcher, times(2)).find();
    }

    @Test
    public void testJsonFalse() {
        when(jsonMatcher.find()).thenReturn(true).thenReturn(false);
        when(jsonMatcher.current()).thenReturn(JsonValue.FALSE).thenReturn(null);

        JsonDescendantMatcherFactory jsonDescendantMatcherFactory = new JsonDescendantMatcherFactory(jsonMatcherFactory);
        JsonMatcher jsonDescendantMatcher = jsonDescendantMatcherFactory.fromPreviousMatcher(jsonMatcher);

        assertTrue(jsonDescendantMatcher.find());
        assertEquals(JsonValue.FALSE, jsonDescendantMatcher.current());

        assertFalse(jsonDescendantMatcher.find());
        assertNull(jsonDescendantMatcher.current());

        verify(jsonMatcher, times(2)).find();
    }

    @Test
    public void testJsonNumber() {
        when(jsonMatcher.find()).thenReturn(true).thenReturn(false);
        when(jsonMatcher.current()).thenReturn(JsonHelper.fromInt(1)).thenReturn(null);

        JsonDescendantMatcherFactory jsonDescendantMatcherFactory = new JsonDescendantMatcherFactory(jsonMatcherFactory);
        JsonMatcher jsonDescendantMatcher = jsonDescendantMatcherFactory.fromPreviousMatcher(jsonMatcher);

        assertTrue(jsonDescendantMatcher.find());
        assertEquals(JsonHelper.fromInt(1), jsonDescendantMatcher.current());

        assertFalse(jsonDescendantMatcher.find());
        assertNull(jsonDescendantMatcher.current());

        verify(jsonMatcher, times(2)).find();
    }
}
