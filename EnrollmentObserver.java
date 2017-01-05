package hw.hw9;

import java.util.*;

public interface EnrollmentObserver {

	public int getSelectedStudentID();
	public String getSelectedCourseCode();
	public List<Course> getCourses(int studentID);
	public String getSelectedName();
	
	public void studentAddCourse(int StudentID, String code);
	public void studentDropCourse(int studentID, String code);
}
