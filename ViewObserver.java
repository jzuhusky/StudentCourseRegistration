package hw.hw9;

import java.util.*;

public interface ViewObserver {
	
	/**
	 * Notify -> A course view or a student view has received some new data
	 * Need to notify the controller and tell it what this new data is
	 * The controller will carry out the necessary moves with respect to the model
	 */
	public void notify(String name, String GradYear); // this is a push?!?
	public List<Student> getStudents();
	public List<Course>  getCourses();


}
