package com.github.appocalypse.jsongrep;

import com.github.appocalypse.jsongrep.impl.JsonPathParser;

import javax.json.JsonValue;
import java.util.stream.Stream;

public interface JsonPath {
    /**
     * Evaluate the current context of json path
     *
     * @return stream of matching json value
     */
    public Stream<JsonValue> evaluate();

    /**
     * @return context of json path
     */
    public JsonContext context();

    /**
     * Change source of json path
     *
     * @param source
     */
    public JsonPath source(JsonValue source);


    public static JsonPath path(String pattern) throws JsonPathParseException {
        return new JsonPathParser(pattern).parse();
    }
}
