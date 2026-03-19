package com.sept.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	static Connection connection=null;
	
	public static Connection getOrCreateConnection() {
		
		if(connection!=null) {
			System.out.println("Connection already exists - returning existing connection");
			return connection;
			
		}else {
			
			System.out.println("Creating new connection");
			
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				try {
					connection = DriverManager.getConnection(
							//DB
							"jdbc:mysql://localhost:3306/sept_login",
							//username
							"your_username",
							//password
							"your_password"
							);
					
					return connection;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
		
						
						
						
						
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return connection;
		
	}

}
