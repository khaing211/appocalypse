package com.github.appocalypse.pandemic.token;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringTokenTest {
    @Test
    public void testBufferShorterThanValue() {
        Token token = new StringToken("build");
        ImmutableList<String> values = token.values("b");
        assertEquals(values.toString(), 1, values.size());
    }

    @Test
    public void testBufferLongerThanValue() {
        Token token = new StringToken("build");
        ImmutableList<String> values = token.values("build a house");
        assertEquals(values.toString(), 1, values.size());
    }

    @Test
    public void testBufferSameLengthValue() {
        Token token = new StringToken("build");
        ImmutableList<String> values = token.values("build");
        assertEquals(values.toString(), 1, values.size());
    }

    @Test
    public void testBufferShorterThanValueUnmatched() {
        Token token = new StringToken("house");
        ImmutableList<String> values = token.values("hoo");
        assertEquals(values.toString(), 0, values.size());
    }

    @Test
    public void testBufferLongerThanValueUnmatched() {
        Token token = new StringToken("house");
        ImmutableList<String> values = token.values("housa ");
        assertEquals(values.toString(), 0, values.size());
    }
}
