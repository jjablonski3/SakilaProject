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

public class BestCustReport extends JFrame
{
	JComboBox bestCusBox;
	JLabel bestCusLbl;
	JButton reportButton;
	public BestCustReport() {
		super("Best Customer Report");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(500, 150);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		initBestCusReport();
		
		this.setVisible(true);
	}
	
	private void initBestCusReport()
	{
		bestCusLbl = new JLabel("Best Customer");
		bestCusBox = new JComboBox();
		reportButton = new JButton("Submit");
		JPanel formPanel = new JPanel(new BorderLayout(20,20));
		JPanel buttonPanel = new JPanel(new BorderLayout());
		JPanel InfoPanel = new JPanel(new GridLayout(1,2,50,50));
		JPanel resultPanel = new JPanel(new FlowLayout());
		JLabel test = new JLabel("RESULT GOES HERE");
		buttonPanel.setSize(400,100);
		InfoPanel.add(bestCusLbl);
		InfoPanel.add(bestCusBox);
		buttonPanel.add(reportButton, BorderLayout.CENTER);
		resultPanel.add(test);
		formPanel.add(InfoPanel, BorderLayout.NORTH);
		formPanel.add(buttonPanel, BorderLayout.CENTER);
		formPanel.add(resultPanel, BorderLayout.SOUTH);
		this.add(formPanel, BorderLayout.NORTH);
	}
	
}
