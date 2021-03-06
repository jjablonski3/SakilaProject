package Views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import SakilaMVC.ProjectSakilaController;

/*
 * Date: August 1st 2020
 * Coders: 
 *   James Jablonski
 *   Darshan Bhavsar
 *   Maad Abduljaleel
 *   Nikhil Balachandran
 * Program Name: MyMenuBar
 * Purpose: Custom JMenuBar class to implement required menu bar for the project.
 */

public class CategoryReport extends JFrame{
	JComboBox categoryList = new JComboBox(ProjectSakilaController.fillComboCategories());
	JLabel catLabel = new JLabel("Selct a Category:");
	JComboBox storeBox = new JComboBox();
	JLabel storeLabel = new JLabel("Store #");
	
	JButton getDataBtn = new JButton("Get Data");
	JPanel resultPanel = new JPanel(new FlowLayout());
	
	public CategoryReport() {
		super("Report: Category");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(450, 450);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		//Init Controls on UI.
		initUI();
		
		this.setVisible(true);
	}
	
	private void initUI() {
		JPanel formPanel = new JPanel(new BorderLayout());
		JPanel controlsPanel = new JPanel(new GridLayout(2,2,10,10));
		JPanel btnPanel = new JPanel(new FlowLayout());
		
		controlsPanel.add(storeLabel);
		controlsPanel.add(storeBox);
		controlsPanel.add(catLabel);
		controlsPanel.add(categoryList);
		formPanel.add(controlsPanel, BorderLayout.NORTH);
		btnPanel.add(getDataBtn);
		btnPanel.setBorder(new EmptyBorder(10,10,10,10));
		formPanel.add(btnPanel);
		
		formPanel.setBorder(new EmptyBorder(10,10,10,10));
		
		this.add(formPanel);
	}
	
	private class Click_Handler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == getDataBtn) {
				System.out.println("Generate Category Report");
				
			}
		}
		
	}
}
