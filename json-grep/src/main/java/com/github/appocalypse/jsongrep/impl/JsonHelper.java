package com.github.appocalypse.jsongrep.impl;

import javax.json.Json;
import javax.json.JsonNumber;
import javax.json.JsonString;
import java.math.BigDecimal;
import java.math.BigInteger;

public interface JsonHelper {
    public static JsonNumber fromInt(int number) {
        return Json.createObjectBuilder().add("tmp", number).build().getJsonNumber("tmp");
    }

    public static JsonNumber fromLong(long number) {
        return Json.createObjectBuilder().add("tmp", number).build().getJsonNumber("tmp");
    }

    public static JsonNumber fromDouble(double number) {
        return Json.createObjectBuilder().add("tmp", number).build().getJsonNumber("tmp");
    }

    public static JsonNumber fromBigInteger(BigInteger number) {
        return Json.createObjectBuilder().add("tmp", number).build().getJsonNumber("tmp");
    }

    public static JsonNumber fromBigDecimal(BigDecimal number) {
        return Json.createObjectBuilder().add("tmp", number).build().getJsonNumber("tmp");
    }

    public static JsonString fromString(String value) {
        return Json.createObjectBuilder().add("tmp", value).build().getJsonString("tmp");
    }
}
