/**
 * Copyright (c) 2010 Sense Tecnic Systems Inc.
 */
package com.sensetecnic.client;

import java.io.Serializable;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * User data object.
 * 
 * @author Mike Blackstock, Vincent Tsao
 */
public class User implements Serializable
{
	private static final long serialVersionUID = 1L;

	private long id;

	@NotEmpty
	@Size (min = 4, max = 50)
	private String username;

	@NotEmpty
	@Size (max = 50)
	private String firstname;

	@NotEmpty
	@Size (max = 50)
	private String lastname;

	@NotEmpty
	@Size (min = 6, max = 50)
	private String password;

	private boolean enabled;
	
	private String timeZone;

	public long getId ()
	{
		return id;
	}

	public void setId (long id)
	{
		this.id = id;
	}

	public String getUsername ()
	{
		return username;
	}

	public void setUsername (String username)
	{
		this.username = username;
	}

	public String getFirstname ()
	{
		return firstname;
	}

	public void setFirstname (String firstname)
	{
		this.firstname = firstname;
	}

	public String getLastname ()
	{
		return lastname;
	}

	public void setLastname (String lastname)
	{
		this.lastname = lastname;
	}

	public String getPassword ()
	{
		return password;
	}

	public void setPassword (String password)
	{
		this.password = password;
	}

	public boolean getEnabled ()
	{
		return enabled;
	}

	public void setEnabled (boolean enabled)
	{
		this.enabled = enabled;
	}
	
	public String getTimeZone() {
		return this.timeZone;
	}
	
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	@Override
	public int hashCode ()
	{
		final int prime = 31;
		int result = 1;

		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((username == null) ? 0 : username.hashCode ());
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode ());
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode ());
		result = prime * result + ((password == null) ? 0 : password.hashCode ());

		return result;
	}

	@Override
	public boolean equals (Object obj)
	{
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (getClass () != obj.getClass ())
			return false;

		User other = (User) obj;

		if (id != other.id)
			return false;

		if (username == null)
		{
			if (other.username != null)
				return false;
		}
		else if (!username.equals (other.username))
			return false;

		if (firstname == null)
		{
			if (other.firstname != null)
				return false;
		}
		else if (!firstname.equals (other.firstname))
			return false;

		if (lastname == null)
		{
			if (other.lastname != null)
				return false;
		}
		else if (!lastname.equals (other.lastname))
			return false;

		if (password == null)
		{
			if (other.password != null)
				return false;
		}
		else if (!password.equals (other.password))
			return false;

		return true;
	}

	@Override
	public String toString ()
	{
		return "User [id=" + id + ", username=" + username + ", firstname=" + firstname + ", lastname=" + lastname + "]";
	}
}
