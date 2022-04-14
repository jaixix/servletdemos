package contextparamsdemos;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//@WebServlet("/TestParamServlet")
public class TestParamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String personalMessage;
	private String license;
	
	 public void init() {
			personalMessage = getServletConfig().getInitParameter("personalMessage");
			license = getServletConfig().getInitParameter("license");
	}
	
    public TestParamServlet() {
    	super();
    }
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside Get Method!");
		PrintWriter out = response.getWriter();
		
		ServletContext sc = getServletContext();
		String message = sc.getInitParameter("message");
		String expiration = sc.getInitParameter("expiration");
		
		out.println("TestParamServlet");
		out.println("-------context params-----------");
		out.println("Message : " + message);
		out.println("Expiration : " + expiration);
		out.println("---------init params------------");
		out.println("Personal Message : " + personalMessage);
		out.println("License : " + license);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside Post Method!");
	}

}
