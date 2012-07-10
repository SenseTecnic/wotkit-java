/**
 * 
 */
package com.sensetecnic.client;


/**
 * @author mike
 *
 */
public class SensorQuery {
	
	private String userName;
	private String text;
	private Boolean subscribed;
	private Boolean isPrivate;
	
	// to come
//	private String[] tagList;
	
	public static class Builder {
		
		private String userName;
		private String text;
		private Boolean subscribed;
		private Boolean isPrivate;
		
		public Builder() {
			userName = null;
			text = null;
			subscribed = null;
			isPrivate = false;
		}
		
		public SensorQuery build() {
			SensorQuery newQuery = new SensorQuery();
			newQuery.setUserName(userName);
			newQuery.setText(text);
			newQuery.setSubscribed(subscribed);
			newQuery.setPrivate(isPrivate);
			
			return newQuery;
		}
		
		public Builder userName(String userName) {
			this.userName = userName;
			return this;
		}
		public Builder text(String text) {
			this.text = text;
			return this;
		}
		public Builder subscribed(Boolean subscribed) {
			this.subscribed = subscribed;
			return this;
		}
		public Builder isPrivate(Boolean isPrivate) {
			this.isPrivate = isPrivate;
			return this;
		}

	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setSubscribed(Boolean subscribed) {
		this.subscribed = subscribed;
	}
	
	public Boolean getSubscribed() {
		return subscribed;
	}
	
	public void setPrivate(Boolean isPrivate) {
		this.isPrivate = isPrivate;
	}
	
	public Boolean isPrivate() {
		return this.isPrivate;
	}
	
	@Override
	public String toString() {
		return "SensorQuery [text=" + text + ", userName=" + userName + "]";
	}



}
