package edu.dev.util;

public class SQLStatement {
	/********** User Statements **********/
	public static final String USER_SELECT_STATEMENT_BY_USERNAME = "SELECT username, name, password FROM User WHERE username=?";
	public static final String USER_CREATE_STATEMENT = "INSERT INTO User (username, name, password) VALUES (?, ?, ?)";
	public static final String USER_UPDATE_STATEMENT_BY_USERNAME = "";
	public static final String USER_DELETE_STATEMENT_BY_USERNAME = "";
	
		
	/********** Join Statements **********/
	public static final String RESOURCE_JOIN_RESOURCE_STATUS_ON_ID = "";
}