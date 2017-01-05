package hw.hw9;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class EnrollmentView {

	ViewObserver vo; // can't be generic list
	private EnrollmentFrame frame;
	private EnrollmentPanel panel;

	public EnrollmentView(){
		frame = new EnrollmentFrame();
		panel = frame.getPanel();
	}

	// Should in theory only register one observer, but can do more if the controller fits. 
	public void registerObserver(EnrollmentObserver vo){
		panel.registerObserver(vo);
	}
	
	public void init(){
		// do nothing...
	}
	
}

class EnrollmentFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EnrollmentPanel panel;
	
	public EnrollmentFrame(){
		setTitle("Enrollments");
		setSize(500,200);
		setLocation(300,300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		panel = new EnrollmentPanel(this);
		getContentPane().add(panel);
		setVisible(true);
	}
	
	public EnrollmentPanel getPanel(){
		return panel;
	}
	
}

class EnrollmentPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton addBtn, dropBtn, selectStudent;
	private JTable table;
	private JScrollPane sp1;
	private DefaultTableModel model;
	private EnrollmentObserver controller;
	
	public EnrollmentPanel( JFrame frame ){
	    addBtn = new JButton("add");
	    dropBtn = new JButton("drop");
	    selectStudent = new JButton("SELECT STUDENT");

	    String[] colNames = new String[] {"Code","Title"};
	    //Object[][]  items = new Object[][] {};
	   
	    model = new DefaultTableModel();
	    model.setColumnIdentifiers(colNames);
	    table = new JTable(model);
	    table.setPreferredScrollableViewportSize(new Dimension(300,100));
	    sp1   = new JScrollPane(table);
	    
	    selectStudent.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		/**
	    		 * Notify model that view wants to know what student is selected...
	    		 */
	    		try{
	    			int ID               = controller.getSelectedStudentID();
		    		String name          = controller.getSelectedName();
		    		List<Course> courses = controller.getCourses(ID);
		    		frame.setTitle("Enrollments for "+name);
		    		model = new DefaultTableModel();
		    		model.setColumnIdentifiers(colNames);
		    		table.setModel(model);
		    		for ( Course c : courses){
		    			model.addRow(new Object[] {c.code(), c.title() } );
		    		}	
	    		}
	    		catch(Exception a){
	    			//System.out.println("hello error");
	    		}
	    		
	    	}
	    });
	    
	    addBtn.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		try{
		    		int ID      = controller.getSelectedStudentID();
		    		String code = controller.getSelectedCourseCode();
		    		controller.studentAddCourse(ID,code);
		    		model = new DefaultTableModel();
		    		model.setColumnIdentifiers(colNames);
		    		table.setModel(model);
		    		List<Course> courses = controller.getCourses(ID);
		    		for ( Course c : courses){
		    			model.addRow(new Object[] {c.code(), c.title()} );
		    		}
	    		}
	    		catch(Exception a){
	    			// do nothing
	    		}

	    	}
	    });
	    
	    dropBtn.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		try{
		    		int ID            = controller.getSelectedStudentID();
		    		String courseCode = (String) model.getValueAt(table.getSelectedRow(), 0);
		    		controller.studentDropCourse(ID, courseCode);
		    		model = new DefaultTableModel();
		    		model.setColumnIdentifiers(colNames);
		    		table.setModel(model);
		    		List<Course> courses = controller.getCourses(ID);
		    		for ( Course c : courses){
		    			model.addRow(new Object[] {c.code(), c.title() } );
		    		}	
	    		}
	    		catch(Exception a){
	    			
	    		}
	    	}
	    });
	    add(sp1); 
	    add(addBtn); 
	    add(dropBtn);
	    add(selectStudent);
	}
	
	public void registerObserver(EnrollmentObserver vo){
		this.controller = vo;
	}

}
