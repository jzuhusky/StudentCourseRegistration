package hw.hw9;

import java.util.List;

public class EnrollmentController implements ModelObserver,EnrollmentObserver {
	
	CourseRegistrationModel crm;
	EnrollmentView ev;
	StudentView sv;
	CourseView cv;
	
	public EnrollmentController(CourseRegistrationModel crm, EnrollmentView ev, StudentView sv, CourseView cv ){
		this.crm = crm;
		this.ev = ev;	
		this.cv = cv;
		this.sv = sv;
		crm.registerObserver(this);
		ev.registerObserver(this);
		/**
		 * Do not register with student and course view
		 * I don't care about those updates, I just need
		 * to access those views for info about which
		 * Row of the table is selected
		 */
	}

	
	public void update(){   // update from the model
		/**
		 * This method is called by the model -> this should update the view to represent the model state
		 * It is never called in this controller
		 * This is because the view asks the controller for info concerning the state,
		 * and therefore requests for itself to be updated.
		 * Whenever the model calls to update the views, it should only be concerned with students and courses
		 * The enrollment controller simply looks at the existing state and displays info...
		 * i.e. it does
		 */

	}
	
	public void notify(String code, String title){ // update call from the view
		/**
		 * ""DO NOT USE FROM ENROLLMENT VIEW""
		 * This method is called by the view to the the model that the user wants to change something
		 * This method isn't used by the Enrollment view, since this view is simply asking for information
		 * concerning the state of the model...
		 */
	}
	
	public void init(){
		ev.init();
	}
	
	/**
	 * The view is asking for information about the current state of the student below
	 * Its also asking for information about the states of the other views, i.e.
	 * which courses or students are selected
	 */
	
	public int getSelectedStudentID(){
		return sv.getSelectedStudentID();
	}
	
	public String getSelectedName(){
		return sv.getSelectedName();
	}
	
	public Student getStudent(int index){
		return crm.getStudent(index);
	}
	
	public String getSelectedCourseCode(){
		return (String) cv.getSelectedCode();
	}
	
	public List<Course> getCourses(int studentID){
		return crm.getCourses(studentID);
	}
	
	public void studentDropCourse(int studentID, String code){
		crm.studentDropCourse(studentID, code);
	}
	
	public void studentAddCourse(int studentID, String code){
		crm.studentAddCourse(studentID, code);
	}
	

}
