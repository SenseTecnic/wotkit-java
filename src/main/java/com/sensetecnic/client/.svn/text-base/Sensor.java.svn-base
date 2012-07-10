/**
 * Copyright (c) 2010 Sense Tecnic Systems Inc.
 */
package com.sensetecnic.client;

import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Data model for a sensor.
 * 
 * @author mike
 * 
 */
public class Sensor {

	private Long id; // so it can be null when new/unassigned

	@NotEmpty
	@Size(min = 4, max = 50)
	private String name;

	@NotEmpty
	@Size(min = 4, max = 100)
	private String longName;

	private String description;
	private boolean hasLocation;
	private double locLat;
	private double locLon;
	private String topic; // in_channel
	private String outTopic; // out_channel
	private String url; // control_url
	private boolean isPrivate;

	private long ownerId;
	private User owner;
	
	private long timeStamp;
	
	private boolean isActive; // is the sensor active in last 15 min

	private boolean subscribed; // is subscribed by the current user (not saved
								// in db)

	// use default until set
	private SensorSchema schema = new SensorSchema();

	@JsonIgnore
	public boolean isNew() {
		return id == null;
	}

	/**
	 * Get the output topic name. Note that the name is generated if it doesn't
	 * exist, but the sensor has a name.
	 * 
	 * @return
	 */
	public String getOutTopic() {
		// if we don't have a topic, but we do have a name, use this.
		if (outTopic == null) {
			if (this.getName() != null) {
				outTopic = "sensor." + getName() + ".out";
			}
		}
		return outTopic;
	}

	public void setOutTopic(String outTopic) {
		this.outTopic = outTopic;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isHasLocation() {
		return hasLocation;
	}

	public void setHasLocation(boolean hasLocation) {
		this.hasLocation = hasLocation;
	}

	public double getLatitude() {
		return locLat;
	}

	public void setLatitude(double locLat) {
		this.locLat = locLat;
	}

	public double getLongitude() {
		return locLon;
	}

	public void setLongitude(double locLon) {
		this.locLon = locLon;
	}

	/**
	 * Return the topic name from the database, or generate it from the sensor
	 * name if it doesn't exist.
	 * 
	 * @return the topic name
	 */
	public String getTopic() {
		// if we don't have a topic, but we do have a sensor name, use this.
		// this is to support older sensors with different names than topics
		if (topic == null) {
			if (this.getName() != null) {
				topic = "sensor." + getName();
			}
		}
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public boolean isPrivate() {
		return isPrivate;
	}

	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	public long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public SensorSchema getSchema() {
		return schema;
	}

	public void setSchema(SensorSchema schema) {
		this.schema = schema;
	}

	public void setSubscribed(boolean isSubscribed) {
		this.subscribed = isSubscribed;
	}

	public boolean isSubscribed() {
		return subscribed;
	}
	
	public long getTimeStamp() {
		return timeStamp;
	}
	
	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public boolean isActive() {
		return this.isActive;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + (hasLocation ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (isPrivate ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(locLat);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(locLon);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((longName == null) ? 0 : longName.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((outTopic == null) ? 0 : outTopic.hashCode());
		result = prime * result + (int) (ownerId ^ (ownerId >>> 32));
		result = prime * result + ((topic == null) ? 0 : topic.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
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
		Sensor other = (Sensor) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (hasLocation != other.hasLocation)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isPrivate != other.isPrivate)
			return false;
		if (Double.doubleToLongBits(locLat) != Double
				.doubleToLongBits(other.locLat))
			return false;
		if (Double.doubleToLongBits(locLon) != Double
				.doubleToLongBits(other.locLon))
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
		if (outTopic == null) {
			if (other.outTopic != null)
				return false;
		} else if (!outTopic.equals(other.outTopic))
			return false;
		if (ownerId != other.ownerId)
			return false;
		if (topic == null) {
			if (other.topic != null)
				return false;
		} else if (!topic.equals(other.topic))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Sensor [id=" + id + ", name=" + name + ", longName=" + longName
				+ ", description=" + description + ", owner=" + owner
				+ ", topic=" + topic + ", locLat=" + locLat + ", locLon="
				+ locLon + ", hasLocation=" + hasLocation + ", isPrivate="
				+ isPrivate + ", outTopic=" + outTopic + ", ownerId=" + ownerId
				+ ", schema=" + schema + ", url=" + url + "]";
	}

}
