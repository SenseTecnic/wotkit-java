/**
 * Copyright (c) 2010 Sense Tecnic Systems Inc.
 */
package com.sensetecnic.client;

import java.util.HashMap;
import java.util.Map;

/**
 * Sensor data sent into the platform from a sensor.
 * 
 * @author mike
 *
 */
public class SensorData implements FeedData {
	
	// ordered hashmap of data
	private Map<String, String> data = new HashMap<String, String>();
	private String feedName;
	
	private long timestamp;
	private long id;
	
	// reserved fields
	public static String LATITUDE_FIELD = "lat";
	public static String LONGITUDE_FIELD = "lng";
	// when data is aggregated
	public static String SOURCE_SENSOR_FIELD = "_sensor";
	
	// schema for the sensor
	private SensorSchema schema;
		
	public SensorData() {
		this.timestamp = System.currentTimeMillis();
		this.feedName = "untitled";
		this.schema = new SensorSchema();
	}

	/**
	 * Create a sensor data element from a map.  This will throw run time
	 * errors if the dataMap doesn't follow "conventions", i.e. that there
	 * is a timestamp, lat, lng, data and value
	 * 
	 * @param dataMap
	 */
	public SensorData(String sensorName, SensorSchema schema, Map<String, String> dataMap) {
		this.feedName = sensorName;
		this.schema = schema;
		
		// check that all of the required fields for this sensor are there
		if (!schema.checkMap(dataMap))
			throw new IllegalArgumentException();
		
		// parse out the timestamp, then remove it from the fields
		this.timestamp = Long.parseLong(dataMap.get("timestamp"));
		dataMap.remove("timestamp");
		
		// now copy in the attributes
		this.data.putAll(dataMap);
	}

	/**
	 * Copy constructor for another FeedData implementation.
	 * 
	 * @param feed
	 */
	public SensorData(FeedData feed) {
		this.feedName = feed.getFeedName();
		this.schema = feed.getSchema();
		this.timestamp = feed.getTimestamp();
		
		for (String attrName: feed.getAttributeNames()) {
			this.data.put(attrName, feed.getAttribute(attrName).toString());
		}
	}

	@Override
	public Object getAttribute(String name) {
		
		String value = data.get(name);
		if (value == null)
			return null;
		
		return schema.parseString(name, value);
	}
	
	/**
	 * Set the attribute
	 * 
	 * @param name
	 * @param value
	 */
	public void setAttribute(String name, Object value) {
		this.data.put(name, value.toString());
	}
	
	/**
	 * Set the corresponding feed name once we know it.
	 * @param feedName
	 */
	public void setFeedName(String feedName) {
		this.feedName = feedName;
	}
	
	@Override
	public String getFeedName() {
		return this.feedName;
	}
	
	/**
	 * Set the id of this element once we know it.
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	@Override
	public long getId() {
		return id;
	}

	@Override
	public long getTimestamp() {
		return timestamp;
	}

	@Override
	public boolean hasAttribute(String name) {
		return data.containsKey(name);
	}

	/**
	 * @return the data
	 */
	public Map<String, String> getData() {
		return data;
	}

	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return Double.parseDouble(data.get("lat"));
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		data.put("lat", Double.toString(latitude));
	}

	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return Double.parseDouble(data.get("lng"));
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(double longitude) {
		data.put("lng", Double.toString(longitude));
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	/* (non-Javadoc)
	 * @see com.sensetecnic.feed.FeedData#getAttributeNames()
	 */
	@Override
	public String[] getAttributeNames() {
		return data.keySet().toArray(new String[0]);
	}

	@Override
	public String getDefaultDataName() {
		String defaultName = data.get("data");
		if (defaultName == null)
			defaultName = schema.getFieldInfo("value").getLongName();
		return defaultName;
	}

	@Override
	public double getDefaultValue() {
		return Double.parseDouble(data.get("value"));
	}

	@Override
	public void setSchema(SensorSchema schema) {
		this.schema = schema;
	}

	@Override
	public SensorSchema getSchema() {
		return schema;
	}

}
