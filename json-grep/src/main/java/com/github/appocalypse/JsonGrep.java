package com.github.appocalypse;

import com.github.appocalypse.jsongrep.JsonPath;

import javax.json.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * Implement json grep by passing in json path
 * http://goessner.net/articles/JsonPath/
 *
 * $                = root object
 * . or []          = child operator
 * ..               = descendant operator (but not at end)
 * *                = match all
 * [number]         = subscript if provide
 * [a,b]            = union operator
 * [start:end:step] = array slice (support negative index)
 *                    start = mandatory, inclusive, support negative index
 *                    end = optional, inclusive, default -1 i.e. end of array
 *                    step = optional, positive number, default 1
 *                    example: [0:], [0:3], [0:-1:1], [-1:-1:2], [-5:-1:2]
 * [?()]            = filter
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
        final BufferedReader bufferedReader = new BufferedReader(systemReader);
        final JsonReader jsonReader = Json.createReader(bufferedReader);

        final JsonStructure root = jsonReader.read();

        final LinkedList<JsonValue> results = JsonPath.path(pattern)
                .source(root)
                .evaluate()
                .collect(Collectors.toCollection(LinkedList::new));

        final boolean isAllObject = results.stream().allMatch(it -> it.getValueType() == JsonValue.ValueType.OBJECT);

        if (isAllObject) {
            printCsv(results);
        } else {
            results.stream().forEach(JsonGrep::println);
        }
    }

    public static void println(JsonValue jsonValue) {
        if (jsonValue instanceof JsonString) {
            System.out.println(((JsonString)jsonValue).getString());
        } else {
            System.out.println(jsonValue.toString());
        }
    }

    /**
     * Print JsonObject as csv, it does not handle inner object or inner array
     */
    public static void printCsv(LinkedList<JsonValue> results) {
        // TODO:
    }
}
