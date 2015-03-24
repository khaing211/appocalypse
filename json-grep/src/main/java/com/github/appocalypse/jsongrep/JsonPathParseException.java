package com.github.appocalypse.jsongrep;

public class JsonPathParseException extends RuntimeException {
    final private int errorIndex;

    public JsonPathParseException(int errorIndex) {
        this(null, null, errorIndex);
    }

    public JsonPathParseException(String message, int errorIndex) {
        this(message, null, errorIndex);
    }

    public JsonPathParseException(Throwable cause, int errorIndex) {
        this(null, cause, errorIndex);
    }

    public JsonPathParseException(String message, Throwable cause, int errorIndex) {
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
