package com.baja.jirareader.connectors.timesheet;

import java.util.Map;
import java.util.Optional;

import org.apache.http.client.methods.HttpGet;
import org.json.simple.JSONObject;

import com.baja.jirareader.JiraClient;
import com.baja.jirareader.connectors.Connector;

/**
 * 
 * @author Samuel Bäumlin
 *
 * @since 18 Jan 2016
 * 
 * 
 * REST API Connector as defined by: http://www.jiratimesheet.com/wiki/RESTful_endpoint.html
 * 
 * 
 */

public class TimesheetConnector extends Connector {

	public TimesheetConnector(JiraClient client) {
		super(client);
	}

	@Override
	public boolean isStatusOK(int status) {
		return 200 == status;
	}

	@Override
	public String getAPIPath() {
		return "/rest/timesheet-gadget/1.0/raw-timesheet.json";
	}
	
	private Optional<JiraWorklog> get( Map<String, String> params){
		Optional<JSONObject> resp = super.get("",params);
		return Optional.ofNullable(resp.isPresent() ? new JiraWorklog("123", resp.get()): null); 
	}
	
	public Optional<JiraWorklog> get(){
		return get(null);
	}

}
