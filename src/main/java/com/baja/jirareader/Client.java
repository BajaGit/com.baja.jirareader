package com.baja.jirareader;

import org.apache.http.impl.client.CloseableHttpClient;

import com.baja.jirareader.data.JiraUser;

public interface Client {
	boolean authenticateUser(JiraUser user);
	CloseableHttpClient getHttpClient();
	String getBaseURL();
}
