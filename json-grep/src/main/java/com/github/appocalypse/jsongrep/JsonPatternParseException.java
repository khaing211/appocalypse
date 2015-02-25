package com.github.appocalypse.jsongrep;

public class JsonPatternParseException extends RuntimeException {
    final private int errorIndex;

    public JsonPatternParseException(int errorIndex) {
        this(null, null, errorIndex);
    }

    public JsonPatternParseException(String message, int errorIndex) {
        this(message, null, errorIndex);
    }

    public JsonPatternParseException(Throwable cause, int errorIndex) {
        this(null, cause, errorIndex);
    }

    public JsonPatternParseException(String message, Throwable cause, int errorIndex) {
        super(message == null ?
                        "parsing error occurs at index: " + errorIndex
                        : message + " occurs at index: " + errorIndex,
                cause);
        this.errorIndex = errorIndex;
    }

    public int getErrorIndex() {
        return errorIndex;
    }
}
