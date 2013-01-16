package com.sensetecnic.client.oath;

import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.security.oauth2.client.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.http.OAuth2ErrorHandler;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.client.RestTemplate;

public class NoContextOAuth2RestTemplate extends RestTemplate {

	private final OAuth2ProtectedResourceDetails resource;
	private final OAuth2AccessToken token;

	public NoContextOAuth2RestTemplate(OAuth2ProtectedResourceDetails resource, OAuth2AccessToken token) {
		this(new SimpleClientHttpRequestFactory(), resource, token);
	}

	public NoContextOAuth2RestTemplate(ClientHttpRequestFactory requestFactory,
			OAuth2ProtectedResourceDetails resource, OAuth2AccessToken token) {
		super();
		if (resource == null) {
			throw new IllegalArgumentException(
					"An OAuth2 resource must be supplied.");
		}
		if (token == null) {
			throw new IllegalArgumentException (
			"An OAuth2 token must be supplied.");
		}

		this.resource = resource;
		this.token = token;
		setRequestFactory(requestFactory);
		setErrorHandler(new OAuth2ErrorHandler());
	}

	@Override
	public void setRequestFactory(ClientHttpRequestFactory requestFactory) {
		if (!(requestFactory instanceof NoContextOAuth2ClientHttpRequestFactory)) {
			requestFactory = new NoContextOAuth2ClientHttpRequestFactory(requestFactory,
					getResource(), token.getValue());
		}
		super.setRequestFactory(requestFactory);
	}

	public OAuth2ProtectedResourceDetails getResource() {
		return resource;
	}

}
