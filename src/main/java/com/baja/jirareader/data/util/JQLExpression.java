package com.baja.jirareader.data.util;

/**
 * JQL INFO:
 * https://confluence.atlassian.com/jira/advanced-searching-179442050.html#AdvancedSearching-CHANGED
 * 
 * TODO: Seems kinda ugly like that, there is surely a better approach for this
 * 
 * */

public class JQLExpression {

	private final String jql;
	
	public JQLExpression(){
		this.jql = "";
	}
	
	public JQLExpression(String jql){
		this.jql = jql;
	}
	
	/*			OPERATORS		*/
	
	public JQLExpression and(){
		return new JQLExpression(jql + " AND ");
	}
	
	public JQLExpression and(JQLExpression expr){
		return new JQLExpression(jql + " AND (" + expr.build() + ")");
	}
	
	public JQLExpression or(){
		return new JQLExpression(jql + " OR ");
	}
	
	public JQLExpression or(JQLExpression expr){
		return new JQLExpression(jql + " OR (" + expr.build() + ")");
	}
	
	public JQLExpression not(){
		return new JQLExpression(jql + " NOT ");
	}
	
	public JQLExpression not(JQLExpression expr){
		return new JQLExpression(jql + " NOT(" + expr.build() + ")");
	}
	
	/*				PROJECT		*/
	
	public JQLExpression project(String projectName){
		return project(projectName, JQLOperator.EQUALS);
	}
	
	public JQLExpression project(String projectName, JQLOperator op){
		return new JQLExpression(jql + "project " + op.get() + projectName);
	}
	
	public JQLExpression projects(String... projectNames){
		return projects(JQLOperator.IN, projectNames);
	}
	
	public JQLExpression projects(JQLOperator op, String... projectNames){
		String newJql = jql + "project " + op.get() + "(";
		String projects = "";
		for ( String project : projectNames){
			projects += project + ",";
		}
		newJql += projects.substring(0, projects.length() -2) + ")";
		return new JQLExpression(newJql);
	}
	
	/*				ASSIGNEE				*/
	
	public JQLExpression assignee(String name) {
		return assignee(JQLOperator.EQUALS, name);
	}
	
	public JQLExpression assignee(JQLOperator op, String name) {
		return new JQLExpression(jql + "assignee " + op.get() + name);
	}
	
	public JQLExpression assignees(String... assigneeNames){
		return assignees(JQLOperator.IN, assigneeNames);
	}
	
	public JQLExpression assignees(JQLOperator op, String... assigneeNames){
		String newJql = jql + "assignee " + op.get() +"(";
		String assignees = "";
		for ( String assignee : assigneeNames){
			assignees += assignee + ",";
		}
		newJql += assignees.substring(0, assignees.length() -2) + ")";
		return new JQLExpression(newJql);
	}
	
	/*				FINAL FUNCTIONS			*/
	
	public JQLExpression orderBy(Boolean sortAscending, String... fields){
		String newJql = jql + "order by";
		
		String allFields = "";
		
		for ( String field: fields){
			allFields += field + ",";
		}
		newJql += allFields.substring(0, allFields.length() - 2);
		
		if ( sortAscending != null){
			newJql += (sortAscending ? " asc" : " desc");
		}
		
		return new JQLExpression(newJql);
	}
	
	
	/*			GENERAL			*/
	
	public String build(){
		return this.jql;
	}
	
	public boolean isEmpty(){
		return this.jql.isEmpty();
	}
	
	@Override
	public String toString(){
		return this.jql;
	}
	
}
