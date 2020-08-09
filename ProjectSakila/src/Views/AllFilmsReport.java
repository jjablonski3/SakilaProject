
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;

import SakilaMVC.ProjectSakilaController;


public class AllFilmsReport extends JFrame
{
		JLabel filmLabel, rentalLabel;
		JComboBox filmBox;
		JTextField rentalText;
		JButton showButton;
		public JTable table;
		public JScrollPane scrollPane;
		public AllFilmsReport selfReference = this;

		
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
      JPanel InfoPanel = new JPanel(new GridLayout(2,2,10,10));
      JPanel buttonPanel = new JPanel(new BorderLayout(10,10));
      JPanel resultPanel = new JPanel(new GridLayout(1,2,10,10));
      
      filmBox = new JComboBox();
      //storeBox = new JComboBox();
      JButton showButton = new JButton("Calculate Rental Price");
     // storeLabel = new JLabel("Store #");
      filmLabel = new JLabel("Film name");
      rentalLabel = new JLabel("Rental Amount $");
      rentalText = new JTextField("some amount");
      rentalText.setEditable(false);
      //InfoPanel.add(storeLabel);
      //InfoPanel.add(storeBox);
      InfoPanel.add(filmLabel);
      InfoPanel.add(filmBox);
      buttonPanel.add(showButton); 
      resultPanel.add(rentalLabel);
      resultPanel.add(rentalText);
      formPanel.add(InfoPanel);
      formPanel.add(buttonPanel);
      formPanel.add(resultPanel);
      
      this.add(formPanel, BorderLayout.CENTER);
			
			table = new JTable();
			scrollPane = new JScrollPane(table);
			this.add(scrollPane);
			
			Click_Handler handler = new Click_Handler();
			showButton.addActionListener(handler);
			
					
		}
		
		private class Click_Handler implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource() == showButton) {
					System.out.println("Generate Category Report");
					
					table.setModel(ProjectSakilaController.generateRentalRevenueReport(filmBox.getSelectedIndex() + 1));
					selfReference.repaint();
					selfReference.setVisible(true);
					selfReference.toFront();
				}
			}
		}
}