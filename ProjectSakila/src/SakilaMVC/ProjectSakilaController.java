package SakilaMVC;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.TableModel;


/*
 * Date: August 1st 2020
 * Coders: 
 *   James Jablonski
 *   Darshan Bhavsar
 *   Maad Abduljaleel
 *   Nikhil Balachandran
 * Program Name: ProjectSakilaController
 * Purpose: Handles the user input and updates the model
 */

public class ProjectSakilaController extends JFrame
{
	JLabel welcomeLabel;
	
	
	ProjectSakilaController()
	{
		super("James Jablonski, Darshan Bhavsar, Maad Abduljaleel and Nikhil Balachandran Present: Actor and Movie Viewer App");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 200);
		this.setLocationRelativeTo(null);
		this.setLayout(new FlowLayout());
		
		this.setJMenuBar(new MyMenuBar());
		
		setWelcomeMsg();
		
		this.setVisible(true);
	}
	
	private void setWelcomeMsg() {
		welcomeLabel = new JLabel();
		welcomeLabel.setText("<html><body style='text-align: center'><br>Welcome to the Sakila DVD Rentals.<br>Please choose one of the options from the menu bar to begin.</body></html>");
		
		JPanel p = new JPanel();
		
		this.add(welcomeLabel);
	}
	
	
	public static Object[] fillComboCity(){
  	Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRslt = null;
		PreparedStatement myPrepStmt = null;
      try{
      	  myConn = DriverManager.getConnection("jdbc:mysql://localhost/sakila","root","password");
      	  
      	  myStmt = myConn.createStatement();
      
          myPrepStmt = myConn.prepareStatement("SELECT DISTINCT city FROM sakila.city ");

          myRslt = myPrepStmt.executeQuery();

          //while(myRslt.next()){
          //    String cityName = myRslt.getString("City");          
          //    cityNames.add(cityName);
          //}
          return DbUtils.resultSetToDropdown(myRslt);
      } catch(Exception ex) {
          JOptionPane.showMessageDialog(null, ex.getMessage()); 
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
					return null;
				}
			}//end finally
			return null;
  }
	
	public static void main(String[] args) 
	{
		new ProjectSakilaController();
	}
}
