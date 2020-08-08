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
			comboBoxes[i] = new JComboBox();
			
			comboBoxLabels[i].setText(comboLabelValues[i]);
			comboBoxes[i].setSize(70, 15);
			comboBoxes[i].setSelectedIndex(-1);
			
			comboFieldsPanel.add(comboBoxLabels[i]);
			comboFieldsPanel.add(comboBoxes[i]);
		}
		
		//populate dropdowns
		comboBoxes[0].setModel(ProjectSakilaController.fillComboCity());
		
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
	
	
	private class Click_Handler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == submitBtn) {
				System.out.println("Add New Customer");
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
						
					//NIK: error checking on the fields
						//error checking here
				};
				ProjectSakilaController.insertCustomer(params);
			}
			
			if(e.getSource() == clearBtn) {
				System.out.println("Clear");
				for(int i = 0; i < NUMTEXTFIELDS; i++) {
					textFields[i].setText("");
				}
				for(int i = 0; i < NUMCOMBOBOXES; i++) {
					comboBoxes[i].setSelectedIndex(-1);
				}
			}
		}
	}
}
