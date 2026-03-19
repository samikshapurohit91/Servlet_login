package com.sept.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	
	Connection connection = null;
	@Override
	public void init() {
		System.out.println("initializing login servlet");
		connection = DBConnection.getOrCreateConnection();
		
}
	
	@Override
	public void doPost(HttpServletRequest request , HttpServletResponse response) {
		
		if(connection!=null ) {
			
			String UserName = request.getParameter("UserName");
			String Password = request.getParameter("Password");
			
			//2 cases
			//1 - combination matched user successfully login
			
			try {
				PreparedStatement pst = connection.prepareStatement("Select * from users1 where UserName = ? and Password = ?");
				pst.setString(1, UserName);
				pst.setString(2, Password);
				
				System.out.println(pst.toString());
				System.out.println("Query"+pst.execute());
				
				ResultSet set = pst.executeQuery();
				while(set.next()) {
					
					System.out.println("User login successfully");
					response.sendRedirect("home.html");
				}
				
				PrintWriter printWriter = response.getWriter();
				printWriter.write("""
						
						<html>
                        <head>
                        <meta charset="UTF-8">
                        <title>Insert title here</title>
                        </head>
                        
                        <body>
                        
                              <h1> Password did not match redirect to login page</h1>
                        
                        <script>
                             
                             setTimeout(  ()=> {
                             console.log("In timeout function");
						           window.location.href="login.html";
						     },1000)
                        
						                        
                        </script>

                           

                       </body>
                       </html>
						
						
						
						""");
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//2- combination mismatched 
		}
		
		
			
		
	}

}
