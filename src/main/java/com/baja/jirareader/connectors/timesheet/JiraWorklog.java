package com.baja.jirareader.connectors.timesheet;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.baja.jirareader.data.util.QueryableJSON;

public class JiraWorklog implements QueryableJSON {

	private final JSONObject json;
	private final String id;
	private List<JiraWorklogEntry> entries = null;
	
	
	public JiraWorklog(String id, JSONObject json){
		this.json = json;
		this.id = id;
	}
	
	@Override
	public JSONObject getJSON() {
		return this.json;
	}
	
	public String getId(){
		return this.id;
	}
	
	/**
	 * 
	 * @return
	 * @throws NoSuchElementException indicates that the jsonResponse does not contain correct data formatting
	 */
	
	public Optional<List<JiraWorklogEntry>> parse() throws NoSuchElementException {
		if (isJSONpresent()){
			try{
				JSONArray arr = (JSONArray) query("worklog").orElseGet(JSONArray::new);
				if ( !arr.isEmpty()){
					this.entries = new ArrayList<JiraWorklogEntry>();
					for ( Object entry: arr){
						QueryableJSON data = () -> (JSONObject) entry;
						JSONObject dataEntries = (JSONObject) data.queryArray("entries").get().get(0);
						entries.add(new JiraWorklogEntry(data.queryString("key").get(), data.queryString("key").get(), dataEntries));
					}
					
					
				}
			} catch( NoSuchElementException nsee){
				this.entries = null;
				throw nsee;
			}
		}
		return Optional.ofNullable(this.entries);
	};

}
