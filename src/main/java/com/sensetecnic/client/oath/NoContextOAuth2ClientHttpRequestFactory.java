/**
 * (c) 2010 Sense Tecnic Systems Inc.   All rights reserved.
 */
package com.sensetecnic.client.oath;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.security.oauth2.client.OAuth2ProtectedResourceDetails;

/**
 * RequestFactory that doesn't need a context
 * @author Mike Blackstock (mike)
 * 
 */
public class NoContextOAuth2ClientHttpRequestFactory implements
		ClientHttpRequestFactory {

	private String accessToken;

	private final ClientHttpRequestFactory delegate;
	private final OAuth2ProtectedResourceDetails resource;

	public NoContextOAuth2ClientHttpRequestFactory(ClientHttpRequestFactory delegate,
			OAuth2ProtectedResourceDetails resource, String accessToken) {
		this.delegate = delegate;
		this.resource = resource;
		this.accessToken = accessToken;

		if (delegate == null) {
			throw new IllegalArgumentException(
					"A delegate must be supplied for an NoContextOAuth2ClientHttpRequestFactory.");
		}
		if (resource == null) {
			throw new IllegalArgumentException(
					"A resource must be supplied for an NoContextOAuth2ClientHttpRequestFactory.");
		}
		
		if (accessToken == null) {
			throw new IllegalArgumentException(
					"An acccessToken must be supplied for an NoContextOAuth2ClientHttpRequestFactory.");
		}
	}

	public ClientHttpRequest createRequest(URI uri, HttpMethod httpMethod)
			throws IOException {

		OAuth2ProtectedResourceDetails.BearerTokenMethod bearerTokenMethod = resource
				.getBearerTokenMethod();
		if (OAuth2ProtectedResourceDetails.BearerTokenMethod.query
				.equals(bearerTokenMethod)) {
			uri = appendQueryParameter(uri, accessToken);
		}

		ClientHttpRequest req = delegate.createRequest(uri, httpMethod);
		if (OAuth2ProtectedResourceDetails.BearerTokenMethod.header
				.equals(bearerTokenMethod)) {
			req.getHeaders().add("Authorization",
					String.format("OAuth %s", accessToken));
		}
		return req;
	}

	protected URI appendQueryParameter(URI uri, String accessToken) {
		try {
			String query = uri.getQuery();
			if (query == null) {
				query = ((resource.getBearerTokenName() == null) ? "oauth_token"
						: resource.getBearerTokenName())
						+ "="
						+ URLEncoder.encode(accessToken, "UTF-8");
			}
			uri = new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(),
					uri.getPort(), uri.getPath(), query, uri.getFragment());
			return uri;
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
