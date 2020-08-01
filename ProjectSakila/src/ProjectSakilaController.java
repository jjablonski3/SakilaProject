import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.TableModel;


/*
 * Date: August 1st 2020
 * Coders: 
 *   James Jablonski
 *   Darshan B
 *   Maad Abduljaleel
 *   Nikihl
 * Program Name: ProjectSakilaController
 * Purpose: Handles the user input and updates the model
 */


public class ProjectSakilaController extends JFrame
{
	
	public JTextField surnameField = new JTextField("",20);
	public JTextField categoryField = new JTextField("",20);
	
	ProjectSakilaController()
	{
		super("James Jablonski's Actor and Movie Viewer App");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 200);
		this.setLocationRelativeTo(null);
		
		//labels
		JLabel surnameLabel = new JLabel("Enter the surname of the actor here:");
		JLabel categoryLabel = new JLabel("Enter the category type here:");
		
		//button
		JButton buttonExecute = new JButton("Execute Query");
		InnerListener nanny = new InnerListener();
		buttonExecute.addActionListener((ActionListener) nanny);
		
		//JPanels
		JPanel panel = new JPanel(new GridLayout(2,1));
		JPanel panelButton = new JPanel(new GridLayout(1,1));
		JPanel panelGrid = new JPanel(new GridLayout(2,2));

		panelGrid.add(surnameLabel);
		panelGrid.add(surnameField);
		panelGrid.add(categoryLabel);
		panelGrid.add(categoryField);
		
		panelButton.add(buttonExecute);
		
		panel.add(panelGrid);
		panel.add(panelButton);
		

		this.add(panel);
		
		this.setVisible(true);
	}
	
	
	
	
	
	
	
//INNER CLASS
	private class InnerListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e)
		{
			
			if(e.getActionCommand().equals("Execute Query"))
			{
				
				Connection myConn = null;
				Statement myStmt = null;
				ResultSet myRslt = null;
				PreparedStatement myPrepStmt = null;
				try
				{
					myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sakila?useSSL=false","root","password");
					
					myStmt = myConn.createStatement();

					
					myPrepStmt = myConn.prepareStatement("SELECT * FROM film_actor");
					/*myPrepStmt = myConn.prepareStatement("SELECT actor.last_name,"
					+ " actor.first_name,"
					+ " film.title,"
					+ " category.name"
					+ " FROM actor INNER JOIN film_actor.film_id ON actor.actor_id = film_actor.actor_id"
					+ " INNER JOIN film ON film_actor.film_id = film.fild_id"
					+ " INNER JOIN film_category ON film.film_id = film_category.film_id"
					+ " INNER JOIN category ON film_category.category_id = category.category_id"
					+ " WHERE actor.last_name = ? AND category.name = ?");
					*/

					//myPrepStmt.setString(1, surnameField.getText());
					//myPrepStmt.setString(2, categoryField.getText());
			
					myRslt = myPrepStmt.executeQuery();
			
					TableModel model = DbUtils.resultSetToTableModel(myRslt);
			
					ProjectSakilaView view = new ProjectSakilaView(model);					
					view.setVisible(true);
				}
				catch(Exception ex)
				{
					System.out.println(ex.getMessage());
				}
				finally
				{	
					try
					{
						if(myRslt != null)
							myRslt.close();
						if(myStmt != null)
							myStmt.close();
						if(myConn != null)
							myConn.close();
					}
					catch(SQLException ex)
					{
						System.out.println("SQL Exception INSIDE finally block: " + ex.getMessage());
						ex.printStackTrace();
					}
				}//end finally
			}
			
			
		}//end actionPerformed()
		
	}//end inner class
	
	public static void main(String[] args) 
	{
		new ProjectSakilaController();
	}
}
