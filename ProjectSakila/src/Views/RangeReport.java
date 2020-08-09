package Views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

public class RangeReport extends JFrame {
	JLabel startLbl, endLbl;
	JFormattedTextField startField, endField;
	JButton getDataBtn;
	
	public RangeReport() {
		super("Report: Date Range");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(450, 450);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		initUI();
		
		this.setVisible(true);
	}
	
	private void initUI() {
		JPanel formPanel = new JPanel(new BorderLayout());
		JPanel controlsPanel = new JPanel(new GridLayout(5,1,10,10));
		
		startLbl = new JLabel("Start Date");
		endLbl = new JLabel("End Date");
		
		try {
			//Date Format: MM-DD-YYYY
			startField = new JFormattedTextField(new MaskFormatter("##-##-####"));
			endField = new JFormattedTextField(new MaskFormatter("##-##-####"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(new JFrame(), "Failed to parse the date.", "Error: Parse Failure", JOptionPane.ERROR_MESSAGE);
			return;
		} //MM-DD-YYYY
		
		getDataBtn = new JButton("Get Data");
		
		controlsPanel.add(startLbl);
		controlsPanel.add(startField);
		controlsPanel.add(endLbl);
		controlsPanel.add(endField);
		controlsPanel.add(getDataBtn);
		
		getDataBtn.addActionListener(new Click_Handler());
		
		formPanel.add(controlsPanel);
		formPanel.setBorder(new EmptyBorder(10,10,10,10));
		this.add(formPanel, BorderLayout.NORTH);
	}
	
	private class Click_Handler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == getDataBtn) {
				System.out.println("Get Range Report");
				
				if(startField.getText().equals("")) {
					JOptionPane.showMessageDialog(new JFrame(), "Start Date is required", "Error: Required Field", JOptionPane.ERROR_MESSAGE);
					startField.requestFocus();
					return;
				}
				else {
					System.out.println("Start Date: " + startField.getText());
				}
				
				if(endField.getText().equals("")) {
					JOptionPane.showMessageDialog(new JFrame(), "End Date is required", "Error: Required Field", JOptionPane.ERROR_MESSAGE);
					endField.requestFocus();
					return;
				}
				else {
					System.out.println("End Date: " + endField.getText());
				}
				
			}
		}
		
	}
}
