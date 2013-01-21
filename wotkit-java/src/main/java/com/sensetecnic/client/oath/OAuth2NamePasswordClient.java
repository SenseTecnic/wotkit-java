/**
 * (c) 2010 Sense Tecnic Systems Inc.   All rights reserved.
 */
package com.sensetecnic.client.oath;

import java.util.Date;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.oauth2.client.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Client to get an authentication token from the user's name and password.
 * 
 * @author Mike Blackstock (mike)
 *
 */
public class OAuth2NamePasswordClient {

	private final static Log logger = LogFactory.getLog(OAuth2NamePasswordClient.class);
	private RestTemplate restTemplate = new RestTemplate();
	
	private OAuth2ProtectedResourceDetails resource;
	
	public void setResource(OAuth2ProtectedResourceDetails resource) {
		this.resource = resource;
	}
	
	/**
	 * Get the authorization token given the user name and password
	 * 
	 * @param tokenUrl
	 * @param userName
	 * @param password
	 * @return
	 */
	public OAuth2AccessToken getAuthToken(String userName, String password) {
		
		String tokenUrl = resource.getAccessTokenUri();
		
		MultiValueMap<String, String> formData = new LinkedMultiValueMap<String, String>();
		
		// set up the form for requesting the token (http://tools.ietf.org/html/draft-ietf-oauth-v2-11#section-5.1.2)
		formData.set("grant_type", "password");
		formData.set("client_id", resource.getClientId());	// get from resource
		if (resource.getClientSecret() != null)
			formData.set("client_secret", resource.getClientSecret());
		formData.set("username", userName);
		formData.set("password", password);		

		@SuppressWarnings("rawtypes")
		HashMap response = restTemplate.postForObject(tokenUrl, formData, HashMap.class );
		@SuppressWarnings("unchecked")
		OAuth2AccessToken token = createAccessToken(response);
		
		logger.debug(response);
		
		return token;
		
	}
	
 protected OAuth2AccessToken createAccessToken(HashMap<String, Object> response) {
	    OAuth2AccessToken token = new OAuth2AccessToken();
	    String tokenValue = (String)response.get("access_token");
	    token.setValue(tokenValue);
	    token.setExpiration(new Date(System.currentTimeMillis() + ((Integer)response.get("expires_in") * 1000L)));
	    
	    OAuth2RefreshToken refreshToken = new OAuth2RefreshToken();
	    refreshToken.setValue((String)response.get("refresh_token"));
	    token.setRefreshToken(refreshToken);
	    return token;
	  }
	
}
