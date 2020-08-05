package Views;

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
	
	final private int NUMTEXTFIELDS = 4;
	final private int NUMCOMBOBOXES = 2;
	
	private JTextField[] textFields = new JTextField[NUMTEXTFIELDS];
	private JLabel[] textFieldLabels = new JLabel[NUMTEXTFIELDS];
	private String[] txtLabelValues = {"First Name", "Last Name", "Email Address", "Address"};
	
	private JComboBox[] comboBoxes = new JComboBox[NUMCOMBOBOXES];
	private JLabel[] comboBoxLabels = new JLabel[NUMCOMBOBOXES];
	private String[] comboLabelValues = {"City", "District"};
	
	private JButton submitBtn, clearBtn;
	
	public AddNewCustomerView() {
		super("Add New Customer");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(400, 300);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		//Initialize and Display Signup Form.
		initSignupForm();
		//Init
		
		this.setVisible(true);
	}
	
	private void initSignupForm() {
		JPanel formPanel = new JPanel(new GridLayout(2, 1, 10, 10));
		JPanel textFieldsPanel = new JPanel(new GridLayout(4, 2, 10, 10));
		
		//add textFields
		for(int i = 0; i < NUMTEXTFIELDS; i++) {
			textFieldLabels[i] = new JLabel();
			textFields[i] = new JTextField();
			
			textFieldLabels[i].setText(txtLabelValues[i]);
			textFields[i].setSize(70, 15);
			
			textFieldsPanel.add(textFieldLabels[i]);
			textFieldsPanel.add(textFields[i]);
		}
		
		JPanel comboFieldsPanel = new JPanel(new GridLayout(3, 2, 10, 10));
		//add comboBoxes
		for(int i = 0; i < NUMCOMBOBOXES; i++) {
			comboBoxLabels[i] = new JLabel();
			comboBoxes[i] = new JComboBox(ProjectSakilaController.fillComboCity());
			
			comboBoxLabels[i].setText(comboLabelValues[i]);
			comboBoxes[i].setSize(70, 15);
			
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
		
		formPanel.add(textFieldsPanel);
		formPanel.add(comboFieldsPanel);
		
		formPanel.setBorder(new EmptyBorder(10,10,20,10));
		
		this.add(formPanel, BorderLayout.CENTER);
	}
	
	
	private class Click_Handler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == submitBtn) {
				System.out.println("Submit");
				
			}
			
			if(e.getSource() == clearBtn) {
				for(int i = 0; i < NUMTEXTFIELDS; i++) {
					textFields[i].setText("");
				}
				for(int i = 0; i < NUMCOMBOBOXES; i++) {
					comboBoxes[i].setSelectedIndex(0);
				}
			}
		}
	}
}
