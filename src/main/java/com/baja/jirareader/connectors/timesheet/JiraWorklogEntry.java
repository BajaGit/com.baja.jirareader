package com.baja.jirareader.connectors.timesheet;

import org.json.simple.JSONObject;

import com.baja.jirareader.data.util.QueryableJSON;

public class JiraWorklogEntry implements QueryableJSON {

	private final String id;
	private String summary;
	
	private final JSONObject json;
	
	public JiraWorklogEntry(String id, String summary, JSONObject json){
		this.id = id;
		this.summary = summary;
		this.json = json;
	}
	
	@Override
	public JSONObject getJSON() {
		return this.json;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getId() {
		return id;
	}

}
