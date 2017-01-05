package hw.hw9;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.util.List;

public class StudentView {
	
	private StudentInfoFrame frame;
	private StudentInfoPanel panel;

	public StudentView(){
		frame = new StudentInfoFrame();
		panel = frame.getPanel();
	}
	
	public void init(){
		panel.init();
	}

	// Should in theory only register one observer, but can do more if the controller fits. 
	public void registerObserver(ViewObserver vo){
		panel.registerObserver(vo);
	}
	
	public void addStudent(int ID, String name, int gradYear){
		panel.addStudent(ID, name, gradYear);
	}
	
	public void setErrorMsg(){
		panel.setErrorMsg();
	}
	
	public int getSelectedStudentID(){
		/**
		 * Gets the ID of the selected student
		 */
		return panel.getSelectedID();
	}
	
	public String getSelectedName(){
		return panel.getSelectedName();
	}
}

class StudentInfoFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2622249426889540816L;
	private StudentInfoPanel panel;
	
	public StudentInfoFrame(){
		setTitle("Student Info");
		setSize(400,200);
		setLocation(40,40);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		panel = new StudentInfoPanel();
		getContentPane().add(panel);
		setVisible(true);
	}
	
	public StudentInfoPanel getPanel(){
		return panel;
	}
	
}

class StudentInfoPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 286384003267645181L;
	private JTextField name;
	private JTextField year;
	private JButton btn;
	private JTable table;
	private JScrollPane sp1;
	private DefaultTableModel model;
	private ViewObserver controller;
	private Font it,n;
	
	public StudentInfoPanel(){
		name = new JTextField(8);
	    year = new JTextField(6);
	    it = new Font("Italic",Font.ITALIC,12);
	    n  = new Font("Normal",Font.PLAIN,12);
	    name.setFont(it);
	    year.setFont(it);
	    name.setText("name");
	    year.setText("gradYear");
	    btn = new JButton("New Student");
	    
	    btn.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		if ( name.getText() != "" && year.getText() != ""){
	    			String stuName = name.getText();
		    		String GradYear = year.getText();
		    		controller.notify(stuName, GradYear); // notify controller of updated view...
		    		name.setFont(it);
		    		year.setFont(it);
		    		name.setText("name");                 // reset field to empty 
		    		year.setText("gradYear");
		    		
	    		}
	    	}
	    });
	    
	    /**
	     * Just some nice features
	     */
	    name.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                name.setText("");
                name.setFont(n);
            }
        });
	    
	    year.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                year.setText("");
                year.setFont(n);
            }
        });
	    
	   	     
	    String[] colNames = new String[] {"ID","Name","GradYear"};
	   
	    model = new DefaultTableModel();
	    model.setColumnIdentifiers(colNames);
	    table = new JTable(model);
	    table.setPreferredScrollableViewportSize(new Dimension(300,100));
	    sp1 = new JScrollPane(table);
	    
	    
	    add(btn); 
	    add(name); 
	    add(year);
	    add(sp1);
	}
	
	
	public void addStudent(int ID, String name, int gradYear){
		model.addRow( new Object[]{ ID, name, gradYear} );
	}
	
	public void registerObserver(ViewObserver vo){
		this.controller = vo;
	}
	
	public void setErrorMsg(){
		name.setText("Error");   // reset field to empty 
		year.setText("");
	}
	
	public int getSelectedID(){
		return (int) model.getValueAt(table.getSelectedRow(), 0);
	}
	
	public String getSelectedName(){
		return (String) model.getValueAt(table.getSelectedRow(), 1);
	}
	
	public void init(){
		List<Student> lst = controller.getStudents();
		for ( Student c : lst){
			model.addRow( new Object[]{ c.ID(), c.name(), c.GradYear()});
		}
	}
}

