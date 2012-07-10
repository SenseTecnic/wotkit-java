package com.sensetecnic.client;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class OAuth2NamePasswordClientTest extends TestCase {

	private final static Log logger = LogFactory.getLog(OAuth2NamePasswordClientTest.class);

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetAuthToken() throws SenseTecnicException {
		
//		// now when we create an OA
//		BaseOAuth2ProtectedResourceDetails resource = new BaseOAuth2ProtectedResourceDetails();
//		resource.setId("sensetecnic");
//		resource.setAccessTokenUri("http://localhost:8080/SenseTecnic/oauth/authorize");
//		resource.setClientId("other-test-app");
//
//		OAuth2NamePasswordClient client = new OAuth2NamePasswordClient();
//		client.setResource(resource);
//		OAuth2AccessToken token = client.getAuthToken("mike", "aMUSEment2");
//
//		logger.debug("Received token (yes!):"+token);
//		
//		// now we can set up an OAuthContext so the OAuth2RestTemplate will work
//		// it associates the security context with the current thread
//		OAuth2SecurityContext context = new OAuth2SimpleClientSecurityContext("sensetecnic", token);
//		// should probably check if we already have one, but since we know we don't
//		OAuth2SecurityContextHolder.setContext(context);
//		
//		// since we have an access token in the security context, the OAuth2RestTemplate just works
//		OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resource);
//		
//		SenseTecnicTemplate stsOperations = new SenseTecnicTemplate();
//		stsOperations.setStsBaseUrl("http://localhost:8080/SenseTecnic/api");
//		stsOperations.setStsRestTemplate(restTemplate);
//		
//		Sensor sensor = null;
//
//		sensor = stsOperations.getSensor("random2");
//		assertEquals("Random Test 2",sensor.getLongName());
//
//		NoContextOAuth2RestTemplate noContextRestTemplate = new NoContextOAuth2RestTemplate(resource, token);
//		stsOperations.setStsRestTemplate(noContextRestTemplate);
//
//		sensor = stsOperations.getSensor("random");
//		assertEquals("Random",sensor.getLongName());

		
	}

}