/**
 * (c) 2010 Sense Tecnic Systems, Inc., all rights reserved.
 * 
 */
package com.sensetecnic.client;

/**
 * The exceptions thrown by the SenseTecnicClient
 * 
 * @author Mike Blackstock
 *
 */
public class SenseTecnicException extends Exception {

	private static final long serialVersionUID = 1L;
	
	protected int status = 200;


	/**
	 * 
	 */
	public SenseTecnicException(int status) {
		// TODO Auto-generated constructor stub
		this.status = status;
	}

	/**
	 * @param message
	 */
	public SenseTecnicException(String message, int status) {
		super(message);
		this.status = status;
	}

	/**
	 * @param cause
	 */
	public SenseTecnicException(Throwable cause, int status) {
		super(cause);
		this.status = status;
	}

	/**
	 * @param message
	 * @param cause
	 */
	public SenseTecnicException(String message, Throwable cause, int status) {
		super(message, cause);
		this.status = status;
	}
	
	public SenseTecnicException(Throwable cause) {
		super(cause);
	}
	
	public SenseTecnicException(String message, Throwable cause) {
		super(message, cause);
	}

	public int getStatus() {
		return status;
	}

}
