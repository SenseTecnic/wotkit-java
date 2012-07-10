package com.sensetecnic.client.oath;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.sensetecnic.client.SenseTecnicException;
import com.sensetecnic.client.Sensor;

/**
 * (c) 2010 Sense Tecnic Systems Inc.   All rights reserved.
 */

/**
 * Work in progress on a template for MEE and others to call Sense Tecnic using
 * the appropriate authenticating REST template.
 * 
 * @author Mike Blackstock (mike)
 * 
 */
public class SenseTecnicTemplate implements SenseTecnicOperations {

	private final static Log logger = LogFactory.getLog(SenseTecnicTemplate.class);

	private final String DEFAULT_BASE_URL = "http://demo.sensetecnic.com/SenseTecnic/api";
	
	private RestTemplate stsRestTemplate;
	private String stsBaseUrl = DEFAULT_BASE_URL;
	
	public void setStsRestTemplate(RestTemplate stsRestTemplate) {
		this.stsRestTemplate = stsRestTemplate;
	}
	
	public void setStsBaseUrl(String stsBaseUrl) {
		this.stsBaseUrl = stsBaseUrl;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see SenseTecnicOperations#getSensor(java.lang.String)
	 */
	@Override
	public Sensor getSensor(String sensorName) throws SenseTecnicException {
		
		Sensor sensor = null;
		
		try {
			sensor = stsRestTemplate.getForObject(stsBaseUrl + "/sensors/" + sensorName, Sensor.class);
		}  catch (RestClientException e) {
			logger.error("RestClientException", e);
			throw new SenseTecnicException("RestClientException", e);
		}
		return sensor;
	}

}
