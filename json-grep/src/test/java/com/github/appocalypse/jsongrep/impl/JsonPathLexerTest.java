package com.github.appocalypse.jsongrep.impl;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class JsonPathLexerTest {
    @Test
    public void testProperty() {
        final JsonPathLexer lexer = new JsonPathLexer("$.aaaa.bbbb");
        JsonPathToken token = null;

        token = lexer.nextToken();
        assertTrue(token instanceof JsonPathToken.DollarSignToken);
        assertEquals(0, token.getCharIndex());
        assertEquals("$", token.getValue());

        token = lexer.nextToken();
        assertTrue(token instanceof JsonPathToken.DotToken);
        assertEquals(1, token.getCharIndex());
        assertEquals(".", token.getValue());

        token = lexer.nextToken();
        assertTrue(token instanceof JsonPathToken.StringToken);
        assertEquals(2, token.getCharIndex());
        assertEquals("aaaa", token.getValue());

        token = lexer.nextToken();
        assertTrue(token instanceof JsonPathToken.DotToken);
        assertEquals(6, token.getCharIndex());
        assertEquals(".", token.getValue());

        token = lexer.nextToken();
        assertTrue(token instanceof JsonPathToken.StringToken);
        assertEquals(7, token.getCharIndex());
        assertEquals("bbbb", token.getValue());
    }

    @Test
    public void testDescendant() {
        final JsonPathLexer lexer = new JsonPathLexer("$..bbbb");
        JsonPathToken token = null;

        token = lexer.nextToken();
        assertTrue(token instanceof JsonPathToken.DollarSignToken);
        assertEquals(0, token.getCharIndex());
        assertEquals("$", token.getValue());

        token = lexer.nextToken();
        assertTrue(token instanceof JsonPathToken.TwoDotsToken);
        assertEquals(1, token.getCharIndex());
        assertEquals("..", token.getValue());

        token = lexer.nextToken();
        assertTrue(token instanceof JsonPathToken.StringToken);
        assertEquals(3, token.getCharIndex());
        assertEquals("bbbb", token.getValue());
    }

    @Test
    public void testSquareBracket() {
        final JsonPathLexer lexer = new JsonPathLexer("$[bbbb]");
        JsonPathToken token = null;

        token = lexer.nextToken();
        assertTrue(token instanceof JsonPathToken.DollarSignToken);
        assertEquals(0, token.getCharIndex());
        assertEquals("$", token.getValue());

        token = lexer.nextToken();
        assertTrue(token instanceof JsonPathToken.OpenSquareBracketToken);
        assertEquals(1, token.getCharIndex());
        assertEquals("[", token.getValue());

        token = lexer.nextToken();
        assertTrue(token instanceof JsonPathToken.StringToken);
        assertEquals(2, token.getCharIndex());
        assertEquals("bbbb", token.getValue());

        token = lexer.nextToken();
        assertTrue(token instanceof JsonPathToken.ClosedSquareBracketToken);
        assertEquals(6, token.getCharIndex());
        assertEquals("]", token.getValue());
    }

    @Test
    public void testPredicate() {
        final JsonPathLexer lexer = new JsonPathLexer("$[?(@.aa >= 2)]");
        JsonPathToken token = null;

        token = lexer.nextToken();
        assertTrue(token instanceof JsonPathToken.DollarSignToken);
        assertEquals(0, token.getCharIndex());
        assertEquals("$", token.getValue());

        token = lexer.nextToken();
        assertTrue(token instanceof JsonPathToken.OpenSquareBracketToken);
        assertEquals(1, token.getCharIndex());
        assertEquals("[", token.getValue());

        token = lexer.nextToken();
        assertTrue(token instanceof JsonPathToken.QuestionToken);
        assertEquals(2, token.getCharIndex());
        assertEquals("?", token.getValue());

        token = lexer.nextToken();
        assertTrue(token instanceof JsonPathToken.OpenRoundBracketToken);
        assertEquals(3, token.getCharIndex());
        assertEquals("(", token.getValue());

        token = lexer.nextToken();
        assertTrue(token instanceof JsonPathToken.AtToken);
        assertEquals(4, token.getCharIndex());
        assertEquals("@", token.getValue());

        token = lexer.nextToken();
        assertTrue(token instanceof JsonPathToken.DotToken);
        assertEquals(5, token.getCharIndex());
        assertEquals(".", token.getValue());

        token = lexer.nextToken();
        assertTrue(token instanceof JsonPathToken.StringToken);
        assertEquals(6, token.getCharIndex());
        assertEquals("aa", token.getValue());

        token = lexer.nextToken();
        assertTrue(token instanceof JsonPathToken.SpaceToken);
        assertEquals(8, token.getCharIndex());
        assertEquals(" ", token.getValue());

        token = lexer.nextToken();
        assertTrue(token instanceof JsonPathToken.GreaterThanOrEqualToken);
        assertEquals(9, token.getCharIndex());
        assertEquals(">=", token.getValue());

        token = lexer.nextToken();
        assertTrue(token instanceof JsonPathToken.SpaceToken);
        assertEquals(11, token.getCharIndex());
        assertEquals(" ", token.getValue());

        token = lexer.nextToken();
        assertTrue(token instanceof JsonPathToken.StringToken);
        assertEquals(12, token.getCharIndex());
        assertEquals("2", token.getValue());

        token = lexer.nextToken();
        assertTrue(token instanceof JsonPathToken.ClosedRoundBracketToken);
        assertEquals(13, token.getCharIndex());
        assertEquals(")", token.getValue());


        token = lexer.nextToken();
        assertTrue(token instanceof JsonPathToken.ClosedSquareBracketToken);
        assertEquals(14, token.getCharIndex());
        assertEquals("]", token.getValue());
    }
}
