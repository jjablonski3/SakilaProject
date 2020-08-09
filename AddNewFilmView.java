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
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import SakilaMVC.*;


public class AddNewFilmView extends JFrame
{
	private String[] txtLabelValues = {"Film Name", "Description","Length","Replacement Cost $", "Actors(separated by commas and a space)"};
	private String[] comboLabelValues = {"Year Of Release", "Language", "Original Language", "Rental Rate", "Duration","Rating" };
	
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
//		textFields[4].setText("Firstname(space)Lastname(comma)(space)");
//		textFields[4].addMouseListener(new MouseAdapter(){
//       @Override
//       public void mouseClicked(MouseEvent  e){
// 				//if(textFields[4].getText().matches("Firstname(space)Lastname(comma)(space)"))
// 	 			if(textFields[4].getText() == "Firstname(space)Lastname(comma)(space)")
// 				{      	 	
//      		 textFields[4].setText("");
//      	 }
//       }
//   });
		
	    
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
	
	
	//[0-9]*\\.?[0-9]*
//Description : To check if the replacement cost is valid
	public static Boolean isValidCost(String textFieldValues){
		if(textFieldValues.matches("[0-9]*\\.?[0-9]*"))
		{
			return true;			
		}
		return false;			

	}
	//^[a-zA-Z0-9] + : [a-zA-Z0-9]+(?:,\s+[a-zA-Z0-9]+:[a-zA-Z0-9]+)+$
//Description : To check if the replacement cost is valid
	public static Boolean isValidSequece(String textFieldValues){
		if(textFieldValues.matches("^([a-zA-Z]+\\s[a-zA-Z]+)(,\\s[a-zA-Z]+\\s[a-zA-Z]+)*$"))
		{
			return true;			
		}
		return false;			

	}
	private class Click_Handler implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
		
//			if(e.getSource() == textFields[4]) {
//			
//				if(textFields[4].getText().matches("Firstname(space)Lastname(comma)(space)"))
//     	 {      	 	
//     		 textFields[4].setText("");
//     	 }
//			}
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
			
			//take off trailing comma
			specialFeaturesString = specialFeaturesString.substring(0, specialFeaturesString.lastIndexOf(","));
			
				
			if(e.getSource() == submitBtn) {
				
					//if not a valid Film title
				if(!AddNewCustomerView.isLetters(textFields[0].getText()) || textFields[0].getText().isBlank())
				{
					JOptionPane.showMessageDialog(null, "Please enter a valid Film title");
					textFields[0].requestFocus();
					textFields[0].setText("");
					return;
				}
				
				//if Film title is too long
				if( textFields[0].getText().length() > 255)
				{
					JOptionPane.showMessageDialog(null, "Please enter a Film title with fewer characters");
					textFields[0].requestFocus();
					return;
				}
				
				
				//if length is invalid
				if(!AddNewCustomerView.isNumbers(textFields[2].getText()))
				{
					JOptionPane.showMessageDialog(null, "Please enter a valid Film length");
					textFields[2].requestFocus();
					textFields[2].setText("");
					return;

				}
				
				//if length is too long or smaller than 0
				try {
				if(AddNewCustomerView.isNumbers(textFields[2].getText()))
				{
					if(Integer.parseInt(textFields[2].getText()) < 0 || Integer.parseInt(textFields[2].getText()) > 65535)
					{					
						JOptionPane.showMessageDialog(null, "Please enter a valid Film length in range");
						textFields[2].requestFocus();
						textFields[2].setText("");
						return;
					}
				}
				}catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "Please enter a valid Film length in range");
					textFields[2].requestFocus();
					textFields[2].setText("");
					return;

	    }
				//if replacementCost is invalid
				if(!isValidCost(textFields[3].getText()) || textFields[3].getText().isBlank())
				{
					JOptionPane.showMessageDialog(null, "Please enter a valid Replacement Cost");
					textFields[3].requestFocus();
					textFields[3].setText("");
					return;
				}
				
				//if Actors is invalid
				
				//^[a-zA-Z]+\s[a-zA-Z]+(?,\s+[a-zA-Z]+\s[a-zA-Z]+)+$
				if(!isValidSequece(textFields[4].getText()) || textFields[4].getText().isBlank())
				{
					JOptionPane.showMessageDialog(null, "Please enter in the following format \n Firstname(space)Lastname(comma)(space)");
					textFields[4].requestFocus();
					textFields[4].setText("");
					return;
				}
				
				
				else {
			
				
				
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
						textFields[4].getText()//special features, Actors
						
					
				};
				ProjectSakilaController.insertFilm(params);
				}//else
			}
			
			if(e.getSource() == clearBtn) {
				
				System.out.println("Clear");
			}
		}
	}
	

}
