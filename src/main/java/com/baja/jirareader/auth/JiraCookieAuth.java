package com.baja.jirareader.auth;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.baja.jirareader.JiraClientImpl;
import com.baja.jirareader.data.JiraUser;

public class JiraCookieAuth implements JiraAuthStrategy {
	
	private Map<JiraUser, Boolean> authMap = new HashMap<JiraUser, Boolean>();
	
	private JiraUser currentUser = null;

	public boolean authenticate(JiraUser user, JiraClientImpl client) {
		if ( this.isAuthenticated(user)) return true;
		
		boolean ret = false;
		if (!user.getName().isEmpty() && !user.getName().isEmpty()){
			HttpPost authPost = new HttpPost(client.getBaseURL() + "/rest/auth/1/session");
		
			try{
				
				JSONObject json = new JSONObject();
				json.put("username", user.getName());
				json.put("password", user.getPassword());
				StringEntity data = new StringEntity(json.toJSONString());
				
				data.setContentType("application/json");
				authPost.setEntity(data);
				
				HttpResponse response = client.getHttpClient().execute(authPost);
				
				if (response.getStatusLine().getStatusCode() != 200) {
					ret = false;
				} else {
					JSONParser jparser = new JSONParser();
					JSONObject resp = (JSONObject) jparser.parse( new InputStreamReader(response.getEntity().getContent()));
					JSONObject session = (JSONObject) resp.get("session");
					if (session != null){
						Map<String, String> authData = new HashMap<String, String>();
						authData.put((String)session.get("name"), (String)session.get("value"));
						user.setAuthData(authData);
						ret = true;
						if ( currentUser == null){
							currentUser = user;
						}
					}
				}

				
			} catch( Exception e){
				e.printStackTrace();
			}
			
		}
		
		return ret;
	}

	public Optional<HttpPost> buildPOST(String targetURL) {
		return this.currentUser == null ? Optional.ofNullable(null) : this.buildPOST(targetURL, this.currentUser);
	}

	public Optional<HttpPost> buildPOST(String targetURL, JiraUser user) {
		HttpPost ret = new HttpPost(targetURL);
		user.getAuthData().forEach(ret::addHeader);
		return Optional.ofNullable(ret);
	}

	public Optional<HttpGet> buildGET(String targetURL) {
		return this.currentUser == null ? Optional.ofNullable(null) : this.buildGET(targetURL, this.currentUser);
	}

	public Optional<HttpGet> buildGET(String targetURL, JiraUser user) {
		HttpGet ret = new HttpGet(targetURL);
		user.getAuthData().forEach(ret::addHeader);
		
		return Optional.ofNullable(ret);
	}

	public boolean isAuthenticated(JiraUser user) {
		return this.authMap.getOrDefault(user, false);
	}

	public JiraUser getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(JiraUser currentUser) {
		this.currentUser = currentUser;
	}


	
}
