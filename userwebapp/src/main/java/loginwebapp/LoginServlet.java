package loginwebapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loginServlet1")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Connection connection;
	public void init() {
		System.out.println("initializing addservlet...");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "root");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside Post Method!");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Statement statement;
		try {
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from user where email='"+email+"' and password='"+password+"'");
			if(rs.next()) {
				PrintWriter out = response.getWriter();
				response.setContentType("text/html");
				out.println("<p>Login Successful!</p>");
				RequestDispatcher rd = request.getRequestDispatcher("homeServlet");
				request.setAttribute("greeting", "Welcome to Servlets - "+ email);
				rd.forward(request, response);
			}
			else {
				PrintWriter out = response.getWriter();
				response.setContentType("text/html");
				out.println("<p>Login failed</p>");
				RequestDispatcher rd = request.getRequestDispatcher("loginServlet.html");
				rd.include(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void destroy() {
		try {
			if (connection != null) {
				connection.close();
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
}
