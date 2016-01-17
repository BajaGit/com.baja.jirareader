package com.baja.jirareader.data.util;

public enum JQLOperator {
	EQUALS("="), NOT_EQUALS("!="), GREATER_THAN(">"), GREATER_EQUAL(">="),
	LESS_THAN("<"), LESS_EQUALS("<="), IN(" IN "), NOT_IN(" NOT IN "),
	CONTAINS("~"), NOT_CONTAINS("!~"), IS(" IS "), IS_NOT(" IS NOT "),
	WAS(" WAS "), WAS_NOT(" WAS NOT "), WAS_IN(" WAS IN "), WAS_NOT_IN(" WAS NOT IN "),
	CHANGED(" CHANGED ");
	
	private final String op;
	
	JQLOperator(String op){
		this.op = op;
	}
	
	public String get(){
		return this.op;
	}
}
