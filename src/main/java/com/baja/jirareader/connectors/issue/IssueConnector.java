package com.baja.jirareader.connectors.issue;

import java.util.Optional;

import org.json.simple.JSONObject;

import com.baja.jirareader.JiraClient;
import com.baja.jirareader.connectors.Connector;

public class IssueConnector extends Connector {

	public IssueConnector(JiraClient client) {
		super(client);
	}

	@Override
	public boolean isStatusOK(int status) {
		return status == 200 || status == 201 || status == 204;
	}

	@Override
	public String getAPIPath() {
		return "/rest/api/2/issue";
	}


	public JiraIssue create(){
		return create(false);
	}
	
	public JiraIssue create(boolean bulk){
		return null;
	}
	
	public JiraIssue get(String issueId){
		return get(issueId, null, false);
	}
	
	public JiraIssue get(String issueId, String fieldFlags){
		return get(issueId, fieldFlags, false);
	}
	
	public JiraIssue get(String issueId, String fieldFlags, boolean expand){
		
		
		
		
		return null;
	}
	
	public Optional<JSONObject> getJSON(String issueId){
		return super.get(issueId, null);
		
	}
	
	
	
}
