/**
 * Copyright (c) 2010 Sense Tecnic Systems Inc.
 */
package com.sensetecnic.client;

/**
 * A single sensor data reading.
 * 
 * @author mike
 *
 */
public interface FeedData {
	
	/** set the schema for the feed data */
	public void setSchema(SensorSchema schema);
	
	/** get this feedData's schema */
	public SensorSchema getSchema();
	
	/** @return The feed-unique id of this data element. */
	public long getId();

	/** @return the timestamp of when this data was collected. */
	public long getTimestamp();
	
	/**
	 * @return the latitude
	 */
	public double getLatitude();

	/**
	 * @return the longitude
	 */
	public double getLongitude();

	/** @return the name of the feed that this data comes from (i.e. sensor name,topic, channel) */
	public String getFeedName();
	
	/**
	 * Get the default sensor data name.  This is the "data" field or the first field's long name.
	 * 
	 * @return
	 */
	public String getDefaultDataName();
	
	/**
	 * Get the default data value.  This is the "value" field or the first field's data.
	 * 
	 * @return
	 */
	public double getDefaultValue();
	
	/**
	 * Retrieves an attribute's value.
	 * 
	 * @param name the name of the attribute to retrieve its value.
	 * @return the value.
	 */
	public Object getAttribute(String name);

	/**
	 * Returns true if the specified attribute exists.
	 * 
	 * @param name name of the attribute to check.
	 * @return true if the attribute exists, false otherwise.
	 */
	public boolean hasAttribute(String name);

	/**
	 * Get all of the attribute names.
	 * 
	 * @return
	 */
	public String[] getAttributeNames();
	
}
