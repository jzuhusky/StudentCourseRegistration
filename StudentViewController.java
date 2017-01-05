package hw.hw9;
import java.util.*;

public class StudentViewController implements ViewObserver, ModelObserver {
	
	CourseRegistrationModel crm;
	StudentView sv;
	int numStudents = 0;
	
	public StudentViewController(CourseRegistrationModel crm, StudentView sv ){
		this.crm = crm;
		this.sv = sv;
		crm.registerObserver(this);
		sv.registerObserver(this);
		init();
	}
	
	public void init(){
		sv.init();
	}
	
	public List<Student> getStudents(){
		return crm.getStudents();
	}
	
	public List<Course> getCourses(){
		return crm.getCourses();
	}
	
	public void update(){    	   // update from the model
		/**
		 * This method is called by the model -> this should update the view to represent the model state
		 */
		if ( crm.addedNewStudent()){
			List<Student> students = crm.getStudents();
			Student newest         = students.get(students.size()-1);
			int ID                 = newest.ID();
			String name            = newest.name();
			int GradYear           = newest.GradYear();
			sv.addStudent(ID, name, GradYear);
		}
	}
	
	public void notify(String name, String GradYear){ // update call from the view
		/**
		 * This method is called by the view to the the model that the user wants to change something
		 */
		try{
			crm.addStudent(name, Integer.parseInt(GradYear));
		}
		catch(Exception e){
			sv.setErrorMsg();
		}
	}

	
}
