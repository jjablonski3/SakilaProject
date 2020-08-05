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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class AddNewCustomerView extends JFrame {
	private JTextField[] textFields = new JTextField[4];
	private JLabel[] textFieldLabels = new JLabel[4];
	private String[] txtLabelValues = {"First Name", "Last Name", "Email Address", "Address"};
	
	private JComboBox[] comboBoxes = new JComboBox[2];
	private JLabel[] comboBoxLabels = new JLabel[2];
	private String[] comboLabelValues = {"City", "District"};
	
	private JButton submitBtn, clearBtn;
	
	public AddNewCustomerView() {
		super("Add New Customer");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(400, 300);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		//this.setLayout();
		
		//Initialize and Display Signup Form.
		initSignupForm();
		
		this.setVisible(true);
	}
	
	private void initSignupForm() {
		JPanel formPanel = new JPanel(new GridLayout(2, 1, 10, 10));
		JPanel textFieldsPanel = new JPanel(new GridLayout(4, 2, 10, 10));
		
		for(int i = 0; i < 4; i++) {
			textFieldLabels[i] = new JLabel();
			textFields[i] = new JTextField();
			
			textFieldLabels[i].setText(txtLabelValues[i]);
			textFields[i].setSize(70, 15);
			
			textFieldsPanel.add(textFieldLabels[i]);
			textFieldsPanel.add(textFields[i]);
		}
		
		JPanel comboFieldsPanel = new JPanel(new GridLayout(3, 2, 10, 10));
		for(int i = 0; i < 2; i++) {
			comboBoxLabels[i] = new JLabel();
			comboBoxes[i] = new JComboBox();
			
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
			// TODO Auto-generated method stub
			if(e.getSource() == submitBtn) {
				System.out.println("Submit");
			}
			
			if(e.getSource() == clearBtn) {
				System.out.println("Clear");
			}
		}
	}
}
