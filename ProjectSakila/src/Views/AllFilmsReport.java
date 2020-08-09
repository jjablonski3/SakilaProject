
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

package Views;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class AllFilmsReport extends JFrame
{
	JLabel filmLabel, rentalLabel;
	JComboBox filmBox;
	JTextField rentalText;
	public AllFilmsReport() {
		super("All Films Rental Report");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(400, 200);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		
		initAllFilmsRep();
		
		
		this.setVisible(true);
	}
	
	private void initAllFilmsRep () {
		JPanel formPanel = new JPanel(new GridLayout(3,1,10,10));
		JPanel InfoPanel = new JPanel(new GridLayout(1,2,10,10));
		JPanel buttonPanel = new JPanel(new BorderLayout(10,10));
		JPanel resultPanel = new JPanel(new GridLayout(1,2,10,10));
		
		filmBox = new JComboBox();
		
		JButton showButton = new JButton("Calculate Rental Price");
		filmLabel = new JLabel("Film name");
		rentalLabel = new JLabel("Rental Amount $");
		rentalText = new JTextField("some amount");
		rentalText.setEditable(false);
		InfoPanel.add(filmLabel);
		InfoPanel.add(filmBox);
		buttonPanel.add(showButton); 
		resultPanel.add(rentalLabel);
		resultPanel.add(rentalText);
		formPanel.add(InfoPanel);
		formPanel.add(buttonPanel);
		formPanel.add(resultPanel);
		
		this.add(formPanel, BorderLayout.CENTER);
	
	}
}