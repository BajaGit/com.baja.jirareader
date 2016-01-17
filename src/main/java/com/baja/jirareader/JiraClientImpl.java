package com.baja.jirareader;

import java.io.IOException;
import java.util.Optional;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.baja.jirareader.auth.JiraAuthStrategy;
import com.baja.jirareader.auth.JiraCookieAuth;
import com.baja.jirareader.data.JiraUser;

public class JiraClientImpl implements JiraClient{
	
	private CloseableHttpClient httpClient;
	private final String baseURL;
	private JiraAuthStrategy authStrat;
	
	public JiraClientImpl(String baseURL){
		this.baseURL = baseURL;
		this.httpClient = HttpClients.createDefault();
		this.authStrat = new JiraCookieAuth();
	}
	
	public JiraClientImpl(String baseURL, JiraAuthStrategy authStrat){
		this.authStrat = authStrat;
		this.baseURL = baseURL;
		this.httpClient = HttpClients.createDefault();
	}
	
	public void destroyHttp(){
		try{
			this.httpClient.close();
		} catch ( IOException ioe){
			ioe.printStackTrace();
		}
	}

	public boolean authenticateUser(JiraUser user){
		return this.authStrat.authenticate(user, this);
	}
	
	@Override
	public Optional<HttpPost> buildPOST(String targetURL) {
		return	this.authStrat.buildPOST(targetURL);
	}

	@Override
	public Optional<HttpGet> buildGET(String targetURL) {
		return this.authStrat.buildGET(targetURL);
	}

	@Override
	public Optional<HttpPost> buildPOST(String targetURL, JiraUser user) {
		return this.authStrat.buildPOST(targetURL, user);
	}

	@Override
	public Optional<HttpGet> buildGET(String targetURL, JiraUser user) {
		return this.authStrat.buildGET(targetURL, user);
	}
	
	

	public CloseableHttpClient getHttpClient() {
		return httpClient;
	}

	public void setHttpClient(CloseableHttpClient httpClient) {
		this.httpClient = httpClient;
	}

	public JiraAuthStrategy getAuthStrat() {
		return authStrat;
	}

	public void setAuthStrat(JiraAuthStrategy authStrat) {
		this.authStrat = authStrat;
	}

	public String getBaseURL() {
		return baseURL;
	}

	
}
