package com.baja.jirareader.connectors;

import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.baja.jirareader.JiraClient;

public abstract class Connector {
	
	private final JiraClient client;
	
	public Connector(JiraClient client){
		this.client = client;
	}
	
	public JiraClient getClient(){
		return this.client;
	}
	
	
	public String buildURLParams(Map<String, String> params){
		return this.buildURLParams(params, "utf-8");
	}
	
	public String buildURLParams(Map<String, String> params, String charset){
			List<NameValuePair> paramList = new LinkedList<NameValuePair>();
			params.forEach((k, v) -> paramList.add(new BasicNameValuePair(k,v)));
			return URLEncodedUtils.format(paramList, charset);
	}
	
	public Optional<JSONObject> get(String urlPart, Map<String, String> params){
		
		String requestUrl = this.client.getBaseURL() + getAPIPath() + (urlPart == null || urlPart.isEmpty() ? "": "/" + urlPart);
		
		if ( params != null){
			requestUrl+= "?";
			requestUrl += buildURLParams(params);
		}
		
		
		Optional<HttpGet> req = client.buildGET(requestUrl);
		System.out.println("requestURL: " + requestUrl);
		JSONObject data = null;
		if ( req.isPresent()){
			try{
				HttpResponse resp = this.client.getHttpClient().execute(req.get());
				
				if (isStatusOK(resp.getStatusLine().getStatusCode())){
					if ( resp.getEntity().getContentType().toString().contains("application/json")){
						data = (JSONObject) new JSONParser().parse(new InputStreamReader(resp.getEntity().getContent()));
					} else {
						//TODO: handle wrong filetype?... it will be json
						System.out.println("RESPONSE CONTENT TYPE :" + resp.getEntity().getContentType());
					}
				} else {
					// TODO: Handle different status!
					System.out.println("HTTP GET STATUS:" + resp.getStatusLine().getStatusCode());
				}
			} catch ( Exception e){
				e.printStackTrace();
			}
		}
		return Optional.ofNullable(data);
	}
	
	public Optional<JSONObject> post(String urlPart, JSONObject data){
		
		
		return null;
	}
	
	public abstract boolean isStatusOK(int status);
	public abstract String getAPIPath();
}
