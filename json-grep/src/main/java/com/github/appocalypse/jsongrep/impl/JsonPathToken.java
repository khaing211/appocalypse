package com.github.appocalypse.jsongrep.impl;

import com.github.appocalypse.jsongrep.JsonPath;

public interface JsonPathToken {

    public default int getCharIndex() { return 0; }

    public default String getValue() { return null; }

    public static JsonPathToken eof(int charIndex) {
        return new EofToken(charIndex);
    }

    public static JsonPathToken asterisk(int charIndex) {
        return new AsteriskToken(charIndex);
    }

    public static JsonPathToken dot(int charIndex) {
        return new DotToken(charIndex);
    }

    public static JsonPathToken twoDots(int charIndex) {
        return new TwoDotsToken(charIndex);
    }

    public static JsonPathToken openSquareBracket(int charIndex) {
        return new OpenSquareBracketToken(charIndex);
    }

    public static JsonPathToken closeSquareBracket(int charIndex) {
        return new ClosedSquareBracketToken(charIndex);
    }

    public static JsonPathToken dollarSign(int charIndex) {
        return new DollarSignToken(charIndex);
    }

    public static JsonPathToken at(int charIndex) {
        return new AtToken(charIndex);
    }

    public static JsonPathToken question(int charIndex) {
        return new QuestionToken(charIndex);
    }

    public static JsonPathToken semiColon(int charIndex) {
        return new SemiColonToken(charIndex);
    }

    public static JsonPathToken openRoundBracket(int charIndex) {
        return new OpenRoundBracketToken(charIndex);
    }

    public static JsonPathToken closeRoundBracket(int charIndex) {
        return new ClosedRoundBracketToken(charIndex);
    }

    public static JsonPathToken quote(int charIndex) {
        return new QuoteToken(charIndex);
    }

    public static JsonPathToken lessThan(int charIndex) {
        return new LessThanToken(charIndex);
    }

    public static JsonPathToken lessThanOrEqual(int charIndex) {
        return new LessThanOrEqualToken(charIndex);
    }

    public static JsonPathToken equal(int charIndex) {
        return new EqualToken(charIndex);
    }

    public static JsonPathToken greaterThan(int charIndex) {
        return new GreaterThanToken(charIndex);
    }

    public static JsonPathToken greaterThanOrEqual(int charIndex) {
        return new GreaterThanOrEqualToken(charIndex);
    }

    public static JsonPathToken string(int charIndex, String value) {
        return new StringToken(charIndex, value);
    }

    public static abstract class AbstractToken implements JsonPathToken {
        final private int charIndex;

        public AbstractToken(int charIndex) {
            this.charIndex = charIndex;
        }

        @Override
        public int getCharIndex() {
            return charIndex;
        }
    }

    final public static class DotToken extends AbstractToken {
        public DotToken(int charIndex) {
            super(charIndex);
        }

        @Override
        public String getValue() {
            return ".";
        }
    }

    final public static class TwoDotsToken extends AbstractToken {
        public TwoDotsToken(int charIndex) {
            super(charIndex);
        }

        @Override
        public String getValue() {
            return "..";
        }
    }

    final public static class OpenSquareBracketToken extends AbstractToken {

        public OpenSquareBracketToken(int charIndex) {
            super(charIndex);
        }

        @Override
        public String getValue() {
            return "[";
        }
    }

    final public static class ClosedSquareBracketToken extends AbstractToken {

        public ClosedSquareBracketToken(int charIndex) {
            super(charIndex);
        }

        @Override
        public String getValue() {
            return "]";
        }
    }

    final public static class AsteriskToken extends AbstractToken {

        public AsteriskToken(int charIndex) {
            super(charIndex);
        }

        @Override
        public String getValue() {
            return "*";
        }
    }

    final public static class DollarSignToken extends AbstractToken {

        public DollarSignToken(int charIndex) {
            super(charIndex);
        }

        @Override
        public String getValue() {
            return "$";
        }
    }

    final public static class SemiColonToken extends AbstractToken {

        public SemiColonToken(int charIndex) {
            super(charIndex);
        }

        @Override
        public String getValue() {
            return ":";
        }
    }

    final public static class AtToken extends AbstractToken {

        public AtToken(int charIndex) {
            super(charIndex);
        }

        @Override
        public String getValue() {
            return "@";
        }
    }

    final public static class QuestionToken extends AbstractToken {

        public QuestionToken(int charIndex) {
            super(charIndex);
        }

        @Override
        public String getValue() {
            return "?";
        }
    }

    final public static class OpenRoundBracketToken extends AbstractToken {

        public OpenRoundBracketToken(int charIndex) {
            super(charIndex);
        }

        @Override
        public String getValue() {
            return "(";
        }
    }

    final public static class ClosedRoundBracketToken extends AbstractToken {

        public ClosedRoundBracketToken(int charIndex) {
            super(charIndex);
        }

        @Override
        public String getValue() {
            return ")";
        }
    }

    final public static class QuoteToken extends AbstractToken {

        public QuoteToken(int charIndex) {
            super(charIndex);
        }

        @Override
        public String getValue() {
            return "'";
        }
    }


    public static interface ComparisonToken extends JsonPathToken { }

    final public static class LessThanToken extends AbstractToken implements ComparisonToken {

        public LessThanToken(int charIndex) {
            super(charIndex);
        }

        @Override
        public String getValue() {
            return "<";
        }
    }

    final public static class EqualToken extends AbstractToken implements ComparisonToken {

        public EqualToken(int charIndex) {
            super(charIndex);
        }

        @Override
        public String getValue() {
            return "=";
        }
    }

    final public static class GreaterThanToken extends AbstractToken implements ComparisonToken {

        public GreaterThanToken(int charIndex) {
            super(charIndex);
        }

        @Override
        public String getValue() {
            return ">";
        }
    }

    final public static class GreaterThanOrEqualToken extends AbstractToken implements ComparisonToken {

        public GreaterThanOrEqualToken(int charIndex) {
            super(charIndex);
        }

        @Override
        public String getValue() {
            return ">=";
        }
    }

    final public static class LessThanOrEqualToken extends AbstractToken implements ComparisonToken {

        public LessThanOrEqualToken(int charIndex) {
            super(charIndex);
        }

        @Override
        public String getValue() {
            return "<=";
        }
    }

    final public static class StringToken implements JsonPathToken {
        final private String value;
        final private int charIndex;

        public StringToken(int charIndex, String value) {
            this.charIndex = charIndex;
            this.value = value;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public int getCharIndex() {
            return charIndex;
        }
    }

    final public static class EofToken extends AbstractToken {
        public EofToken(int charIndex) {
            super(charIndex);
        }

        @Override
        public String getValue() {
            return "[EOF]";
        }
    }

    final public static class SpaceToken implements JsonPathToken {
        final private String value;
        final private int charIndex;

        public SpaceToken(int charIndex, String value) {
            this.charIndex = charIndex;
            this.value = value;
        }

        @Override
        public String getValue() {
            return value;
        }

        @Override
        public int getCharIndex() {
            return charIndex;
        }
    }
}
