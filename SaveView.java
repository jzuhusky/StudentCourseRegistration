package hw.hw9;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.*;

public class SaveView {
	
	ViewObserver so;
	private SaveFrame frame;
	private SavePanel panel;

	public SaveView(){
		frame = new SaveFrame();
		panel = frame.getPanel();
	}
	
	public void registerObserver(FileController vo){
		panel.registerObserver(vo);
	}
}

class SaveFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SavePanel panel;
	
	public SaveFrame(){
		setTitle("Save");
		//setSize(200,180);
		setSize(200,100);
		setLocation(90,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		panel = new SavePanel();
		getContentPane().add(panel);
		setVisible(true);
	}
	
	public SavePanel getPanel(){
		return panel;
	}
	
}

class SavePanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton btn, load;
	FileController controller;
	
	public SavePanel(){
		btn = new JButton("Save and Exit");
		btn.setPreferredSize(new Dimension(200,70));
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.save();
				System.exit(0);
			}
		});
		/**
		 * Uncomment below to add a load button-
		 * Also need to get rid of the load method in the 
		 * model's constructor to have any use for this button
		 */
		/*load = new JButton("Load");
		load.setPreferredSize(new Dimension(200,70));
		load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.load();
			}
		});*/
		 
		add(btn); //add(load);
		
	}
	
	public void registerObserver(FileController vo){
		this.controller = vo;
	}
	
}
