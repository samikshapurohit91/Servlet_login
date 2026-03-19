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

public class RegisterServlet extends HttpServlet {
	
	Connection connection = null;
	@Override
	public void init() {
		System.out.println("initializing register servlet");
		connection = DBConnection.getOrCreateConnection();
		
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Registering user");
		
		if(connection!=null) {
			
			try {
//				PreparedStatement pst = connection.prepareStatement("INSERT INTO users1 Values (?,?,?,?)");
				
				String UserName = request.getParameter("UserName");
				String Password= request.getParameter("Password");
				String Email = request.getParameter("Email");
				String PhoneNumber  = request.getParameter("PhoneNumber");
				
				//to check if user is already present in table with UserName
				PreparedStatement pstUser = connection.prepareStatement("Select * from users1 where UserName = ?");
				pstUser.setString(1, UserName);
						
				ResultSet rs = pstUser.executeQuery();
				while(rs.next()) {
					
					System.out.println(" User with "+ UserName +" already present ");
					try {
						response.setContentType("text/html");
						PrintWriter printWriter = response.getWriter();
						
//						printWriter.write("<h1> User is already present </h1>");		
//						response.sendRedirect("login.html");
						
						printWriter.write("""
								
								<html>
                                <head>
                                <meta charset="UTF-8">
                                <title>Insert title here</title>
                                </head>
                                
                                <body>
                                
                                      <h1> User is already present redirecting to login page</h1>
                                
                                <script>
                                     
                                     setTimeout(  ()=> {
                                     console.log("In timeout function");
								           window.location.href="login.html";
								     },1000)
                                
								                        
                                </script>

	                               

                               </body>
                               </html>
								
								
								
								""");
						
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return;
				}
						
						
				PreparedStatement pst = connection.prepareStatement("INSERT INTO users1 Values (?,?,?,?)");
				pst.setString(1, UserName);
				pst.setString(2, Password);
				pst.setString(3, Email);
				pst.setString(4, PhoneNumber);
				
				pst.executeUpdate();
				
				System.out.println("User register successfully");
				
				response.sendRedirect("home.html");
				
				
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
