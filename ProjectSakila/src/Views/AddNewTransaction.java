/*
 * Date: August 1st 2020
 * Coders: 
 *   James Jablonski
 *   Darshan Bhavsar
 *   Maad Abduljaleel
 *   Nikhil Balachandran
 * Program Name: AddNewFilmView
 * Purpose: Responsible for adding a new film(s) with its/their actors/actresses.
 */
package Views;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import SakilaMVC.*;


public class AddNewTransaction extends JFrame
{
	private String[] comboLabelValues = {"Customer List", "Movie name"};
	
  final private int NUMCOMBOBOXES = comboLabelValues.length;

	private JComboBox[] comboBoxes = new JComboBox[NUMCOMBOBOXES];
	private JLabel[] comboBoxLabels = new JLabel[NUMCOMBOBOXES];
	
	
	private JButton submitBtn, clearBtn;
	
	public AddNewTransaction() {
		super("Add New Rental Transaction");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(400, 200);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		initAddTransForm();
		
		this.setVisible(true);
	}
	
	private void initAddTransForm() {
		JPanel formPanel = new JPanel(new GridLayout(1, 2, 10, 10));
		JPanel comboLablesPanel = new JPanel(new GridLayout(4, 2, 10, 10));
		
		
		
		for(int i = 0; i < NUMCOMBOBOXES; i++) { 
			comboBoxLabels[i] = new JLabel();
			comboBoxes[i] = new JComboBox();
			
			comboBoxLabels[i].setText(comboLabelValues[i]);
			comboBoxes[i].setSize(75, 15);
			
			comboLablesPanel.add(comboBoxLabels[i]);
			comboLablesPanel.add(comboBoxes[i]);
		}
		
		//populate dropdowns
		comboBoxes[0].setModel(ProjectSakilaController.fillComboCustomer());
		comboBoxes[1].setModel(ProjectSakilaController.fillComboFilm());
		
		submitBtn = new JButton("Submit");
		clearBtn  = new JButton("Clear");
		comboLablesPanel.add(submitBtn);
		comboLablesPanel.add(clearBtn);
		
		Click_Handler handler = new Click_Handler();
		submitBtn.addActionListener(handler);
		clearBtn.addActionListener(handler);
		

		
		formPanel.add(comboLablesPanel);
		formPanel.setBorder(new EmptyBorder(10,10,20,10));
		
		this.add(formPanel, BorderLayout.CENTER);
	}
	
	
	private class Click_Handler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == submitBtn) {
				System.out.println("Submit");
				
				int [] params = {
						comboBoxes[0].getSelectedIndex() + 1,//customer id
						comboBoxes[1].getSelectedIndex() + 1,//film id
				};
				ProjectSakilaController.insertTransaction(params);
			}
			
			if(e.getSource() == clearBtn) {
				for(int i = 0; i < NUMCOMBOBOXES; i++) {
					comboBoxes[i].setSelectedIndex(0);
				}			}
		}
	}
}
