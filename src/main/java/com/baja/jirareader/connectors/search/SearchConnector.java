package com.baja.jirareader.connectors.search;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.json.simple.JSONObject;

import com.baja.jirareader.JiraClient;
import com.baja.jirareader.connectors.Connector;
import com.baja.jirareader.data.util.JQLExpression;

public class SearchConnector extends Connector {

	public SearchConnector(JiraClient client) {
		super(client);
	}

	@Override
	public boolean isStatusOK(int status) {
		return status == 200;
	}

	@Override
	public String getAPIPath() {
		return "/rest/api/2/search";
	}
	
	
	public Optional<JSONObject> getJSON(){
		Map<String, String> params = new HashMap<String, String>();
		JQLExpression ex = new JQLExpression().project("ZPOL");
		System.out.println("EX:" + ex);
		params.put("jql", ex.build());
		params.put("start", "0");
		params.put("maxResults", "2");
		params.put("expand", "schema");
		
		return super.get("", params);
	}

}
