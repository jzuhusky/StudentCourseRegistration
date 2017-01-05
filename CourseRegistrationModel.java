package hw.hw9;

import java.io.*;
import java.util.*;

public class CourseRegistrationModel{
	
	private OutputStream os;
	private ObjectOutputStream output;
	private InputStream is;
	private ObjectInputStream input;
	
	private List<Student>  students;
	private List<Course>   courses;
	private List<ModelObserver> controllers;

	/*Flags for controllers to look and see if state has changed*/
	private boolean newStudent = false;
	private boolean newCourse  = false;
	// ID numbers
	private int ID;
	
	public CourseRegistrationModel(){
		this.controllers = new LinkedList<>();
		this.students = new LinkedList<>();
		this.ID = 0;
		this.courses  = new LinkedList<>();
		try{
			load();
		}
		catch(Exception e){
			// throw some exception...
		}
	}
	
	/**
	 * 
	 * @param name is name of new student
	 * @param GradYear is year of graduation -> this will notify all observers that
	 * the state of the model has changed.
	 * This same general idea is used for adding a new course
	 */
	public void addStudent(String name, int GradYear){
		students.add( new Student(ID++,name,GradYear) );
		newCourse  = false;
		newStudent = true;
		notifyObservers();
		newStudent = false;
	}
		
	public void addCourse(String code, String title){
		courses.add(new Course(code,title));
		newStudent = false;
		newCourse  = true;
		notifyObservers();
		newCourse = false;
	}
	
	/**
	 * 
	 * These are flags for the observers to look at
	 * when deciding if any information has changed that
	 * is relative to their views
	 */
	public boolean addedNewStudent(){
		return newStudent;
	}
	
	public boolean addedNewCourse(){
		return newCourse;
	}
	
	
	/**
	 * 
	 * Some generic helper functions...
	 */
	public void addCourse(Course c){
		courses.add(c);
	}
	
	public List<Student> getStudents(){
		return students;
	}
	
	public List<Course> getCourses(){
		return courses;
	}
	
	public int numStudents(){
		return students.size();
	}
	
	/**
	 * Observable implements notifyObservers!! 
	 * This is key to observer pattern
	 * This lets views know state has changed
	 */
	public void notifyObservers(){
		for (ModelObserver c : controllers){
			c.update();
		}
	}
	
	/**
	 * Observers need to be registered...
	 */
	public void registerObserver(ModelObserver mo){
		controllers.add(mo);
	}
	/**
	 * Dont really ever use this....
	 */
	public void display() {
		try{
			load();
		}
		catch(Exception e){
			System.out.println("error");
		}
		for (ModelObserver mo : controllers){
			mo.init();
		}
	}
	
	public void exitModel(){
		save();
	}
	
	@SuppressWarnings("unchecked")
	public void load() throws IOException, FileNotFoundException, ClassNotFoundException{
		is = new FileInputStream("test.dat");
		input = new ObjectInputStream(is);
		this.ID       = (int)           input.readObject();
		this.students = (List<Student>) input.readObject();
		this.courses  = (List<Course>)  input.readObject();
		input.close();
	}
	
	
	public void save(){
		try{
			os = new FileOutputStream("test.dat");
			output = new ObjectOutputStream(os);
			output.writeObject(ID);
			output.writeObject(students);
			output.writeObject(courses);
			output.close();
			System.exit(0);
		}
		catch(IOException e){
			System.out.println("i/o exception");
			System.out.println(e.getMessage());
		}
		
	}
	
	public Student getStudent(int index){
		return students.get(index);
	}
	
	public List<Course> getCourses(int studentID){
		for (Student s : students){
			if ( s.ID() == studentID ){
				return s.getCourses();
			}
		}
		return null;
	}
	
	public void studentAddCourse(int ID, String code){
		for (Student s : students){
			if (s.ID() == ID ){
				for (Course c : courses){
					if (c.code().equals(code) ){
						s.addCourse(c);
					}
				}
			}
		}
	}
	
	public void studentDropCourse(int ID, String code){
		for (Student s : students){
			if ( s.ID() == ID ){
				s.dropCourse(code);
			}
		}
	}
	

}
