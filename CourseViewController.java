package hw.hw9;

import java.util.List;

public class CourseViewController implements ViewObserver, ModelObserver {
	
	CourseRegistrationModel crm;
	CourseView cv;
	
	public CourseViewController(CourseRegistrationModel crm, CourseView cv ){
		this.crm = crm;
		this.cv = cv;	
		crm.registerObserver(this);
		cv.registerObserver(this);
		init();
	}
	
	public void init(){
		cv.init();
	}
	
	/**
	 * Only to be used by Course View
	 */
	public List<Course> getCourses(){
		return crm.getCourses();
	}
	
	public List<Student> getStudents(){
		return crm.getStudents();
	}
	
	public void update(){    	                  // update from the model
		/**
		 * This method is called by the model -> this should update the view to represent the model state
		 */
		if ( crm.addedNewCourse()){
			List<Course> courses = crm.getCourses();
			Course newest = courses.get(courses.size()-1);
			String code = newest.code();
			String title = newest.title();
			cv.addCourse(code,title);
			
		}
	}
	
	public void notify(String code, String title){ // update call from the view
		/**
		 * This method is called by the view to the the model that the user wants to change something
		 */
		if (!code.equals("code") && !title.equals("title"))
			crm.addCourse(code,title);
	}
	
	public int getSelected(){
		return cv.getSelected();
	}

}
