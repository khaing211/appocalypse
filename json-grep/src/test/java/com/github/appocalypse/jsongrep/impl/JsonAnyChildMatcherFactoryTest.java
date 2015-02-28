package com.github.appocalypse.jsongrep.impl;

import com.github.appocalypse.jsongrep.JsonMatcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonString;
import javax.json.JsonValue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JsonAnyChildMatcherFactoryTest {

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

        JsonAnyChildMatcherFactory jsonAnyChildMatcherFactory = new JsonAnyChildMatcherFactory(jsonMatcherFactory);
        JsonMatcher jsonAnyChildMatcher = jsonAnyChildMatcherFactory.fromRoot(JsonValue.NULL);

        assertTrue(jsonAnyChildMatcher.find());
        assertTrue(jsonAnyChildMatcher.current() instanceof JsonString);
        assertEquals("this", ((JsonString)jsonAnyChildMatcher.current()).getString());

        assertTrue(jsonAnyChildMatcher.find());
        assertTrue(jsonAnyChildMatcher.current() instanceof JsonString);
        assertEquals("world", ((JsonString)jsonAnyChildMatcher.current()).getString());

        assertFalse(jsonAnyChildMatcher.find());

        verify(jsonMatcher, times(2)).find();
    }

    @Test
    public void testJsonArray() {

    }

    @Test
    public void testJsonNull() {

    }

    @Test
    public void testJsonTrue() {

    }

    @Test
    public void testJsonFalse() {

    }

    @Test
    public void testJsonNumber() {

    }
}
