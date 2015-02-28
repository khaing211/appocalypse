package com.github.appocalypse.jsongrep.impl;

import javax.json.Json;
import javax.json.JsonNumber;
import java.math.BigDecimal;
import java.math.BigInteger;

public interface JsonNumbers {
    public static JsonNumber fromInt(int number) {
        return Json.createObjectBuilder().add("tmp", number).build().getJsonNumber("tmp");
    }

    public static JsonNumber fromLong(long number) {
        return Json.createObjectBuilder().add("tmp", number).build().getJsonNumber("tmp");
    }

    public static JsonNumber fromDouble(Double number) {
        return Json.createObjectBuilder().add("tmp", number).build().getJsonNumber("tmp");
    }

    public static JsonNumber fromBigInteger(BigInteger number) {
        return Json.createObjectBuilder().add("tmp", number).build().getJsonNumber("tmp");
    }

    public static JsonNumber fromBigDecimal(BigDecimal number) {
        return Json.createObjectBuilder().add("tmp", number).build().getJsonNumber("tmp");
    }
}
