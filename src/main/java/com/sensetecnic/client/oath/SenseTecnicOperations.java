package com.sensetecnic.client.oath;
import com.sensetecnic.client.SenseTecnicException;
import com.sensetecnic.client.Sensor;

/**
 * (c) 2010 Sense Tecnic Systems Inc.   All rights reserved.
 */

/**
 * Interface to sense tecnic REST API
 * 
 * @author Mike Blackstock (mike)
 *
 */
public interface SenseTecnicOperations {
	
	public Sensor getSensor(String sensorName) throws SenseTecnicException;

}
