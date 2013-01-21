/**
 * Copyright (c) 2010 Sense Tecnic Systems Inc.
 */
package com.sensetecnic.client;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * Information about a field, its name, units, long name, etc.
 * 
 * @author mike
 *
 */
public class FieldInfo {

	public static enum Type {
		STRING(0),
		NUMBER(1);
		
		private int typeCode;
		
		Type(int typeCode) {
			this.typeCode = typeCode;
		}
		
		public int getCode() {
			return typeCode;
		}
	}
	
	// fancy way to map integer type codes to Type enums
	final static class EnumMap {
		static Map<Integer, Type> intMap = new HashMap<Integer, Type>(2);
		
		static {
			for (Type t:Type.values()) {
				intMap.put(t.getCode(), t);
			}
		}
		
		public static Type intValue(int typeCode) {
			return intMap.get(typeCode);
		}
	}
	
	private int index;
	private String name;
	private String longName;
	private String units;
	private Type type;
	private boolean required;
	
	public static class Builder {
		
		private int index;
		private String name;
		private String longName;
		private String units;
		private Type type;
		private boolean required;
		
		public Builder() {
			index = 0;
			name = null;
			longName = "Untitled";
			units = null;
			type = Type.STRING;
		}
		
		public FieldInfo build() {
			FieldInfo newField = new FieldInfo();
			newField.index = index;
			newField.name = name;
			newField.longName = longName;
			newField.units = units;
			newField.type = type;
			newField.required = required;
			
			return newField;
		}
		
		public Builder index(int index) {
			this.index = index;
			return this;
		}
		public Builder name(String name) {
			this.name = name;
			return this;
		}
		public Builder longName(String longName) {
			this.longName = longName;
			return this;
		}
		public Builder units(String units) {
			this.units = units;
			return this;
		}
		public Builder type(Type type) {
			this.type = type;
			return this;
		}
		public Builder required(boolean required) {
			this.required = required;
			return this;
		}
	}
	
	private FieldInfo(Builder builder) {
		
	}
	
	public FieldInfo() {
		this(new Builder());
	}
	
	public FieldInfo(FieldInfo fieldInfo) {
		this.index = fieldInfo.getIndex();
		this.name = fieldInfo.getName();
		this.longName = fieldInfo.getLongName();
		this.units = fieldInfo.getUnits();
		this.type = fieldInfo.getType();
		this.required = fieldInfo.isRequired();
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLongName() {
		return longName;
	}
	public void setLongName(String longName) {
		this.longName = longName;
	}
	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		this.units = units;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getIndex() {
		return index;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public Type getType() {
		return type;
	}
	@JsonIgnore
	public void setTypeCode(int typeCode) {
		this.type = EnumMap.intValue(typeCode);
	}
	@JsonIgnore
	public int getTypeCode() {
		return this.type.getCode();
	}
	public boolean isRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}

	@Override
	public String toString() {
		return "FieldInfo [index=" + index + ", longName=" + longName
				+ ", name=" + name + ", required=" + required + ", type="
				+ type + ", units=" + units + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + index;
		result = prime * result
				+ ((longName == null) ? 0 : longName.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (required ? 1231 : 1237);
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((units == null) ? 0 : units.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FieldInfo other = (FieldInfo) obj;
		if (index != other.index)
			return false;
		if (longName == null) {
			if (other.longName != null)
				return false;
		} else if (!longName.equals(other.longName))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (required != other.required)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (units == null) {
			if (other.units != null)
				return false;
		} else if (!units.equals(other.units))
			return false;
		return true;
	}

}
