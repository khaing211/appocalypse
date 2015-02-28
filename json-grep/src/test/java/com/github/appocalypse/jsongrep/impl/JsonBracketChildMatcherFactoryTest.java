package com.github.appocalypse.jsongrep.impl;

import com.github.appocalypse.jsongrep.JsonMatcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.json.JsonValue;

import static org.mockito.Matchers.any;
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
