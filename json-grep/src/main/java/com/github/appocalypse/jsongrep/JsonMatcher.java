package com.github.appocalypse.jsongrep;

import javax.json.JsonValue;

public interface JsonMatcher {
    /**
     * Call find() in order to find next match
     *
     * @return true if match is found. false otherwise.
     */
    public boolean find();

    /**
     * @return current match if find() return true; null otherwise.
     */
    public JsonValue current();
}
