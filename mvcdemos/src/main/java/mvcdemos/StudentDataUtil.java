package mvcdemos;

import java.util.ArrayList;
import java.util.List;

public class StudentDataUtil {
	
	public static List<Student> getStudents(){
		List<Student> students = new ArrayList<Student>();
		students.add(new Student("John", "Wilson", "jwil@example.com"));
		students.add(new Student("Alex", "Johnson", "aj@example.com"));
		students.add(new Student("William", "Ray", "rwil@example.com"));
		students.add(new Student("Max", "Jackson", "maxjack@example.com"));
		return students;
	}
}
