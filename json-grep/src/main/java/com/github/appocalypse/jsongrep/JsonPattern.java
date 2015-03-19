package com.github.appocalypse.jsongrep;

import java.util.Optional;
import java.util.function.Predicate;

import javax.json.JsonValue;

import com.github.appcalypse.jdk.extra.function.IntPredicates;
import com.github.appcalypse.jdk.extra.function.Predicates;
import com.github.appcalypse.jdk.extra.util.Eithers;
import com.github.appocalypse.jsongrep.impl.JsonAnyChildMatcherFactory;
import com.github.appocalypse.jsongrep.impl.JsonArrayMatcherFactory;
import com.github.appocalypse.jsongrep.impl.JsonChildMatcherFactory;
import com.github.appocalypse.jsongrep.impl.JsonDescendantMatcherFactory;
import com.github.appocalypse.jsongrep.impl.JsonMatcherFactory;
import com.github.appocalypse.jsongrep.impl.JsonRootMatcherFactory;

public class JsonPattern {
    final private JsonMatcherFactory jsonMatcherFactory;

    public JsonPattern(JsonMatcherFactory jsonMatcherFactory) {
        this.jsonMatcherFactory = jsonMatcherFactory;
    }

    public JsonMatcher matcher(JsonValue root) {
        if (root == null) {
            throw new NullPointerException("root cannot be null");
        }

        return jsonMatcherFactory.fromRoot(root);
    }

    private static final String DOLLAR_SIGN = "$";
    private static final String DOT = ".";
    private static final String OPEN_SQUARE_BRACKET = "[";
    private static final String CLOSED_SQUARE_BRACKET = "]";
    private static final String ASTERISK = "*";
    private static final String SEMI_COLON = ":";
    private static final String AT = "@";
    private static final String QUESTION = "?";
    private static final String OPEN_ROUND_BRACKET = "(";
    private static final String CLOSED_ROUND_BRACKET = ")";
    
    // operators
    private static final String[] COMPARATORS = new String[] { "=", "<", "<=", ">", ">=", "!=" };
    
    public static JsonPattern compile(String pattern) {
        if (pattern == null) throw new NullPointerException("pattern cannot be null");
        // parse pattern

        final Optional<Integer> rootIndex = eat(DOLLAR_SIGN, pattern, 0);

        if (!rootIndex.isPresent()) {
            throw new JsonPatternParseException("pattern have to start with '" + DOLLAR_SIGN + "'", 0);
        }

        return fromRoot(new JsonRootMatcherFactory(), pattern ,rootIndex.get());
    }
    
    private static JsonPattern fromRoot(final JsonMatcherFactory rootMatcherFactory, final String pattern, final int startIndex) {
        final Predicate<Integer> lessThanPatternLength = Predicates.boxed(IntPredicates.lessThan(pattern.length()));

    	// side-effect variables
        Optional<Integer> index = Optional.of(startIndex);
        JsonMatcherFactory matcherFactory = rootMatcherFactory;

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
        final Optional<JsonMatcherFactory> jsonArrayMatcherFactory = createJsonArrayMatcherFactory(previousMatcherFactory, value);
        if (jsonArrayMatcherFactory.isPresent()) {
            return jsonArrayMatcherFactory.get();
        } 
        
        final Optional<JsonMatcherFactory> jsonPredicateMatcherFactory = createJsonPredicateMatcherFactory(previousMatcherFactory, value);
        if (jsonPredicateMatcherFactory.isPresent()) {
            return jsonPredicateMatcherFactory.get();
        } 
        
        if (ASTERISK.equals(value)) {
            return new JsonAnyChildMatcherFactory(previousMatcherFactory);
        } else {
            return new JsonChildMatcherFactory(previousMatcherFactory, value);
        }
    }

    private static Optional<JsonMatcherFactory> createJsonArrayMatcherFactory(JsonMatcherFactory previousMatcherFactory, String value) {
        final int firstSemiColonIndex = value.indexOf(SEMI_COLON);
        final int secondSemiColonIndex = value.indexOf(SEMI_COLON, firstSemiColonIndex);
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
    
    private static Optional<JsonMatcherFactory> createJsonPredicateMatcherFactory(JsonMatcherFactory previousMatcherFactory, String value) {
    	if (!value.startsWith(QUESTION + OPEN_ROUND_BRACKET) || !value.endsWith(CLOSED_ROUND_BRACKET)) {
    		return Optional.empty();	
    	}
    	
    	value = value.substring(2, value.length() - 1);
    	
    	for (final String comparator : COMPARATORS) {
    		Optional<Integer> index = eatTill(comparator, value, 0);
    		if (index.isPresent()) {
    			final String leftValue = value.substring(0, index.get()).trim();
    			final String rightValue = value.substring(index.get() + comparator.length()).trim();
    			
    			
    			
    			
    		}
    	}
    	
    	return Optional.empty();
    }
    
    private static boolean isJsonString(String value) {
    	return value != null 
    			&& (value.startsWith("'") || value.startsWith("\"")) 
    			&& (value.endsWith("'") || value.endsWith("\"")); 
    }
    
    private static boolean isPattern(String value) {
    	// TODO: not support $ yet, bad design pattern.
    	return value != null 
    			&& (value.startsWith(AT) /*|| value.startsWith(DOLLAR_SIGN)*/ );
    }
}
