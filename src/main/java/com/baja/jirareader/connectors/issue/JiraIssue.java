package com.baja.jirareader.connectors.issue;

import org.json.simple.JSONObject;

import com.baja.jirareader.data.util.QueryableJSON;

public class JiraIssue implements QueryableJSON{
	
	private final String key;
	private final JSONObject json;
	private final IssueConnector conn;
	
	public JiraIssue(String key, JSONObject json, IssueConnector conn){
		this.key = key;
		this.json = json;
		this.conn = conn;
	}

	@Override
	public JSONObject getJSON() {
		return this.json;
	}
	
	public String getKey(){
		return this.key;
	}
	
	
	
	
}
