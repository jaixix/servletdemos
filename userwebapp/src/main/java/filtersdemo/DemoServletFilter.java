package filtersdemo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

@WebFilter("/demoServlet")
public class DemoServletFilter extends HttpFilter implements Filter {
	
	public void destroy() {
		System.out.println("Filter destroyed!");
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("Inside doFilter Method in Filter!");
		PrintWriter out = response.getWriter();
		out.println("Before calling Demo Servlet!");
		chain.doFilter(request, response);
		out.println("\nAfter calling Demo Servlet!");
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("Inside Filter init!");
	}
}
