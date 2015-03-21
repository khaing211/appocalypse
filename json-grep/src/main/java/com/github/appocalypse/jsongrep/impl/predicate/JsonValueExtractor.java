package com.github.appocalypse.jsongrep.impl.predicate;

import com.github.appocalypse.jsongrep.impl.JsonHelper;

import javax.json.JsonString;
import javax.json.JsonValue;
import javax.json.JsonValue.ValueType;
import java.math.BigDecimal;

public interface JsonValueExtractor {
	public JsonValue apply(JsonValue source);
	
	public static class JsonStringExtractor implements JsonValueExtractor {
		final private JsonString value;
		
		public JsonStringExtractor(String value) {
			this.value = JsonHelper.fromString(value);
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
					return JsonHelper.fromBigDecimal(new BigDecimal(value));
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
			return JsonHelper.fromString(value);
		}
	}
	
	public static class ExpressionJsonValueExtractor implements JsonValueExtractor {
		@Override
		public JsonValue apply(JsonValue source) {
			// TODO Auto-generated method stub
			return null;
		}
	}
}
