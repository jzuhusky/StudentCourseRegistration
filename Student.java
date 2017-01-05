package hw.hw9;

import java.io.Serializable;
import java.util.*;

public class Student implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -19539141298610974L;
	private String name;
	private int gradYear;
	private int ID;
	private List<Course> courses;
	
	public Student(int ID, String name, int GradYear){
		this.name = name;
		this.gradYear = GradYear;
		this.courses = new LinkedList<Course>();
		this.ID = ID;
	}
	
	public List<Course> getCourses(){
		return courses;
	}
	
	public void addCourse(Course c){
		courses.add(c);
	}
	
	public void dropCourse(Course c){
		if ( courses.contains(c) ){
			courses.remove(c);
		}
		// else do nothing...
	}
	
	public String name(){
		return this.name;
	}
	
	public int ID(){
		return ID;
	}
	
	public int GradYear(){
		return gradYear;
	}
	
	public void dropCourse(String code){
		for (Course c : courses){
			if (c.code().equals(code) ){
				courses.remove(c);
			}
		}
	}
	
	public String toString(){
		return name;
	}
	
	

}
