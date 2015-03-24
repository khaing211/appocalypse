package com.github.appocalypse.jsongrep.impl;

import com.github.appocalypse.jsongrep.JsonPath;
import com.github.appocalypse.jsongrep.JsonPathParseException;
import com.github.appocalypse.jsongrep.SourceJsonPath;

public class JsonPathParser {
    final private JsonPathLexer lexer;

    public JsonPathParser(String pattern) {
        lexer = new JsonPathLexer(pattern);
    }

    public JsonPath parse() throws JsonPathParseException {
        // TODO:
        return new SourceJsonPath();
    }
}
