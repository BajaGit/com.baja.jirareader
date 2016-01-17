package com.baja.jirareader.auth;

import com.baja.jirareader.JiraClientImpl;
import com.baja.jirareader.JiraRequestSupplier;
import com.baja.jirareader.data.JiraUser;

public interface JiraAuthStrategy extends JiraRequestSupplier{
	
	boolean authenticate(JiraUser user, JiraClientImpl client);
	
	boolean isAuthenticated(JiraUser user);
}
