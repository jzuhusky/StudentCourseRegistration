package hw.hw9;

import java.util.List;

public class FileController implements ModelObserver, ViewObserver {
	
	CourseRegistrationModel crm;
	SaveView sv;
	
	public FileController(CourseRegistrationModel crm, SaveView sv){
		this.crm = crm;
		this.sv = sv;
		crm.registerObserver(this);
		sv.registerObserver(this);
		
	}

	public void load(){
		crm.display();
	}
	
	public void save(){
		crm.save();
	}
	
	public void update(){
		// do nothing
	}
	
	public void init(){
		// do nothing
	}


	@Override
	public void notify(String name, String GradYear) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<Student> getStudents() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Course> getCourses() {
		// TODO Auto-generated method stub
		return null;
	}

}
