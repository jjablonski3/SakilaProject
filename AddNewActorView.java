package Views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import SakilaMVC.ProjectSakilaController;


/*
 * Date: August 1st 2020
 * Coders: 
 *   James Jablonski
 *   Darshan Bhavsar
 *   Maad Abduljaleel
 *   Nikhil Balachandran
 * Program Name: ProjectSakilaController
 * Purpose: This form lets users create a new actor and store it in the database.
 */

public class AddNewActorView extends JFrame {
	JButton submitBtn, clearBtn;
	JTextField fnameField, lnameField;
	String[] txtFieldLables = {"First Name", "Last Name"};
	
	public AddNewActorView() {
		super("Add New Actor");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(400, 150);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		//Initialize and Display Signup Form.
		initNewActorForm();
		//Init
		
		this.setVisible(true);
	}
	
	private void initNewActorForm() {
		JPanel formPanel = new JPanel(new BorderLayout());
		JPanel textFieldsPanel = new JPanel(new GridLayout(2, 2, 10, 10));
		
		fnameField = new JTextField();
		lnameField = new JTextField();
		
		textFieldsPanel.add(new JLabel(txtFieldLables[0]));
		textFieldsPanel.add(fnameField);
		textFieldsPanel.add(new JLabel(txtFieldLables[1]));
		textFieldsPanel.add(lnameField);
		
		JPanel buttonsPanel = new JPanel(new GridLayout(1, 2, 10, 10));
		submitBtn = new JButton("Submit");
		clearBtn  = new JButton("Clear");
		buttonsPanel.add(submitBtn);
		buttonsPanel.add(clearBtn);
		
		Click_Handler handler = new Click_Handler();
		submitBtn.addActionListener(handler);
		clearBtn.addActionListener(handler);
		
		formPanel.add(textFieldsPanel, BorderLayout.NORTH);
		formPanel.add(buttonsPanel, BorderLayout.SOUTH);
		
		formPanel.setBorder(new EmptyBorder(10,10,20,10));
		
		this.add(formPanel);
	}
	
	private class Click_Handler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			if(e.getSource() == submitBtn) {
				System.out.println("Add New Actor");
				if(!AddNewCustomerView.isLetters(fnameField.getText()) || fnameField.getText().isBlank())
				{
					JOptionPane.showMessageDialog(null, "Please enter a valid Firstname");
					fnameField.requestFocus();
					fnameField.setText("");

					return;
				}
				
				if( fnameField.getText().length() > 45)
				{
					JOptionPane.showMessageDialog(null, "Please enter a Firstname with fewer characters");
					fnameField.requestFocus();
					fnameField.setText("");
					return;
				}
				
				if(!AddNewCustomerView.isLetters(lnameField.getText()) || lnameField.getText().isBlank())
				{
					JOptionPane.showMessageDialog(null, "Please enter a valid Lastname");
					lnameField.requestFocus();
					lnameField.setText("");
					return;
				}
				
				if( lnameField.getText().length() > 45)
				{
					JOptionPane.showMessageDialog(null, "Please enter a Lastname with fewer characters");
					lnameField.requestFocus();
					lnameField.setText("");
					return;
				}
				else {
				String[] params = {
						fnameField.getText(),   //fname
						lnameField.getText(),   //lname
		
				};
				
				if(ProjectSakilaController.insertActor(params)) {
					JOptionPane.showMessageDialog(null, "Actor '" + fnameField.getText() + lnameField.getText() + "' succesfully inserted");

						fnameField.setText("");
						lnameField.setText("");
				}
				}//else
			}//submitBtn
			
			if(e.getSource() == clearBtn) {
				System.out.println("Clear Fields");
				
				if(fnameField != null) {
					fnameField.setText("");
				}
				
				if(lnameField != null) {
					lnameField.setText("");
				}
			}
		}
		
	}
}
