package com.baja.jirareader;

import java.util.Optional;

import org.json.simple.JSONObject;

import com.baja.jirareader.connectors.issue.IssueConnector;
import com.baja.jirareader.connectors.search.SearchConnector;
import com.baja.jirareader.data.JiraUser;
import com.baja.jirareader.data.util.JQLExpression;
import com.baja.jirareader.data.util.JQLOperator;

public class Example {

	public static void main(String[] args) {
		
		Example main = new Example();
		
		//main.testQueryableJSON();
		//main.jqlTester();
		
		main.testSearch();
	}
	
	public void testSearch(){
		JiraUser user = new JiraUser("YOUR_USER_NAME", "YOUR_PASSWORD" );
		JiraClientImpl client = new JiraClientImpl("YOUR_URL_TO_JIRA");
		client.authenticateUser(user);
		
		user.getAuthData().forEach((k, v) -> System.out.println(k + ":" + v)); 
		
		SearchConnector sConn = new SearchConnector(client);
		
		Optional<JSONObject> ret = sConn.getJSON();
		
		if ( ret.isPresent()){
			System.out.println(ret.get().toJSONString());
		}
	}
	
	public void jqlTester(){
		JQLExpression ex = new JQLExpression().assignee("ASSIGNEENAME").and().project("PROJECTKEY");
		System.out.println(ex.build());
		
		JQLExpression ex2 = new JQLExpression();
		
		System.out.println(ex2.assignee(JQLOperator.IS_NOT, "ANAME").and(ex));
		
	}
	
	
	public void testMain(){
		JiraUser user = new JiraUser("YOUR_USER_NAME", "YOUR_PASSWORD" );
		JiraClientImpl client = new JiraClientImpl("YOUR_URL_TO_JIRA");
		client.authenticateUser(user);
		
		user.getAuthData().forEach((k, v) -> System.out.println(k + ":" + v)); 
		
		IssueConnector iConn = new IssueConnector(client);
		
		Optional<JSONObject> ret = iConn.getJSON("ISSUEKEY");
		
		if ( ret.isPresent()){
			System.out.println(ret.get().toJSONString());
		}
	}

}
