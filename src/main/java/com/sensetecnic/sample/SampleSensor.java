/**
 * 
 */
package com.sensetecnic.sample;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sensetecnic.client.SenseTecnicClient;
import com.sensetecnic.client.SenseTecnicException;
import com.sensetecnic.client.Sensor;

/**
 * @author mike
 *
 */
public class SampleSensor {
	
	private final static Log logger = LogFactory.getLog(SampleSensor.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		SenseTecnicClient client = new SenseTecnicClient();
		
		// test registering a sensor
		Sensor sensor = new Sensor();
		sensor.setName("test-sensor");
		sensor.setLongName("Java Test Sensor");
		sensor.setLatitude(49.345);
		sensor.setLongitude(-123.456);
		sensor.setDescription("this ist he description");
		try {
			client.registerSensor(sensor, "mike", "aMUSEment2");
		} catch (SenseTecnicException e) {
			logger.error("an error occurred when sending data:", e);
		}
		
		try {
			sensor = client.getSensor("test-sensor", "mike", "aMUSEment2");
			System.out.println("Retrieved sensor:"+sensor);
		} catch (SenseTecnicException e) {
			logger.error("an error occurred when sending data:", e);
		}
		
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("lat", "49.22149");
		dataMap.put("lng", "-123.15856");
		
		Random r = new Random();          // Default seed comes from system time.

		while (true) {
			long time = System.currentTimeMillis();
			
			int value = r.nextInt(100);
			dataMap.put("value", Integer.toString(value));
			dataMap.put("timestamp", Long.toString(time));
			try {
				client.sendData("test-sensor", "mike", "aMUSEment2", dataMap);
			} catch (SenseTecnicException e) {
				logger.error("an error occurred when sending data:", e);
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
