package com.github.appocalypse.jsongrep;

import com.github.appocalypse.jsongrep.impl.JsonHelper;

import javax.json.Json;
import javax.json.JsonNumber;
import javax.json.JsonValue;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.function.Predicate;

public interface JsonPredicate {

    public static JsonPredicate constant(final String value) {
        return new JsonPredicate() {
            @Override
            public JsonValue value() {
                return JsonHelper.fromString(value);
            }
        };
    }

    public static JsonPredicate constant(final int value) {
        return new JsonPredicate() {
            @Override
            public JsonValue value() {
                return JsonHelper.fromInt(value);
            }
        };
    }

    public static JsonPredicate constant(final BigDecimal value) {
        return new JsonPredicate() {
            @Override
            public JsonValue value() {
                return JsonHelper.fromBigDecimal(value);
            }
        };
    }

    public static JsonPredicate constant(final long value) {
        return new JsonPredicate() {
            @Override
            public JsonValue value() {
                return JsonHelper.fromLong(value);
            }
        };
    }

    public static JsonPredicate constant(final double value) {
        return new JsonPredicate() {
            @Override
            public JsonValue value() {
                return JsonHelper.fromDouble(value);
            }
        };
    }

    public static JsonPredicate constant(BigInteger value) {
        return new JsonPredicate() {
            @Override
            public JsonValue value() {
                return JsonHelper.fromBigInteger(value);
            }
        };
    }

    public static JsonPredicate current(JsonPath jsonPath) {
        return new JsonPredicate() {
            private JsonContext context;

            @Override
            public JsonValue value() {
                jsonPath.source(context.current());
                return jsonPath.evaluate()
                        .findAny()
                        .orElseThrow(() -> new IllegalArgumentException("not matching anything"));
            }

            @Override
            public void context(JsonContext context) {
                this.context = context;
            }
        };
    }

    public static JsonPredicate root(JsonPath jsonPath) {
        return new JsonPredicate() {
            private JsonContext context;

            @Override
            public JsonValue value() {
                jsonPath.source(context.root());
                return jsonPath.evaluate()
                        .findAny()
                        .orElseThrow(() -> new IllegalArgumentException("not matching anything"));
            }

            @Override
            public void context(JsonContext context) {
                this.context = context;
            }
        };
    }

    public JsonValue value();

    public default void context(JsonContext context) { }

    public default Predicate<JsonContext> lt(JsonPredicate parameter) {
        return jsonContext -> {
            this.context(jsonContext);
            parameter.context(jsonContext);
            return JsonPredicateComparator.INSTANCE.compare(this, parameter) < 0;
        };
    }

    public default Predicate<JsonContext> lte(JsonPredicate parameter) {
        return jsonContext -> {
            this.context(jsonContext);
            parameter.context(jsonContext);
            return JsonPredicateComparator.INSTANCE.compare(this, parameter) <= 0;
        };
    }

    public default Predicate<JsonContext> eq(JsonPredicate parameter) {
        return jsonContext -> {
            this.context(jsonContext);
            parameter.context(jsonContext);
            return JsonPredicateComparator.INSTANCE.compare(this, parameter) == 0;
        };
    }

    public default Predicate<JsonContext> gte(JsonPredicate parameter) {
        return jsonContext -> {
            this.context(jsonContext);
            parameter.context(jsonContext);
            return JsonPredicateComparator.INSTANCE.compare(this, parameter) >= 0;
        };
    }

    public default Predicate<JsonContext> gt(JsonPredicate parameter) {
        return jsonContext -> {
            this.context(jsonContext);
            parameter.context(jsonContext);
            return JsonPredicateComparator.INSTANCE.compare(this, parameter) > 0;
        };
    }

    static enum JsonPredicateComparator implements Comparator<JsonPredicate> {
        INSTANCE;

        @Override
        public int compare(JsonPredicate left, JsonPredicate right) {
            return 0;
        }
    }
}
