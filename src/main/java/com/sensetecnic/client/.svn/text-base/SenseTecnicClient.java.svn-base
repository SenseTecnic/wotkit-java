/**
 * (c) 2010 Sense Tecnic Systems, Inc.
 */
package com.sensetecnic.client;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Object for communications with the SenseTecnic system
 * 
 * @author mike
 * 
 */
public class SenseTecnicClient {

	private final static Log logger = LogFactory
			.getLog(SenseTecnicClient.class);

	private String brokerBaseUrl = "http://localhost:8800/osgibroker/event";
	
	// STS_BASE_URL="http://dev.sensetecnic.com/SenseTecnic/api";
	private String stsBaseUrl = "http://localhost:8888/sensetecnic-web/api";

	public void setBrokerBaseUrl(String brokerBaseUrl) {
		this.brokerBaseUrl = brokerBaseUrl;
	}

	public void setStsBaseUrl(String stsBaseUrl) {
		this.stsBaseUrl = stsBaseUrl;
	}

	final static private ObjectMapper objectMapper = new ObjectMapper();

	public void sendData(String sensor, String user, String password,
			Map<String, String> attributes) throws SenseTecnicException {
		sendData(sensor, user, password, attributes, null, false);
	}

	public void sendData(String sensor, String user, String password,
			Map<String, String> attributes, String topic, boolean useBroker)
			throws SenseTecnicException {
		if (topic == null)
			topic = "sensor." + sensor;

		if (useBroker)
			sendDataBroker(topic, user, attributes);
		else
			sendDataSTS(sensor, user, password, attributes);
	}

	public void sendDataBroker(String topic, String clientId,
			Map<String, String> attributes) throws SenseTecnicException {
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(brokerBaseUrl + "?clientID="
				+ clientId + "&topic=" + topic);

		NameValuePair[] data = new NameValuePair[attributes.size()];
		int i = 0;
		for (String key : attributes.keySet()) {
			data[i] = new NameValuePair(key, attributes.get(key));
			i++;
		}

		method.setRequestBody(data);
		int statusCode;
		try {
			statusCode = client.executeMethod(method);
			if (!isSuccess(statusCode)) {
				logger.error("method failed: " + method.getStatusLine());
				statusCode = method.getStatusCode();
				String statusMessage = method.getStatusText();
				throw new SenseTecnicException(statusMessage, statusCode);
			}
		} catch (HttpException e) {
			logger.error("Fatal protocol violation: " + e.getMessage());
			throw new SenseTecnicException("HTTPException", e);
		} catch (IOException e) {
			logger.error("Fatal transport error: " + e.getMessage());
			throw new SenseTecnicException("IOException", e);
		} finally {
			// Release the connection.
			method.releaseConnection();
		}
	}

	public void sendDataSTS(String sensor, String user, String password,
			Map<String, String> attributes) throws SenseTecnicException {
		HttpClient client = createClientWithCredentials(user, password);
		PostMethod method = new PostMethod(stsBaseUrl + "/sensors/" + sensor
				+ "/data");
		// do whatever is needed to authenticate
		method.setDoAuthentication(true);

		NameValuePair[] data = new NameValuePair[attributes.size()];
		int i = 0;
		for (String key : attributes.keySet()) {
			data[i] = new NameValuePair(key, attributes.get(key));
			i++;
		}

		method.setRequestBody(data);
		int statusCode;
		try {
			statusCode = client.executeMethod(method);
			if (!isSuccess(statusCode)) {
				logger.error("method failed: " + method.getStatusLine());
				statusCode = method.getStatusCode();
				String statusMessage = method.getStatusText();
				throw new SenseTecnicException(statusMessage, statusCode);
			}
		} catch (HttpException e) {
			logger.error("Fatal protocol violation: " + e.getMessage());
			throw new SenseTecnicException("HTTPException", e);
		} catch (IOException e) {
			logger.error("Fatal transport error: " + e.getMessage());
			throw new SenseTecnicException("IOException", e);
		} finally {
			// Release the connection.
			method.releaseConnection();
		}

	}
	
	public String getSensorDataCsv(String sensor, Integer beforeE, String user, String password) throws SenseTecnicException {
		HttpClient client = createClientWithCredentials(user, password);
		
		String requestUrl = stsBaseUrl + "/sensors/" + sensor + "/data?tqx=out:csv&wait=5000";
		if (beforeE != null)
			requestUrl += "&beforeE="+beforeE;
		
		GetMethod method = new GetMethod(requestUrl);
		// do whatever is needed to authenticate
		method.setDoAuthentication(true);
		String csvData = null;
		try {
			int statusCode;

			statusCode = client.executeMethod(method);
			if (!isSuccess(statusCode)) {
				logger.error("method failed: " + method.getStatusLine());
				statusCode = method.getStatusCode();
				String statusMessage = method.getStatusText();
				throw new SenseTecnicException(statusMessage, statusCode);
			}
			csvData = responseToString(method.getResponseBodyAsStream());
			
		} catch (HttpException e) {
			logger.error("Fatal protocol violation: " + e.getMessage());
			throw new SenseTecnicException("HTTPException", e);
		} catch (IOException e) {
			logger.error("Fatal transport error: " + e.getMessage());
			throw new SenseTecnicException("IOException", e);
		} finally {
			// Release the connection.
			method.releaseConnection();
		}
		return csvData;
	}
	
	/**
	 * Read in the response as a string
	 * 
	 * @param stream
	 * @return
	 * @throws IOException
	 */
	private String responseToString(InputStream stream) throws IOException {
		BufferedInputStream bi = new BufferedInputStream(stream);
 
		StringBuilder sb = new StringBuilder();
 
		byte[] buffer = new byte[1024];
		int bytesRead = 0;
		while ((bytesRead = bi.read(buffer)) != -1) {
			sb.append(new String(buffer, 0, bytesRead));
		}
		return sb.toString();
	}

	public void registerSensor(Sensor sensor, String user, String password)
			throws SenseTecnicException {
		HttpClient client = createClientWithCredentials(user, password);
		PutMethod method = new PutMethod(stsBaseUrl + "/sensors/"
				+ sensor.getName());
		// do whatever is needed to authenticate
		method.setDoAuthentication(true);
		try {
			String jsonString = objectMapper.writeValueAsString(sensor);
			RequestEntity entity = new StringRequestEntity(jsonString,
					"application/json", null);

			method.setRequestEntity(entity);
			int statusCode;

			statusCode = client.executeMethod(method);
			if (!isSuccess(statusCode)) {
				logger.error("method failed: " + method.getStatusLine());
				statusCode = method.getStatusCode();
				String statusMessage = method.getStatusText();
				throw new SenseTecnicException(statusMessage, statusCode);
			}
		} catch (HttpException e) {
			logger.error("Fatal protocol violation: " + e.getMessage());
			throw new SenseTecnicException("HTTPException", e);
		} catch (IOException e) {
			logger.error("Fatal transport error: " + e.getMessage());
			throw new SenseTecnicException("IOException", e);
		} finally {
			// Release the connection.
			method.releaseConnection();
		}
	}

	public Sensor getSensor(String sensorName, String user, String password)
			throws SenseTecnicException {
		HttpClient client = createClientWithCredentials(user, password);
		GetMethod method = new GetMethod(stsBaseUrl + "/sensors/" + sensorName);
		// do whatever is needed to authenticate
		method.setDoAuthentication(true);
		Sensor sensor = null;
		try {
			int statusCode;

			statusCode = client.executeMethod(method);
			if (!isSuccess(statusCode)) {
				logger.error("method failed: " + method.getStatusLine());
				statusCode = method.getStatusCode();
				String statusMessage = method.getStatusText();
				throw new SenseTecnicException(statusMessage, statusCode);
			}
			sensor = objectMapper.readValue(method.getResponseBodyAsStream(),
					Sensor.class);
		} catch (HttpException e) {
			logger.error("Fatal protocol violation: " + e.getMessage());
			throw new SenseTecnicException("HTTPException", e);
		} catch (IOException e) {
			logger.error("Fatal transport error: " + e.getMessage());
			throw new SenseTecnicException("IOException", e);
		} finally {
			// Release the connection.
			method.releaseConnection();
		}
		return sensor;
	}

	public void deleteSensor(String sensorName, String user, String password)
			throws SenseTecnicException {
		HttpClient client = createClientWithCredentials(user, password);
		DeleteMethod method = new DeleteMethod(stsBaseUrl + "/sensors/"
				+ sensorName);
		// do whatever is needed to authenticate
		method.setDoAuthentication(true);
		try {
			int statusCode = client.executeMethod(method);
			if (!isSuccess(statusCode)) {
				logger.error("method failed: " + method.getStatusLine());
				statusCode = method.getStatusCode();
				String statusMessage = method.getStatusText();
				throw new SenseTecnicException(statusMessage, statusCode);
			}
		} catch (HttpException e) {
			logger.error("Fatal protocol violation: " + e.getMessage());
			throw new SenseTecnicException("HTTPException", e);
		} catch (IOException e) {
			logger.error("Fatal transport error: " + e.getMessage());
			throw new SenseTecnicException("IOException", e);
		} finally {
			// Release the connection.
			method.releaseConnection();
		}

	}
	
	public String getHtml(String user, String password, String extension) throws SenseTecnicException {
		HttpClient client = createClientWithCredentials(user, password);
		
		String requestUrl = stsBaseUrl + extension;
		
		GetMethod method = new GetMethod(requestUrl);
		// do whatever is needed to authenticate
		method.setDoAuthentication(true);
		String body = null;
		try {
			int statusCode;

			statusCode = client.executeMethod(method);
			if (!isSuccess(statusCode)) {
				logger.error("method failed: " + method.getStatusLine());
				statusCode = method.getStatusCode();
				String statusMessage = method.getStatusText();
				throw new SenseTecnicException(statusMessage, statusCode);
			}
			body = method.getResponseBodyAsString();
		} catch (HttpException e) {
			logger.error("Fatal protocol violation: " + e.getMessage());
			throw new SenseTecnicException("HTTPException", e);
		} catch (IOException e) {
			logger.error("Fatal transport error: " + e.getMessage());
			throw new SenseTecnicException("IOException", e);
		} finally {
			// Release the connection.
			method.releaseConnection();
		}
		return body;
	}


	private boolean isSuccess(int statusCode) {
		return (statusCode >= 200 && statusCode < 300);
	}

	private HttpClient createClientWithCredentials(String name, String password) {

		HttpClient hclient = new HttpClient();

		// preemptive authentication avoids a round trip
		hclient.getParams().setAuthenticationPreemptive(true);

		hclient.getState().setCredentials(
				new AuthScope(null, AuthScope.ANY_PORT, null), // arbitrary host
				// and realm
				new UsernamePasswordCredentials(name, password));

		return hclient;
	}

}
