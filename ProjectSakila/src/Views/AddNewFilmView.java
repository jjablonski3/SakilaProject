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

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import SakilaMVC.*;


public class AddNewFilmView extends JFrame
{
	private String[] txtLabelValues = {"Film Name", "Description","Length","Replacement Cost $", "Actors(separated by commas and a space)"};
	private String[] comboLabelValues = {"Year Of Release", "Language", "Original Language", "Rental Rate", "Rental Duration","Rating" };
	
	final private int NUMTEXTFIELDS = txtLabelValues.length;
  final private int NUMCOMBOBOXES = comboLabelValues.length;
  
	private JTextField[] textFields = new JTextField[NUMTEXTFIELDS];
	private JLabel[] textFieldLabels = new JLabel[NUMTEXTFIELDS];

	
	private JComboBox[] comboBoxes = new JComboBox[NUMCOMBOBOXES];
	private JLabel[] comboBoxLabels = new JLabel[NUMCOMBOBOXES];
	
	private JCheckBox trailersCheckBox;
	private JCheckBox commentariesCheckBox;
	private JCheckBox deletedScenesCheckBox;
	private JCheckBox behindTheScenesCheckBox;
	
	final protected double DOUBLESFORRENTALRATE[] = {4.99, 2.99, 0.99};
	
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
		JPanel comboLablesPanel = new JPanel(new GridLayout(13, 2, 10, 10));
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
		
		//populate dropdowns
		comboBoxes[0].setModel(populateWithIntRange(1900,2020)); //release year
		comboBoxes[1].setModel(ProjectSakilaController.fillComboLanguage()); //main language
		comboBoxes[2].setModel(ProjectSakilaController.fillComboLanguage()); //original language
		comboBoxes[3].setModel(populateRentalRates()); //rental rate
		comboBoxes[4].setModel(populateWithIntRange(3, 7)); //duration
		comboBoxes[5].setModel(ProjectSakilaController.fillComboRating()); //rating
		
		
		JLabel cbLabel = new JLabel("Special Features");
		comboLablesPanel.add(cbLabel);
		JPanel CheckBoxPanel = new JPanel(new FlowLayout());
		
		trailersCheckBox = new JCheckBox("Trailers", false);
		commentariesCheckBox = new JCheckBox("Commentaries",false);
		deletedScenesCheckBox = new JCheckBox("Deleted Scenes", false);
		behindTheScenesCheckBox = new JCheckBox("Behind the Scenes", false);
		
		CheckBoxPanel.add(trailersCheckBox);
		CheckBoxPanel.add(commentariesCheckBox);
		CheckBoxPanel.add(deletedScenesCheckBox);
		CheckBoxPanel.add(behindTheScenesCheckBox);
		
		comboLablesPanel.add(CheckBoxPanel);
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
	
	DefaultComboBoxModel populateWithIntRange(int startYear, int endYear)
	{
		Integer[] intArr = new Integer[(endYear - startYear) + 1];
		
		int counter = 0;
		for (int i = endYear; i >= startYear; i--) {
			intArr[counter] = i;
			counter++;
		}
		return new DefaultComboBoxModel(intArr);
	}
	
	DefaultComboBoxModel populateRentalRates()
	{
		String[] stringArr = new String[] {"$4.99", "$2.99", "$0.99"};
		return new DefaultComboBoxModel(stringArr);	
	}
	
	
	private class Click_Handler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == submitBtn) {
				System.out.println("Submit");
				
				//build special features string
				String specialFeaturesString = "";
				if(trailersCheckBox.isSelected()) 
					specialFeaturesString += trailersCheckBox.getText() + ",";
				if(commentariesCheckBox.isSelected()) 
					specialFeaturesString += commentariesCheckBox.getText() + ",";
				if(deletedScenesCheckBox.isSelected()) 
					specialFeaturesString += deletedScenesCheckBox.getText() + ",";
				if(behindTheScenesCheckBox.isSelected()) 
					specialFeaturesString += behindTheScenesCheckBox.getText() + ",";
				
				if(specialFeaturesString.length() > 1)
				{
					//take off trailing comma
					specialFeaturesString = specialFeaturesString.substring(0, specialFeaturesString.lastIndexOf(","));
				}
				else {
					specialFeaturesString = "";
				}
				
				
				String[] params = {
						//"Year Of Release", "Language", "Original Language", "Rental Rate", "Duration","Rating"
						textFields[0].getText(), //film title
						textFields[1].getText(), //description
						comboBoxes[0].getSelectedItem().toString(),//release year
						Integer.toString(comboBoxes[1].getSelectedIndex() + 1),//language_id
						Integer.toString(comboBoxes[2].getSelectedIndex() + 1),//original lang id
						comboBoxes[4].getSelectedItem().toString(),//rental duration 
						Double.toString(DOUBLESFORRENTALRATE[comboBoxes[3].getSelectedIndex() + 1]), //rental rate
						textFields[2].getText(), //length
						textFields[3].getText(), //replacementCost
						comboBoxes[5].getSelectedItem().toString(),//rating
						specialFeaturesString,
						textFields[4].getText()//special features
						
					//NIK: error checking on the fields
						//error checking here
				};
				ProjectSakilaController.insertFilm(params);
			}
			
			if(e.getSource() == clearBtn) {
				System.out.println("Clear");
			}
		}
	}
	

}
