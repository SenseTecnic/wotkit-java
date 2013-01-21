/**
 * (c) 2010 Sense Tecnic Systems Inc.   All rights reserved.
 */
package com.sensetecnic.client.oath;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.client.OAuth2SecurityContext;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

/**
 * Really simple security context so we can use the OAuth2RestTemplate from a client
 * 
 * @author Mike Blackstock (mike)
 *
 */
public class OAuth2SimpleClientSecurityContext implements OAuth2SecurityContext {

	private final Map<String, OAuth2AccessToken> accessTokens = new HashMap<String, OAuth2AccessToken>(1);
	
	public OAuth2SimpleClientSecurityContext(String resource, OAuth2AccessToken token) {
		accessTokens.put(resource, token);
	}
	/* (non-Javadoc)
	 * @see org.springframework.security.oauth2.consumer.OAuth2SecurityContext#getAccessTokens()
	 */
	@Override
	public Map<String, OAuth2AccessToken> getAccessTokens() {
		return accessTokens;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.oauth2.consumer.OAuth2SecurityContext#getPreservedState()
	 */
	@Override
	public Object getPreservedState() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.oauth2.consumer.OAuth2SecurityContext#getUserAuthorizationRedirectUri()
	 */
	@Override
	public String getUserAuthorizationRedirectUri() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.oauth2.consumer.OAuth2SecurityContext#getVerificationCode()
	 */
	@Override
	public String getAuthorizationCode() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.oauth2.consumer.OAuth2SecurityContext#getDetails()
	 */
	@Override
	public Object getDetails() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.oauth2.consumer.OAuth2SecurityContext#getErrorParameters()
	 */
	@Override
	public Map<String, String> getErrorParameters() {
		return null;
	}

}
