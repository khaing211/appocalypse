package com.github.appocalypse.jsongrep.impl;

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



}
