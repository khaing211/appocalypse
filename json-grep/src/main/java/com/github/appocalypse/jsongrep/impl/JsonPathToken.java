package com.github.appocalypse.jsongrep.impl;

import com.github.appocalypse.jsongrep.JsonPath;

public interface JsonPathToken {

    public default int getCharIndex() { return 0; }

    public default String getValue() { return null; }


    public static class NoneToken implements JsonPathToken { }

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

    public static class DotToken extends AbstractToken {
        public DotToken(int charIndex) {
            super(charIndex);
        }

        @Override
        public String getValue() {
            return ".";
        }
    }

    public static class OpenSquareBracketToken extends AbstractToken {

        public OpenSquareBracketToken(int charIndex) {
            super(charIndex);
        }

        @Override
        public String getValue() {
            return "[";
        }
    }

    public static class ClosedSquareBracketToken extends AbstractToken {

        public ClosedSquareBracketToken(int charIndex) {
            super(charIndex);
        }

        @Override
        public String getValue() {
            return "]";
        }
    }

    public static class AsteriskToken extends AbstractToken {

        public AsteriskToken(int charIndex) {
            super(charIndex);
        }

        @Override
        public String getValue() {
            return "*";
        }
    }

    public static class DollarSignToken extends AbstractToken {

        public DollarSignToken(int charIndex) {
            super(charIndex);
        }

        @Override
        public String getValue() {
            return "$";
        }
    }

    public static class SemiColonToken extends AbstractToken {

        public SemiColonToken(int charIndex) {
            super(charIndex);
        }

        @Override
        public String getValue() {
            return ":";
        }
    }

    public static class AtToken extends AbstractToken {

        public AtToken(int charIndex) {
            super(charIndex);
        }

        @Override
        public String getValue() {
            return "@";
        }
    }

    public static class QuestionToken extends AbstractToken {

        public QuestionToken(int charIndex) {
            super(charIndex);
        }

        @Override
        public String getValue() {
            return "?";
        }
    }

    public static class OpenRoundBracketToken extends AbstractToken {

        public OpenRoundBracketToken(int charIndex) {
            super(charIndex);
        }

        @Override
        public String getValue() {
            return "(";
        }
    }

    public static class ClosedRoundBracketToken extends AbstractToken {

        public ClosedRoundBracketToken(int charIndex) {
            super(charIndex);
        }

        @Override
        public String getValue() {
            return ")";
        }
    }

    public static interface ComparisonToken { }

    public static class LessThanToken extends AbstractToken implements ComparisonToken {

        public LessThanToken(int charIndex) {
            super(charIndex);
        }

        @Override
        public String getValue() {
            return "<";
        }
    }

    public static class EqualToken extends AbstractToken implements ComparisonToken {

        public EqualToken(int charIndex) {
            super(charIndex);
        }

        @Override
        public String getValue() {
            return "=";
        }
    }

    public static class GreaterThanToken extends AbstractToken implements ComparisonToken {

        public GreaterThanToken(int charIndex) {
            super(charIndex);
        }

        @Override
        public String getValue() {
            return ">";
        }
    }

    public static class GreaterThanOrEqualToken extends AbstractToken implements ComparisonToken {

        public GreaterThanOrEqualToken(int charIndex) {
            super(charIndex);
        }

        @Override
        public String getValue() {
            return ">=";
        }
    }

    public static class LessThanOrEqualToken extends AbstractToken implements ComparisonToken {

        public LessThanOrEqualToken(int charIndex) {
            super(charIndex);
        }

        @Override
        public String getValue() {
            return "<=";
        }
    }

    public static class StringToken implements JsonPathToken {
        final private String value;
        final private int charIndex;

        public StringToken(String value, int charIndex) {
            this.value = value;
            this.charIndex = charIndex;
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
