package hw.hw9;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.util.List;

public class CourseView {

	private CourseInfoFrame frame;
	private CourseInfoPanel panel;

	public CourseView(){
		frame = new CourseInfoFrame();
		panel = frame.getPanel();
	}
	
	public void init(){
		panel.init();
	}

	// Should in theory only register one observer, but can do more if the controller fits. 
	public void registerObserver(ViewObserver vo){
		panel.registerObserver(vo);
	}
	
	public void addCourse(String code, String title){
		panel.addCourse(code, title);
	}
	
	public String getSelectedCode(){
		return (String) panel.getSelectedCode();
	}
	
	public int getSelected(){
		return panel.getSelected();
	}
	
}

class CourseInfoFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CourseInfoPanel panel;
	
	public CourseInfoFrame(){
		setTitle("Course Info");
		setSize(400,200);
		setLocation(500,40);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		panel = new CourseInfoPanel();
		getContentPane().add(panel);
		setVisible(true);
	}
	
	public CourseInfoPanel getPanel(){
		return panel;
	}
	
}

class CourseInfoPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField code,title;
	private JButton btn;
	private JTable table;
	private JScrollPane sp1;
	private DefaultTableModel model;
	private ViewObserver controller;
	private Font it,n;
	
	public CourseInfoPanel(){
		code  = new JTextField(8);
	    title = new JTextField(8);
	    it = new Font("Italic",Font.ITALIC,12);
	    n  = new Font("Normal",Font.PLAIN,12);
	    code.setFont(it);
	    title.setFont(it);
	    code.setText("code");
	    title.setText("title");
	    
	    btn = new JButton("New Course");
	    
	    btn.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		try{
		    		if ( code.getText() != "" && title.getText() != "" ){
		    			String courseCode  = code.getText();
			    		String courseTitle = title.getText();
			    		controller.notify(courseCode, courseTitle); // notify controller of updated view...
			    		code.setText("code");   // reset field to empty 
			    		title.setText("title");
			    	    code.setFont(it);
			    	    title.setFont(it);
		    		}	
	    		}
	    		catch(Exception a){
	    			
	    		}
	    	}
	    });
	    
	    
	    /**
	     * Just some nice features
	     */
	    code.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                code.setText("");
                code.setFont(n);
            }
        });
	    
	    title.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                title.setText("");
                title.setFont(n);
            }
        });
	    
	    String[] colNames = new String[] {"Code","Title"};
	    model = new DefaultTableModel();
	    model.setColumnIdentifiers(colNames);
	    table = new JTable(model);
	    table.setPreferredScrollableViewportSize(new Dimension(300,100));
	    sp1   = new JScrollPane(table);
	    add(btn); add(code); add(title);add(sp1);
	}
	
	public void init(){
		List<Course> lst = controller.getCourses();
		for ( Course c : lst){
			model.addRow( new Object[]{ c.code(), c.title()});
		}
	}
	
	public void addCourse(String code, String title){
		model.addRow( new Object[]{ code, title} );
	}
	
	public void registerObserver(ViewObserver vo){
		this.controller = vo;
	}
	
	public String getSelectedCode(){
		return (String) model.getValueAt(table.getSelectedRow(), 0);
	}
	
	public int getSelected(){
		return table.getSelectedRow();
	}

}
