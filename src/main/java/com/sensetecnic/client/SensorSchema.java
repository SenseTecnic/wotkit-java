/**
 * Copyright (c) 2010 Sense Tecnic Systems Inc.
 */
package com.sensetecnic.client;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;



/**
 * Schema for a sensor; wraps a list of FieldInfo objects.
 * 
 * @author mike
 *
 */
public class SensorSchema {
	
	private LinkedHashMap<String, FieldInfo> fieldMap = new LinkedHashMap<String, FieldInfo>();
	
	final public static SensorSchema defaultSchema = new SensorSchema();
	
	public SensorSchema() {
		// add the default required fields.
		FieldInfo field = new FieldInfo.Builder().name("lat").longName(
				"latitude").type(FieldInfo.Type.NUMBER).required(true).index(0)
				.build();
		fieldMap.put("lat", field);
		field = new FieldInfo.Builder().name("lng").longName("longitude").type(
				FieldInfo.Type.NUMBER).required(true).index(1).build();
		fieldMap.put("lng", field);
		field = new FieldInfo.Builder().name("value").type(FieldInfo.Type.NUMBER)
			.required(true).index(2).build();
		fieldMap.put("value", field);		
	}
	
	/**
	 * Set the fields in the schema
	 * 
	 * @param fields
	 */
	public void setFields(List<FieldInfo> fields) {
		fieldMap.clear();
		for (FieldInfo field: fields) {
			fieldMap.put(field.getName(), field);
		}
	}
	
	/**
	 * Get the fields in the schema in order they should be presented (after id and timestamp)
	 * 
	 * @return
	 */
	public List<FieldInfo> getFields() {
		List<FieldInfo> fieldList = new ArrayList<FieldInfo>();
		
		for (FieldInfo field:fieldMap.values()) {
			fieldList.add(field);
		}
		return fieldList;
	}
	
	/**
	 * Get the field info for a field
	 * 
	 * @param name
	 * @return
	 */
	public FieldInfo getFieldInfo(String name) {
		return fieldMap.get(name);
	}
	
	/**
	 * Return a Java object of the right type given a string value.
	 * 
	 * @param fieldName
	 * @param value
	 * @return
	 */
	public Object parseString(String fieldName, String value) {
		if (isNumber(fieldName))
			try {
				Double d = new Double(value);
				return d;
			} catch (Exception e) {
				return new Double(0.0);
			}
		return value;
	}
	
	/**
	 * Return true if the field is in the schema and a number.
	 * 
	 * @param fieldName
	 * @return
	 */
	private boolean isNumber(String fieldName) {
		FieldInfo field = getFieldInfo(fieldName);
		if ((field != null) && (field.getType() == FieldInfo.Type.NUMBER))
			return true;
		
		return false;
	}
	
	/**
	 * Check that all of the required fields are in the supplied Map
	 * 
	 * @param dataMap
	 * @return
	 */
	public boolean checkMap(Map<String, String> dataMap) {
	
		if (!dataMap.containsKey("timestamp"))
			return false;
		
		for (FieldInfo field:fieldMap.values()) {
			if (field.isRequired() && dataMap.get(field.getName()) == null)
				return false;
		}
		return true;
	}

	/**
	 * Remove a field from the schema.
	 * 
	 * @param fieldName
	 */
	public void deleteField(String fieldName) {
		fieldMap.remove(fieldName);
	}

	/**
	 * Update the field in the schema
	 * 
	 * @param editField
	 */
	public void updateField(FieldInfo editField) {
		fieldMap.put(editField.getName(), editField);
	}

	@Override
	public String toString() {
		return "SensorSchema [fieldMap=" + fieldMap + "]";
	}

}
