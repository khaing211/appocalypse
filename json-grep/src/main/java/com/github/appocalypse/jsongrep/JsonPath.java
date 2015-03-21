package com.github.appocalypse.jsongrep;

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
    public void source(JsonValue source);
}
