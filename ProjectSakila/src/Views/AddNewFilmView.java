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

import javax.swing.JButton;
import javax.swing.JCheckBox;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;



public class AddNewFilmView extends JFrame
{
	private String[] txtLabelValues = {"Film Name", "Description","Length","Replacement Cost $"};
	private String[] comboLabelValues = {"Year Of Release", "Language", "Original Language", "Rental Rate", "Duration","Rating" };
	
	final private int NUMTEXTFIELDS = txtLabelValues.length;
  final private int NUMCOMBOBOXES = comboLabelValues.length;
  
	private JTextField[] textFields = new JTextField[NUMTEXTFIELDS];
	private JLabel[] textFieldLabels = new JLabel[NUMTEXTFIELDS];

	
	private JComboBox[] comboBoxes = new JComboBox[NUMCOMBOBOXES];
	private JLabel[] comboBoxLabels = new JLabel[NUMCOMBOBOXES];
	
	
	private JButton submitBtn, clearBtn;
	
	public AddNewFilmView() {
		super("Add New Film & Actors");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(1000, 600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		initAddFilmForm();
		
		this.setVisible(true);
	}
	
	private void initAddFilmForm() {
		JPanel formPanel = new JPanel(new GridLayout(1, 2, 10, 10));
		JPanel comboLablesPanel = new JPanel(new GridLayout(12, 2, 10, 10));
		for(int i = 0; i < NUMTEXTFIELDS; i++) 
		{
			textFieldLabels[i] = new JLabel();
			textFields[i] = new JTextField();
			
			textFieldLabels[i].setText(txtLabelValues[i]);
			textFields[i].setSize(50,15);
			comboLablesPanel.add(textFieldLabels[i]);
			comboLablesPanel.add(textFields[i]);
			
		}
		
		
		for(int i = 0; i < NUMCOMBOBOXES; i++) { 
			comboBoxLabels[i] = new JLabel();
			comboBoxes[i] = new JComboBox();
			
			comboBoxLabels[i].setText(comboLabelValues[i]);
			comboBoxes[i].setSize(75, 15);
			
			comboLablesPanel.add(comboBoxLabels[i]);
			comboLablesPanel.add(comboBoxes[i]);
		}
		JLabel cbLabel = new JLabel("Special Features");
		comboLablesPanel.add(cbLabel);
		JPanel CheckBoxPanel = new JPanel(new FlowLayout());
		
		
		
		CheckBoxPanel.add(new JCheckBox("Commentaries",false));
		CheckBoxPanel.add(new JCheckBox("Behind the scenes", false));
		CheckBoxPanel.add(new JCheckBox("Trailers", false));
		CheckBoxPanel.add(new JCheckBox("Deleted Scenes", false));
		
		comboLablesPanel.add(CheckBoxPanel);
		submitBtn = new JButton("Submit");
		clearBtn  = new JButton("Clear");
	
		
		comboLablesPanel.add(submitBtn);
		comboLablesPanel.add(clearBtn);
		
		
		
		formPanel.add(comboLablesPanel);
		formPanel.setBorder(new EmptyBorder(10,10,20,10));
		
		this.add(formPanel, BorderLayout.CENTER);
	}
	
	
	

}
