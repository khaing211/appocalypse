package com.github.appocalypse.jsongrep;

import com.github.appocalypse.jsongrep.impl.JsonHelper;

import javax.json.*;
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
            final JsonValue leftValue = left.value();
            final JsonValue rightValue = right.value();

            return compare(leftValue, rightValue);
        }

        private int compare(final JsonValue leftValue, final JsonValue rightValue) {
            if (leftValue.getValueType() == JsonValue.ValueType.NUMBER &&
                rightValue.getValueType() == JsonValue.ValueType.NUMBER) {

                final JsonNumber leftNumber = (JsonNumber)leftValue;
                final JsonNumber rightNumber = (JsonNumber)rightValue;

                return leftNumber.bigDecimalValue().compareTo(rightNumber.bigDecimalValue());
            }

            if (leftValue.getValueType() == JsonValue.ValueType.NUMBER &&
                rightValue.getValueType() == JsonValue.ValueType.STRING) {

                try {
                    final JsonNumber leftNumber = (JsonNumber)leftValue;
                    final JsonString rightString = (JsonString)rightValue;

                    return leftNumber.bigDecimalValue().compareTo(new BigDecimal(rightString.getString()));
                } catch (NumberFormatException ignore) {}
            }

            if (leftValue.getValueType() == JsonValue.ValueType.STRING &&
                rightValue.getValueType() == JsonValue.ValueType.NUMBER) {

                try {
                    final JsonString leftString = (JsonString)leftValue;
                    final JsonNumber rightNumber = (JsonNumber)rightValue;

                    return new BigDecimal(leftString.getString()).compareTo(rightNumber.bigDecimalValue());
                } catch (NumberFormatException ignore) {}
            }

            if (leftValue.getValueType() == JsonValue.ValueType.ARRAY &&
                rightValue.getValueType() == JsonValue.ValueType.ARRAY) {

                final JsonArray leftArray = (JsonArray)leftValue;
                final JsonArray rightArray = (JsonArray)rightValue;

                final int sizeDiff =  (leftArray.size() - rightArray.size());
                if (sizeDiff != 0) {
                    return sizeDiff;
                }

                for (int i = 0; i < leftArray.size(); i++) {
                    final int diff = compare(leftArray.get(i), rightArray.get(i));
                    if (diff != 0) {
                        return diff;
                    }
                }

                return 0;
            }

            if (leftValue.getValueType() == JsonValue.ValueType.OBJECT &&
                rightValue.getValueType() == JsonValue.ValueType.OBJECT) {

                final JsonObject leftObject = (JsonObject)leftValue;
                final JsonObject rightObject = (JsonObject)rightValue;

                final boolean keyDiff = leftObject.keySet().containsAll(rightObject.keySet());
                if (!keyDiff) {
                    // don't care about the order
                    return -1;
                }

                for (String key : leftObject.keySet()) {
                    final int diff = compare(leftObject.get(key), rightObject.get(key));
                    if (diff != 0) {
                        return diff;
                    }
                }

                return 0;
            }

            return leftValue.toString().compareTo(rightValue.toString());
        }
    }
}
