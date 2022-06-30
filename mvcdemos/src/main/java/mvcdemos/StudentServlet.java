package mvcdemos;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/studentServlet")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Inside doGet Method!");
		List<Student> students = StudentDataUtil.getStudents();
		request.setAttribute("studentList", students);
		RequestDispatcher rd = request.getRequestDispatcher("/view_students.jsp");
		rd.forward(request, response);
	}
}
