package com.github.appocalypse.jsongrep;

import com.github.appcalypse.jdk.extra.function.IntPredicates;
import com.github.appcalypse.jdk.extra.function.Predicates;
import com.github.appcalypse.jdk.extra.util.Eithers;
import com.github.appocalypse.jsongrep.impl.*;

import javax.json.JsonStructure;
import java.util.Optional;
import java.util.function.Predicate;

public class JsonPattern {
    final private JsonMatcherFactory jsonMatcherFactory;

    public JsonPattern(JsonMatcherFactory jsonMatcherFactory) {
        this.jsonMatcherFactory = jsonMatcherFactory;
    }

    public JsonMatcher matcher(JsonStructure root) {
        if (root == null) {
            throw new NullPointerException("root cannot be null");
        }

        return jsonMatcherFactory.fromRoot(root);
    }

    private static final String ROOT = "$";
    private static final String DOT = ".";
    private static final String OPEN_SQUARE_BRACKET = "[";
    private static final String CLOSED_SQUARE_BRACKET = "]";
    private static final String ASTERISK = "*";
    private static final String SEMI_COLON = ":";
    private static final String AT = "@";
    private static final String QUESTION = "?";
    private static final String OPEN_ROUND_BRACKET = "(";
    private static final String CLOSED_ROUND_BRACKET = ")";

    public static JsonPattern compile(String pattern) {
        if (pattern == null) throw new NullPointerException("pattern cannot be null");
        // parse pattern

        final Predicate<Integer> lessThanPatternLength = Predicates.boxed(IntPredicates.lessThan(pattern.length()));

        final Optional<Integer> rootIndex = eat(ROOT, pattern, 0);

        if (!rootIndex.isPresent()) {
            throw new JsonPatternParseException("pattern have to start with '" + ROOT + "'", 0);
        }

        // side-effect variables
        JsonMatcherFactory matcherFactory = new JsonRootMatcherFactory();
        Optional<Integer> index = rootIndex;

        while (index.filter(lessThanPatternLength).isPresent()) {
            final int currentIndex = index.get();

            // handling dot operator
            index = eat(DOT, pattern, currentIndex);
            if (index.isPresent()) {
                // find the next dot
                final Optional<Integer> nextDotIndex = eatTill(DOT, pattern, index.get());

                if (nextDotIndex.isPresent()) {
                    final String value = pattern.substring(index.get(), nextDotIndex.get());
                    matcherFactory = handleDot(matcherFactory, value);

                    // move index to the next index
                    index = nextDotIndex;
                } else {
                    final Optional<Integer> nextBracketIndex = eatTill(OPEN_SQUARE_BRACKET, pattern, index.get());

                    if (nextBracketIndex.isPresent()) {
                        final String value = pattern.substring(index.get(), nextBracketIndex.get());

                        if (value.isEmpty()) {
                            throw new JsonPatternParseException("pattern does not expected empty access after '.'", index.get());
                        }

                        matcherFactory = handleDot(matcherFactory, value);

                        index = nextBracketIndex;

                    } else {
                        final String value = pattern.substring(index.get());

                        if (value.isEmpty()) {
                            throw new JsonPatternParseException("pattern does not expected empty access after '.'", pattern.length() - 1);
                        }

                        matcherFactory = handleDot(matcherFactory, value);

                        // move index to the end
                        index = Optional.of(pattern.length());
                    }
                }

                continue;
            }

            // handling bracket operator
            index = eat(OPEN_SQUARE_BRACKET, pattern, currentIndex);
            if (index.isPresent()) {
                final Optional<Integer> nextIndex = eatTill(CLOSED_SQUARE_BRACKET, pattern, index.get());

                if (!nextIndex.isPresent()) {
                    throw new JsonPatternParseException("pattern expect to have '" + CLOSED_SQUARE_BRACKET
                            + "' matching to '" + OPEN_SQUARE_BRACKET + "'", currentIndex);
                }


                final String value = pattern.substring(index.get(), nextIndex.get());

                if (value.isEmpty()) {
                    throw new JsonPatternParseException("pattern expected to have non-empty string inside bracket", currentIndex);
                }

                matcherFactory = handleBracket(matcherFactory, value);

                // move index to after closed square bracket index
                index = Optional.of(nextIndex.get() + 1);

                continue;
            }

            throw new JsonPatternParseException("pattern expect to have '.' or '['", currentIndex);
        }

        return new JsonPattern(matcherFactory);
    }

    /**
     * @return next startInclusiveIndex
     */
    private static Optional<Integer> eat(String token, String pattern, int startInclusiveIndex) {
        if (startInclusiveIndex < 0) {
            return Optional.empty();
        }

        if (startInclusiveIndex + token.length() > pattern.length()) {
            return Optional.empty();
        }

        if (pattern.startsWith(token, startInclusiveIndex)) {
            return Optional.of(startInclusiveIndex + token.length());
        }

        return Optional.empty();
    }

    /**
     * @return next token index from start index
     */
    private static Optional<Integer> eatTill(String token, String pattern, int startInclusiveIndex) {
        final int indexOfToken = pattern.indexOf(token, startInclusiveIndex);
        if (0 < indexOfToken) {
            return Optional.of(indexOfToken);
        } else {
            return Optional.empty();
        }
    }


    private static JsonMatcherFactory handleDot(JsonMatcherFactory previousMatcherFactory, String value) {
        if (value.isEmpty()) {
            return new JsonDescendantMatcherFactory(previousMatcherFactory);
        } else if (ASTERISK.equals(value)) {
            return new JsonAnyChildMatcherFactory(previousMatcherFactory);
        } else {
            return new JsonChildMatcherFactory(previousMatcherFactory, value);
        }
    }

    private static JsonMatcherFactory handleBracket(JsonMatcherFactory previousMatcherFactory, String value) {
        // TODO: handle value: index, filter use cases
        final Optional<JsonMatcherFactory> jsonArrayMatcherFactory = createJsonArrayMatcherFactory(previousMatcherFactory, value);
        if (jsonArrayMatcherFactory.isPresent()) {
            return jsonArrayMatcherFactory.get();
        } else if (ASTERISK.equals(value)) {
            return new JsonAnyChildMatcherFactory(previousMatcherFactory);
        } else {
            return new JsonChildMatcherFactory(previousMatcherFactory, value);
        }
    }

    private static Optional<JsonMatcherFactory> createJsonArrayMatcherFactory(JsonMatcherFactory previousMatcherFactory, String value) {
        final int firstSemiColonIndex = value.indexOf(':');
        final int secondSemiColonIndex = value.indexOf(':', firstSemiColonIndex);
        if (firstSemiColonIndex == -1) {
            return Optional.empty();
        }

        final String startIndexStr = value.substring(0, firstSemiColonIndex);
        final String endIndexStr = secondSemiColonIndex == -1 ?
                value.substring(firstSemiColonIndex + 1) : value.substring(firstSemiColonIndex + 1, secondSemiColonIndex);
        final String stepStr = secondSemiColonIndex == -1 ?
                "1" : value.substring(secondSemiColonIndex + 1);

        final Optional<Integer> startIndex = Eithers.wrap(() -> Integer.parseInt(startIndexStr), NumberFormatException.class)
                .right();

        final Optional<Integer> endIndex = Eithers.wrap(() -> Integer.parseInt(endIndexStr), NumberFormatException.class)
                .right();

        final Optional<Integer> step = Eithers.wrap(() -> Integer.parseInt(stepStr), NumberFormatException.class)
                .right()
                .filter(Predicates.boxed(IntPredicates.greaterThan(0)));

        if (!startIndex.isPresent() || !endIndex.isPresent() || !step.isPresent()) {
            return Optional.empty();
        }

        return Optional.of(new JsonArrayMatcherFactory(previousMatcherFactory, startIndex.get(), endIndex.get(), step.get()));
    }
}
