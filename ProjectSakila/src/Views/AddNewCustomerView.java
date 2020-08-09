package Views;

/*
 * Date: August 1st 2020
 * Coders: 
 *   James Jablonski
 *   Darshan Bhavsar
 *   Maad Abduljaleel
 *   Nikhil Balachandran
 * Program Name: ProjectSakilaController
 * Purpose: This is a form that allows users to enter information to insert and store a new customer into the database.
 */

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import SakilaMVC.*;

public class AddNewCustomerView extends JFrame {
	protected String[] txtLabelValues = {"First Name", "Last Name", "Email Address", "Address 1", "Address 2", "District", "Postal Code", "Phone No."};
	protected String[] comboLabelValues = {"City"};
	
	final private int NUMTEXTFIELDS = txtLabelValues.length;
	final private int NUMCOMBOBOXES = comboLabelValues.length;
	
	protected JTextField[] textFields = new JTextField[NUMTEXTFIELDS];
	protected JLabel[] textFieldLabels = new JLabel[NUMTEXTFIELDS];
	
	protected JComboBox[] comboBoxes = new JComboBox[NUMCOMBOBOXES];
	protected JLabel[] comboBoxLabels = new JLabel[NUMCOMBOBOXES];
	
	private JButton submitBtn, clearBtn;
	
	public AddNewCustomerView() {
		super("Add New Customer");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(400, 370);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		//Initialize and Display Signup Form.
		initSignupForm();
		//Init
		
		this.setVisible(true);
	}
	
	private void initSignupForm() {
		JPanel formPanel = new JPanel(new BorderLayout());
		JPanel textFieldsPanel = new JPanel(new GridLayout(NUMTEXTFIELDS, 2, 10, 10));
		
		//add textFields
		for(int i = 0; i < NUMTEXTFIELDS; i++) {
			textFieldLabels[i] = new JLabel();
			textFields[i] = new JTextField();
			
			textFieldLabels[i].setText(txtLabelValues[i]);
			textFields[i].setSize(70, 15);
			
			textFieldsPanel.add(textFieldLabels[i]);
			textFieldsPanel.add(textFields[i]);
		}
		
		JPanel comboFieldsPanel = new JPanel(new GridLayout(NUMCOMBOBOXES+1, 2, 10, 10));
		//add comboBoxes
		for(int i = 0; i < NUMCOMBOBOXES; i++) {
			comboBoxLabels[i] = new JLabel();
			comboBoxes[i] = new JComboBox(ProjectSakilaController.fillComboCity());
			
			comboBoxLabels[i].setText(comboLabelValues[i]);
			comboBoxes[i].setSize(70, 15);
			comboBoxes[i].setSelectedIndex(-1);
			
			comboFieldsPanel.add(comboBoxLabels[i]);
			comboFieldsPanel.add(comboBoxes[i]);
		}
		
		submitBtn = new JButton("Submit");
		clearBtn  = new JButton("Clear");
		comboFieldsPanel.add(submitBtn);
		comboFieldsPanel.add(clearBtn);
		
		Click_Handler handler = new Click_Handler();
		submitBtn.addActionListener(handler);
		clearBtn.addActionListener(handler);
		
		formPanel.add(textFieldsPanel, BorderLayout.NORTH);
		formPanel.add(comboFieldsPanel, BorderLayout.SOUTH);
		
		formPanel.setBorder(new EmptyBorder(10,10,20,10));
		
		this.add(formPanel, BorderLayout.CENTER);
	}
	
	//Description : To check if the string contains only alphabets
	public static Boolean isLetters(String textFieldValues){
		if(textFieldValues.matches("^[A-Za-z]+$"))
		{
			return true;			
		}
		return false;			

	}
	
	//Description : To check if the email is valid
	public static Boolean isValidEmail(String textFieldValues){
		if(textFieldValues.matches("([A-Za-z0-9-_.]+@[A-Za-z0-9-_]+(?:\\.[A-Za-z0-9]+)+)"))
		{//
			return true;			
		}
		return false;			

	}

	//Description : To check if the Address is valid
	public static Boolean isValidAddress(String textFieldValues){
		if(textFieldValues.matches("^[#.0-9a-zA-Z\\s,-]+$"))
		{
			return true;			
		}
		return false;			

	}

	//Description : To check if the string contains only numbers
	public static Boolean isNumbers(String textFieldValues){
		if(textFieldValues.matches("\\b\\d+\\b"))
		{
			return true;			
		}
		return false;			

	}
	
	//Description : To check if the is Postal Code is valid
	public static Boolean isValidPostalCode(String textFieldValues){
		if(textFieldValues.matches("^[a-zA-Z0-9]+$"))
		{
			return true;			
		}
		return false;			

	}
	
	private class Click_Handler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			
			if(e.getSource() == submitBtn) {
				
				//if Firstname is not valid or blank
				if(!isLetters(textFields[0].getText()) || textFields[0].getText().isBlank())
				{
					JOptionPane.showMessageDialog(null, "Please enter a valid Firstname");
					textFields[0].requestFocus();
					textFields[0].setText("");
					return;
				}
				
				//if Firstname is greater than 45 characters 
				if(textFields[0].getText().length() > 45)
				{
					JOptionPane.showMessageDialog(null, "Please enter a Firstname with fewer character");
					textFields[0].requestFocus();
					textFields[0].setText("");
					return;
				}
				
				//if Lastname is not valid or blank
				if(!isLetters(textFields[1].getText()) || textFields[1].getText().isBlank())
				{
					JOptionPane.showMessageDialog(null, "Please enter a valid Lastname");
					textFields[1].requestFocus();
					textFields[1].setText("");
					return;
				}
				
				//if Lastname is greater than 45 characters 
				if(textFields[1].getText().length() > 45)
				{
					JOptionPane.showMessageDialog(null, "Please enter a Lastname with fewer character");
					textFields[1].requestFocus();
					textFields[1].setText("");
					return;
				}
				
				//if Address is blank
				if( textFields[3].getText().isBlank())
				{
					JOptionPane.showMessageDialog(null, "Please enter a valid Address");
					textFields[3].requestFocus();
					return;
				}	
				
				//if Email Address is not valid OR Blank
				if(!isValidEmail(textFields[2].getText()) || textFields[2].getText().isBlank())
				{
					JOptionPane.showMessageDialog(null, "Please enter a valid email Address");
					textFields[2].requestFocus();
					textFields[2].setText("");
					return;
				}	
						
				
				//if Email Address is greater than 50 characters 
				if( textFields[2].getText().length() > 50)
				{
					JOptionPane.showMessageDialog(null, "Please enter a email Address with fewer character");
					textFields[2].requestFocus();
					textFields[2].setText("");
					return;
				}
				
				//if Address is greater than 50 characters 
				if( textFields[3].getText().length() > 50 || textFields[4].getText().length() > 50)
				{
					JOptionPane.showMessageDialog(null, "Please enter a  Address with fewer character");
					textFields[3].requestFocus();
					textFields[3].setText("");
					textFields[4].setText("");

					return;
				}
				
				//if Address is not valid or blank
				if(!isValidAddress(textFields[3].getText()) || textFields[3].getText().isBlank())
				{
					JOptionPane.showMessageDialog(null, "Please enter a valid Address");
					textFields[3].requestFocus();
					textFields[3].setText("");
					return;
				}
				
				//if Address is not blank check if its valid 
				if(!textFields[4].getText().isBlank())
				{
					//if Address is not valid 
					if(!isValidAddress(textFields[4].getText()))
					{
						JOptionPane.showMessageDialog(null, "Please enter a valid Address");
						textFields[4].requestFocus();
						textFields[4].setText("");
						return;
					}
				}
				
				//if District is greater than 10 characters 
				if( textFields[5].getText().length() > 20)
				{
					JOptionPane.showMessageDialog(null, "Please enter a District with fewer character");
					textFields[5].requestFocus();
					textFields[5].setText("");

					return;
				}
				
				//if District is not valid or blank
				if(!isLetters(textFields[5].getText()) || textFields[5].getText().isBlank())
				{
					JOptionPane.showMessageDialog(null, "Please enter a valid District");
					textFields[5].requestFocus();
					textFields[5].setText("");
					return;
				}
				
				//if PostalCode is greater than 10 characters 
				if( textFields[6].getText().length() > 10)
				{
					JOptionPane.showMessageDialog(null, "Please enter a  Postal Code with fewer character");
					textFields[6].requestFocus();
					textFields[6].setText("");

					return;
				}
						
				
				//if PostalCode is not valid or blank
				if(!isValidPostalCode(textFields[6].getText()) || textFields[6].getText().isBlank())
				{
					JOptionPane.showMessageDialog(null, "Please enter a valid Postal Code");
					textFields[6].requestFocus();
					textFields[6].setText("");
					return;
				}
				
				//if Phone Number is greater than 20 characters 
				if( textFields[7].getText().length() > 20)
				{
					JOptionPane.showMessageDialog(null, "Please enter a Phone Number with fewer character");
					textFields[7].requestFocus();
					textFields[7].setText("");

					return;
				}
				
				//if Phone Number is not valid or blank
				if(!isNumbers(textFields[7].getText())  || textFields[7].getText().isBlank())
				{
					JOptionPane.showMessageDialog(null, "Please enter a valid Phone Number");
					textFields[7].requestFocus();
					textFields[7].setText("");
					return;
				}
				
					//if a city is not selected from the drop down
				 if(comboBoxes[0].getSelectedIndex() == -1)
				 {			 
						JOptionPane.showMessageDialog(null, "Please select a city from the drop down");
						return;
				 }

				
				else {
					String[] params = {
							textFields[3].getText(),   //address1
							textFields[4].getText(),   //address2
							textFields[5].getText(),   //district
							Integer.toString(comboBoxes[0].getSelectedIndex() + 1), //city
							textFields[6].getText(),   //postal code
							textFields[7].getText(),   //phone
							textFields[0].getText(),   //fname
							textFields[1].getText(),   //lname
							textFields[2].getText()    //email				
					};
					if(ProjectSakilaController.insertCustomer(params)) {
						JOptionPane.showMessageDialog(null, "Customer '" + textFields[0].getText() + " " +  textFields[1].getText() + "' succesfully inserted");
						
						for(int i = 0; i < NUMTEXTFIELDS; i++) {
							textFields[i].setText("");
						}
						for(int i = 0; i < NUMCOMBOBOXES; i++) {
							comboBoxes[i].setSelectedIndex(-1);
						}
					}
				}//else
			}//submitBtn
			
			if(e.getSource() == clearBtn) {
				for(int i = 0; i < NUMTEXTFIELDS; i++) {
					textFields[i].setText("");
				}
				for(int i = 0; i < NUMCOMBOBOXES; i++) {
					comboBoxes[i].setSelectedIndex(-1);
				}
			}//clearBtn
		}//actionPerformed
	}
}
