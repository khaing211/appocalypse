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
public class JsonChildMatcherFactoryTest {

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

        JsonChildMatcherFactory jsonChildMatcherFactory = new JsonChildMatcherFactory(jsonMatcherFactory, "match");
        JsonMatcher jsonChildMatcher = jsonChildMatcherFactory.fromRoot(JsonValue.NULL);

        assertTrue(jsonChildMatcher.find());
        assertTrue(jsonChildMatcher.current() instanceof JsonString);
        assertEquals("this", ((JsonString)jsonChildMatcher.current()).getString());

        assertFalse(jsonChildMatcher.find());
        assertNull(jsonChildMatcher.current());

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

        JsonChildMatcherFactory jsonChildMatcherFactory = new JsonChildMatcherFactory(jsonMatcherFactory, "nonmatch");
        JsonMatcher jsonChildMatcher = jsonChildMatcherFactory.fromRoot(JsonValue.NULL);

        assertFalse(jsonChildMatcher.find());
        assertNull(jsonChildMatcher.current());

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

        JsonChildMatcherFactory jsonChildMatcherFactory = new JsonChildMatcherFactory(jsonMatcherFactory, "match");
        JsonMatcher jsonChildMatcher = jsonChildMatcherFactory.fromRoot(JsonValue.NULL);

        assertFalse(jsonChildMatcher.find());
        assertNull(jsonChildMatcher.current());

        verify(jsonMatcher, times(2)).find();
    }

    @Test
    public void testJsonNull() {
        when(jsonMatcher.find()).thenReturn(true).thenReturn(false);
        when(jsonMatcher.current()).thenReturn(JsonValue.NULL).thenReturn(null);

        JsonChildMatcherFactory jsonChildMatcherFactory = new JsonChildMatcherFactory(jsonMatcherFactory, "match");
        JsonMatcher jsonChildMatcher = jsonChildMatcherFactory.fromRoot(JsonValue.NULL);

        assertFalse(jsonChildMatcher.find());
        assertNull(jsonChildMatcher.current());

        verify(jsonMatcher, times(2)).find();
    }

    @Test
    public void testJsonTrue() {
        when(jsonMatcher.find()).thenReturn(true).thenReturn(false);
        when(jsonMatcher.current()).thenReturn(JsonValue.TRUE).thenReturn(null);

        JsonChildMatcherFactory jsonChildMatcherFactory = new JsonChildMatcherFactory(jsonMatcherFactory, "match");
        JsonMatcher jsonChildMatcher = jsonChildMatcherFactory.fromRoot(JsonValue.NULL);

        assertFalse(jsonChildMatcher.find());
        assertNull(jsonChildMatcher.current());

        verify(jsonMatcher, times(2)).find();
    }

    @Test
    public void testJsonFalse() {
        when(jsonMatcher.find()).thenReturn(true).thenReturn(false);
        when(jsonMatcher.current()).thenReturn(JsonValue.FALSE).thenReturn(null);

        JsonChildMatcherFactory jsonChildMatcherFactory = new JsonChildMatcherFactory(jsonMatcherFactory, "match");
        JsonMatcher jsonChildMatcher = jsonChildMatcherFactory.fromRoot(JsonValue.NULL);

        assertFalse(jsonChildMatcher.find());
        assertNull(jsonChildMatcher.current());

        verify(jsonMatcher, times(2)).find();
    }

    @Test
    public void testJsonNumber() {
        when(jsonMatcher.find()).thenReturn(true).thenReturn(false);
        when(jsonMatcher.current()).thenReturn(JsonNumbers.fromInt(3)).thenReturn(null);

        JsonChildMatcherFactory jsonChildMatcherFactory = new JsonChildMatcherFactory(jsonMatcherFactory, "match");
        JsonMatcher jsonChildMatcher = jsonChildMatcherFactory.fromRoot(JsonValue.NULL);

        assertFalse(jsonChildMatcher.find());
        assertNull(jsonChildMatcher.current());

        verify(jsonMatcher, times(2)).find();
    }
}
