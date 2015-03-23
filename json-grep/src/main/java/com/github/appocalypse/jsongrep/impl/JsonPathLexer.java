package com.github.appocalypse.jsongrep.impl;

import javax.json.Json;

public class JsonPathLexer {

    final private String pattern;
    private int index;

    public JsonPathLexer(String pattern) {
        this.pattern = pattern;
        this.index = 0;
    }

    public JsonPathLexer(String pattern, int startIndex) {
        this.pattern = pattern;
        this.index = startIndex;
    }

    /**
     * Current index changes by next token
     *
     * @return token
     */
    public JsonPathToken nextToken() {
        // TODO;
        return new JsonPathToken.NoneToken();
    }
}
