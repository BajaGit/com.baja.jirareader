package com.baja.jirareader;

import java.util.Optional;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import com.baja.jirareader.data.JiraUser;

public interface JiraRequestSupplier {
	Optional<HttpPost> buildPOST(String targetURL);
	
	Optional<HttpGet> buildGET(String targetURL);
	
	Optional<HttpPost> buildPOST(String targetURL, JiraUser user);
	
	Optional<HttpGet> buildGET(String targetURL, JiraUser user);
}
