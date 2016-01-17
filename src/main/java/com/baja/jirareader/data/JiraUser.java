package com.baja.jirareader.data;

import java.util.Map;

public class JiraUser {
	
	private String name;
	private String password;
	private String selfURL;
	private Map<String, String> authData;
	
	
	public JiraUser(String name, String password){
		this.name = name;
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSelfURL() {
		return selfURL;
	}
	public void setSelfURL(String selfURL) {
		this.selfURL = selfURL;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Map<String, String> getAuthData() {
		return authData;
	}

	public void setAuthData(Map<String, String> authData) {
		this.authData = authData;
	}
}
