package com.github.appocalypse.jsongrep.impl.predicate;

import java.math.BigDecimal;

import javax.json.JsonString;
import javax.json.JsonValue;
import javax.json.JsonValue.ValueType;

import com.github.appocalypse.jsongrep.impl.JsonNumbers;

public interface JsonValueExtractor {
	public JsonValue apply(JsonValue source);
	
	public static class JsonStringExtractor implements JsonValueExtractor {
		final private JsonString value;
		
		public JsonStringExtractor(String value) {
			this.value = new JsonStringImpl(value);
		}

		@Override
		public JsonValue apply(JsonValue source) {
			return value;
		}
	}
	
	public static class DynamicJsonValueExtractor implements JsonValueExtractor {
		final private String value;
		
		public DynamicJsonValueExtractor(String value) {
			this.value = value;
		}

		@Override
		public JsonValue apply(JsonValue source) {
			if (source.getValueType() == ValueType.NUMBER) {
				try {
					return JsonNumbers.fromBigDecimal(new BigDecimal(value));
				} catch (NumberFormatException ignore) { }
			} 
			
			if (source.getValueType() == ValueType.TRUE ||
				source.getValueType() == ValueType.FALSE) {
				
				if (Boolean.parseBoolean(value)) {
					return JsonValue.TRUE;
				} else {
					return JsonValue.FALSE;
				}
			}
			
			// default to String type
			return new JsonStringImpl(value);
		}
	}
	
	public static class ExpressionJsonValueExtractor implements JsonValueExtractor {
		@Override
		public JsonValue apply(JsonValue source) {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	
	static class JsonStringImpl implements JsonString {
		final private String value;
		
		public JsonStringImpl(String value) {
			this.value = value;
		}
		
		@Override
		public ValueType getValueType() {
			return ValueType.STRING;
		}

		@Override
		public String getString() {
			return value;
		}

		@Override
		public CharSequence getChars() {
			return value;
		}
	}
}
