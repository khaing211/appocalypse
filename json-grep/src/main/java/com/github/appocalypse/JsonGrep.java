package com.github.appocalypse;

import javax.json.*;
import javax.json.stream.JsonParser;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

/**
 * Implement json grep by passing in json path
 * http://goessner.net/articles/JsonPath/
 *
 * $                = root object
 * . or []          = child operator
 * ..               = descendant operator
 * *                = match all
 * [number]         = subscript if provide
 * [a,b]            = union operator
 * [start:end:step] = array slice (support negative index)
 * ?()              = filter
 *
 * Example:
 * $..book[?(@.price<10)]	= find all books with price less than 10
 * $..book[-1:]             = find all last book in array
 * $.store.book[*].author	= find all books author
 * $[*]                     = match all provided root object is an array
 */
public class JsonGrep {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Missing <pattern> arguments");
            System.exit(1);
        }

        final String pattern = args[0];
        final InputStreamReader systemReader = new InputStreamReader(System.in);
        final LineNumberReader lineNumberReader = new LineNumberReader(systemReader);
        final JsonParser jsonParser = Json.createParser(lineNumberReader);

        if (!jsonParser.hasNext()) return;

        final JsonParser.Event firstEvent = jsonParser.next();
        switch (firstEvent) {
            case START_ARRAY: break;
            case START_OBJECT: break;
            default: return;
        }
    }
}
