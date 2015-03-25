package com.github.appocalypse.jsongrep.impl;

import com.github.appocalypse.jsongrep.*;

import javax.json.Json;
import javax.json.stream.JsonParsingException;

public class JsonPathParser {
    final private JsonPathLexer lexer;

    public JsonPathParser(String pattern) {
        lexer = new JsonPathLexer(pattern);
    }

    public JsonPath parse() throws JsonPathParseException {
        final JsonPathToken token = lexer.nextToken();
        if (token instanceof JsonPathToken.DollarSignToken) {
            return root();
        }
        throw new JsonPathParseException("Expect $ but got " + token.getValue(), token.getCharIndex());
    }

    private JsonPath root() {
        JsonPath jsonPath = new SourceJsonPath();

        while (true) {
            JsonPathToken token = lexer.nextToken();

            if (token instanceof JsonPathToken.DotToken) {
                jsonPath = property(jsonPath);
            } else if (token instanceof JsonPathToken.TwoDotsToken) {
                jsonPath = descendant(jsonPath);
            } else if (token instanceof JsonPathToken.OpenSquareBracketToken) {
                jsonPath = openSquareBracket(jsonPath);
            } else if (token instanceof JsonPathToken.EofToken) {
                return jsonPath;
            } else {
                throw new JsonPathParseException("Unexpected token " + token.getValue(), token.getCharIndex());
            }
        }
    }

    private JsonPath descendant(JsonPath jsonPath) {
        return property(new DescendantJsonPath(jsonPath));
    }

    private JsonPath property(JsonPath jsonPath) {
        final JsonPathToken token = lexer.nextToken();
        if (token instanceof JsonPathToken.StringToken) {
            if (token.getValue().trim().equals(token.getValue())) {
                return new PropertyJsonPath(jsonPath, token.getValue());
            } else {
                throw new JsonPathParseException("Should not contain space in property: " + token.getValue(), token.getCharIndex());
            }
        } else if (token instanceof JsonPathToken.AsteriskToken) {
            return new AnyJsonPath(jsonPath);
        }

        throw new JsonPathParseException("Unexpected token " + token.getValue(), token.getCharIndex());
    }

    private JsonPath openSquareBracket(JsonPath jsonPath) {
        // TODO:
        return jsonPath;
    }
}
