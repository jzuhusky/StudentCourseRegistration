package hw.hw9;

import java.io.Serializable;
import java.util.*;

public class Course implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1165350096148213444L;
	private String title;
	private String code; // should I even use this??
	private List<Student> students;
	
	public Course(String code, String title){
		this.code = code;
		this.title = title;
		this.students = new LinkedList<>();
	}
	
	public String code(){
		return code;
	}
	
	public String title(){
		return this.title;
	}
	
	public Iterator<Student> students(){
		return students.iterator();
	}
	
	public String toString(){
		return code;
	}

}
