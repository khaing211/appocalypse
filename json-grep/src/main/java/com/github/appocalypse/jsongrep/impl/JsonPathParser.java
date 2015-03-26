package com.github.appocalypse.jsongrep.impl;

import com.github.appocalypse.jsongrep.*;

import javax.json.Json;
import javax.json.stream.JsonParsingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        return root(Arrays.asList(JsonPathToken.EofToken.class));
    }

    private static boolean isOneOfClasses(Object object, List<Class> classes) {
        return classes
                .stream()
                .filter(i -> i.isAssignableFrom(object.getClass()))
                .findAny()
                .isPresent();
    }

    private JsonPath root(List<Class> expectedTerminateTokenClasses) {
        JsonPath jsonPath = new SourceJsonPath();

        while (true) {
            JsonPathToken token = lexer.nextToken();

            if (token instanceof JsonPathToken.DotToken) {
                jsonPath = property(jsonPath);
            } else if (token instanceof JsonPathToken.TwoDotsToken) {
                jsonPath = descendant(jsonPath);
            } else if (token instanceof JsonPathToken.OpenSquareBracketToken) {
                jsonPath = openSquareBracket(jsonPath);
            } else if (isOneOfClasses(token, expectedTerminateTokenClasses)) {
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
            return new PropertyJsonPath(jsonPath, token.getValue());
        } else if (token instanceof JsonPathToken.AsteriskToken) {
            return new AnyJsonPath(jsonPath);
        }

        throw new JsonPathParseException("Unexpected token " + token.getValue(), token.getCharIndex());
    }

    private JsonPath openSquareBracket(JsonPath jsonPath) {
        final JsonPathToken token = lexer.nextToken();

        if (token instanceof JsonPathToken.StringToken) {
            final JsonPathToken nextToken = lexer.peek();
            if (nextToken instanceof JsonPathToken.SemiColonToken) {
                jsonPath = sliceBracket(token, jsonPath);
            } else {
                jsonPath = simpleBracket(token, jsonPath);
            }
        } else if (token instanceof JsonPathToken.QuoteToken) {
            final JsonPathToken quoteToken = quote();
            jsonPath = new PropertyJsonPath(jsonPath, quoteToken.getValue());
        } else if  (token instanceof JsonPathToken.AsteriskToken) {
            jsonPath = new AnyJsonPath(jsonPath);
        } else if (token instanceof JsonPathToken.QuestionToken) {
            jsonPath = predicateBracket(jsonPath);
        }

        final JsonPathToken closeSquareBracketToken = lexer.nextToken();
        if (!(closeSquareBracketToken instanceof JsonPathToken.ClosedSquareBracketToken)) {
            throw new JsonPathParseException("Expect ] but got " + token.getValue(), token.getCharIndex());
        }

        return jsonPath;
    }

    private JsonPathToken quote() {
        final int index = lexer.index();
        lexer.spaceSensitive(false);

        JsonPathToken token = JsonPathToken.string(index, "");
        JsonPathToken nextToken = null;
        do {
            nextToken = lexer.nextToken();

            if (nextToken instanceof JsonPathToken.EofToken) {
                throw new JsonPathParseException("Expect ' but got " + token.getValue(), token.getCharIndex());
            } else if (!(nextToken instanceof JsonPathToken.QuoteToken)) {
                token = JsonPathToken.string(index, token.getValue() + nextToken.getValue());
            }
        } while (!(nextToken instanceof JsonPathToken.QuoteToken));

        lexer.spaceSensitive(true);
        return token;
    }

    private JsonPath simpleBracket(JsonPathToken token, JsonPath jsonPath) {
        try {
            final int index = Integer.parseInt(token.getValue());
            return new IndexJsonPath(jsonPath, index);
        } catch (NumberFormatException ignore) {
            return new PropertyJsonPath(jsonPath, token.getValue());
        }
    }

    private JsonPath sliceBracket(JsonPathToken token, JsonPath jsonPath) {
        try {
            final int start = Integer.parseInt(token.getValue());

            // consume first semi-colon
            lexer.nextToken();

            JsonPathToken lookAheadToken = lexer.peek();
            if (lookAheadToken instanceof JsonPathToken.ClosedSquareBracketToken) {
                return new SliceJsonPath(jsonPath, start, -1, 1);
            }

            // end
            token = lexer.nextToken();
            final int end = Integer.parseInt(token.getValue());

            lookAheadToken = lexer.peek();
            if (lookAheadToken instanceof JsonPathToken.ClosedSquareBracketToken) {
                return new SliceJsonPath(jsonPath, start, end, 1);
            }

            if (!(lookAheadToken instanceof JsonPathToken.SemiColonToken)) {
                throw new JsonPathParseException("Expect : but got " + lookAheadToken.getValue(), lookAheadToken.getCharIndex());
            }

            // consume second semi-colon
            lexer.nextToken();

            token = lexer.nextToken();
            final int step = Integer.parseInt(token.getValue());

            return new SliceJsonPath(jsonPath, start, end, step);
        } catch (NumberFormatException e) {
            throw new JsonPathParseException("Expect a number but got " + token.getValue(), token.getCharIndex());
        }
    }

    private JsonPath predicateBracket(JsonPath jsonPath) {
        JsonPathToken token = null;

        JsonPredicate leftPredicate, rightPredicate;

        token = lexer.nextToken();
        if (!(token instanceof JsonPathToken.OpenRoundBracketToken)) {
            throw new JsonPathParseException("Expect ( but got " + token.getValue(), token.getCharIndex());
        }

        // left predicate
        token = lexer.nextToken();
        if (token instanceof JsonPathToken.AtToken) {
            leftPredicate = JsonPredicate.current(root(Arrays.asList(JsonPathToken.SpaceToken.class, JsonPathToken.ComparisonToken.class)));
            lexer.rewind();
        } else if (token instanceof JsonPathToken.DollarSignToken) {
            leftPredicate = JsonPredicate.current(root(Arrays.asList(JsonPathToken.SpaceToken.class, JsonPathToken.ComparisonToken.class)));
            lexer.rewind();
        } else if (token instanceof JsonPathToken.StringToken) {
            leftPredicate = JsonPredicate.constant(token.getValue());
        } else {
            throw new JsonPathParseException("Expect $,@,or string but got " + token.getValue(), token.getCharIndex());
        }

        // comparison token
        JsonPathToken comparisonToken = lexer.nextToken();
        if (comparisonToken instanceof JsonPathToken.SpaceToken) {
            comparisonToken = lexer.nextToken();
        }

        if (!(comparisonToken instanceof JsonPathToken.ComparisonToken)) {
            throw new JsonPathParseException("Expect comparison operator but got " + comparisonToken.getValue(), comparisonToken.getCharIndex());
        }

        // right predicate
        token = lexer.nextToken();
        if (token instanceof JsonPathToken.SpaceToken) {
            token = lexer.nextToken();
        }


        if (token instanceof JsonPathToken.AtToken) {
            rightPredicate = JsonPredicate.current(root(Arrays.asList(JsonPathToken.ClosedRoundBracketToken.class)));
            lexer.rewind();
        } else if (token instanceof JsonPathToken.DollarSignToken) {
            rightPredicate = JsonPredicate.root(root(Arrays.asList(JsonPathToken.ClosedRoundBracketToken.class)));
            lexer.rewind();
        } else if (token instanceof JsonPathToken.StringToken) {
            rightPredicate = JsonPredicate.constant(token.getValue());
        } else {
            throw new JsonPathParseException("Expect $,@,or string but got " + token.getValue(), token.getCharIndex());
        }

        token = lexer.nextToken();
        if (!(token instanceof JsonPathToken.ClosedRoundBracketToken)) {
            throw new JsonPathParseException("Expect ) but got " + token.getValue(), token.getCharIndex());
        }

        if (comparisonToken instanceof JsonPathToken.EqualToken) {
            return new PredicateJsonPath(jsonPath, leftPredicate.eq(rightPredicate));
        } else if (comparisonToken instanceof JsonPathToken.LessThanToken) {
            return new PredicateJsonPath(jsonPath, leftPredicate.lt(rightPredicate));
        } else if (comparisonToken instanceof JsonPathToken.LessThanOrEqualToken) {
            return new PredicateJsonPath(jsonPath, leftPredicate.lte(rightPredicate));
        } else if (comparisonToken instanceof JsonPathToken.GreaterThanToken) {
            return new PredicateJsonPath(jsonPath, leftPredicate.gt(rightPredicate));
        } else if (comparisonToken instanceof JsonPathToken.GreaterThanOrEqualToken) {
            return new PredicateJsonPath(jsonPath, leftPredicate.gte(rightPredicate));
        } else {
            throw new JsonPathParseException("Unexpected comparison operator " + comparisonToken.getValue(), comparisonToken.getCharIndex());
        }
    }
}
