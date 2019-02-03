package com.buga.boxes.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Swapneel
 * A class to connect with mysql
 */
public class PostgresConnector {
	@SuppressWarnings("unused")
	private final String username, password, database;
	@SuppressWarnings("unused")
	private final String uri;
	private final Connection connection;
	
	public PostgresConnector(String username, String password, String database, String uri) throws ClassNotFoundException, SQLException {
		super();
		this.username = username;
		this.password = password;
		this.database = database;
		this.uri = uri;
		
		Class.forName("org.postgresql.Driver"); 
		connection=DriverManager.getConnection(  
		uri+"/"+database,username,password); 
	}

	public Connection getConnection() {
		return connection;
	}
}
