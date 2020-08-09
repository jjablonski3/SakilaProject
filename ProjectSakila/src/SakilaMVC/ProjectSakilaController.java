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
import java.util.Arrays;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
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
	
	public static DefaultComboBoxModel fillComboCategories(){
	  	Connection myConn = null;
			Statement myStmt = null;
			ResultSet myRslt = null;
			PreparedStatement myPrepStmt = null;
	      try{
	      	  myConn = DriverManager.getConnection("jdbc:mysql://localhost/sakila","root","290500Db!");
	      	  
	      	  myStmt = myConn.createStatement();
	      	  
	          myPrepStmt = myConn.prepareStatement("SELECT DISTINCT name FROM sakila.category");

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
	
	public static Boolean insertCustomer(String[] params){
  	Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRslt = null;
		PreparedStatement myPrepStmt = null;
		int rowsAffected;
      try{
      	  myConn = DriverManager.getConnection("jdbc:mysql://localhost/sakila","root","password");
      	  
      	  //Transaction, so we turn autocommit off
      	  myConn.setAutoCommit(false);
      	  
      	  myStmt = myConn.createStatement();
      
          myPrepStmt = myConn.prepareStatement("INSERT INTO sakila.address " +
          		"(address, address2, district, city_id, postal_code, phone, location) " +
          		"VALUES (?, ?, ?, ?, ?, ?, POINT(0,0))", Statement.RETURN_GENERATED_KEYS);
          
          //address textfield
          myPrepStmt.setString(1, params[0]);
          //address2 textfield
          myPrepStmt.setString(2, params[1]);
          //district textfield
          myPrepStmt.setString(3, params[2]);
          //city_id from dropdown
          myPrepStmt.setInt(4, Integer.parseInt(params[3]));
          //postalcode textfield
          myPrepStmt.setString(5, params[4]);
          //phone textfield
          myPrepStmt.setString(6, params[5]);
          
          System.out.println(myPrepStmt.toString());

          rowsAffected = myPrepStmt.executeUpdate();
          System.out.println("Rows Affected: " + rowsAffected);
          
          if(rowsAffected != 1)
          {
          	throw new SQLException("SQL failed on the address insert");
          }
          
          myRslt = myPrepStmt.getGeneratedKeys();
          int newRowId = 0;
          if(myRslt.next())
          	newRowId = myRslt.getInt(1);
          
          myPrepStmt = myConn.prepareStatement("INSERT INTO sakila.customer " +
          		"(store_id, first_name, last_name, email, address_id, create_date)" +
          		"VALUES (1, ?, ?, ?, ?, now())");
          
         //fname textfield
          myPrepStmt.setString(1, params[6]);
          //lname textfield
          myPrepStmt.setString(2, params[7]);
          //email textfield
          myPrepStmt.setString(3, params[8]);
          //address ID that was just created
          myPrepStmt.setInt(4, newRowId);
          
          System.out.println(myPrepStmt.toString());
          
          rowsAffected = myPrepStmt.executeUpdate();
          
          if(rowsAffected != 1)
          {
          	throw new SQLException("SQL failed on the customer insert");
          }
          
          //if everything succeeded we want to commit the transaction to the db
          myConn.commit();
          return true;

          
      } 
      catch(Exception ex) {
    	  	//rollback the transaction
			try {
				myConn.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
					return false;
				}
			}//end finally
			return false;
	}
	
	
	public static Boolean insertActor(String[] params){
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRslt = null;
		PreparedStatement myPrepStmt = null;
		int rowsAffected;
      try{
      	 myConn = DriverManager.getConnection("jdbc:mysql://localhost/sakila","root","password");
 	  
     		 myStmt = myConn.createStatement();
     
         myPrepStmt = myConn.prepareStatement("INSERT INTO sakila.actor " +
         		"(first_name, last_name) " +
         		"VALUES (?, ?)");
         
         //fname textfield
         myPrepStmt.setString(1, params[0]);
         //lname textfield
         myPrepStmt.setString(2, params[1]);
         
         System.out.println(myPrepStmt.toString());

         rowsAffected = myPrepStmt.executeUpdate();
         System.out.println("Rows Affected: " + rowsAffected);
         
         if(rowsAffected != 1)
         {
         	throw new SQLException("SQL failed on the actor insert");
         }
         
         
         return true;
      }
      catch(Exception ex) {
		    JOptionPane.showMessageDialog(null, ex.getMessage()); 
		  }//end catch
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
					return false;
				}
			}//end finally
			return false;
	}
	
	
	
	public static Boolean insertFilm(String[] params){
  	Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRslt = null;
		PreparedStatement myPrepStmt = null;
		int rowsAffected;
      try{
      	  myConn = DriverManager.getConnection("jdbc:mysql://localhost/sakila","root","password");
      	  
      	  //Transaction, so we turn autocommit off
      	  myConn.setAutoCommit(false);
      	  
      	  myStmt = myConn.createStatement();
      
          myPrepStmt = myConn.prepareStatement("INSERT INTO sakila.film " +
          		"(title, description, release_year, language_id, original_language_id, " +
          		"rental_duration, rental_rate, length, replacement_cost, rating, special_features) " +
          		"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
          
          //title textfield
          myPrepStmt.setString(1, params[0]);
          //description textfield
          myPrepStmt.setString(2, params[1]);
          //release_year dropdown
          myPrepStmt.setInt(3, Integer.parseInt(params[2]));
          //language_id from dropdown
          myPrepStmt.setInt(4, Integer.parseInt(params[3]));
          //original_language_id from dropdown
          myPrepStmt.setInt(5, Integer.parseInt(params[4]));
          //rental_duration from dropdown
          myPrepStmt.setString(6, params[5]);
          //rental_rate from dropdown
          myPrepStmt.setDouble(7, Double.parseDouble(params[6]));
          //length from textfield
          myPrepStmt.setInt(8, Integer.parseInt(params[7]));
          //replacement_cost textfield
          myPrepStmt.setString(9, params[8]);
          //rating from dropdown
          myPrepStmt.setString(10, params[9]);
          //special_features from checkboxes (multiple)
          myPrepStmt.setString(11, params[10]);
          
          
          System.out.println(myPrepStmt.toString());

          rowsAffected = myPrepStmt.executeUpdate();
          
          System.out.println("Rows Affected: " + rowsAffected);
          if(rowsAffected != 1)
          {
          	throw new SQLException("SQL failed on the film insert");
          }
          
          myRslt = myPrepStmt.getGeneratedKeys();
          int newRowId = 0;
          if(myRslt.next())
          	newRowId = myRslt.getInt(1);
          
          //split the actors string into an array
          ArrayList<String> actorsArr = 
          			new ArrayList<String>(Arrays.asList(params[11].split(",\\s*")));
          
          //for each actor, find their id, and insert into junction table
          for(int i = 0; i < actorsArr.size(); i++)
          {
          	//first select the actor from the db if they exist and get their id
          	myPrepStmt = myConn.prepareStatement("SELECT DISTINCT actor_id FROM sakila.actor " +
            		"WHERE first_name = ? AND last_name = ?");
          	
          	//split the fullname into first and last
          	String[] FirstAndLastName = actorsArr.get(i).split("\\s+");
            myPrepStmt.setString(1, FirstAndLastName[0].toUpperCase());//first name
            myPrepStmt.setString(2, FirstAndLastName[1].toUpperCase());//last name
          	
          	System.out.println(myPrepStmt.toString());
          	myRslt = myPrepStmt.executeQuery();
          	
          	//go to first node and read the actor id
          	int actorId = -1;
          	myRslt.next();
          	actorId = myRslt.getInt("actor_id");
          	
          	//if the loop didn't run, exit this iteration
          	if(actorId == -1) {
          		throw new Exception("Actor:" + FirstAndLastName[0].toUpperCase() +" "+  FirstAndLastName[1].toUpperCase()+ "not found.");
          	}
          	
          	myPrepStmt = myConn.prepareStatement("INSERT INTO sakila.film_actor " +
            		"(actor_id, film_id)" +
            		"VALUES (?, ?)");
            
            //get the actor id and get it to insert
            myPrepStmt.setInt(1, actorId);
            myPrepStmt.setInt(2, newRowId);
            
            System.out.println(myPrepStmt.toString());      
            rowsAffected = myPrepStmt.executeUpdate();
            
            if(rowsAffected != 1)
            {
            	throw new SQLException("SQL failed on the film_actor insert");
            }
          }
          
          myPrepStmt = myConn.prepareStatement("INSERT INTO sakila.inventory " +
          		"(film_id, store_id) " +
          		"VALUES (?, 1)", Statement.RETURN_GENERATED_KEYS);
          
          myPrepStmt.setInt(1, newRowId);
          
          System.out.println(myPrepStmt.toString());

          rowsAffected = myPrepStmt.executeUpdate();
          System.out.println("Rows Affected: " + rowsAffected);
          
          if(rowsAffected != 1)
          {
          	throw new SQLException("SQL failed on the inventory insert");
          }
          
          //if everything succeeded we want to commit the transaction to the db
          myConn.commit();
          return true;

          
      } 
      catch(Exception ex) {
    	  //rollback the transaction
				try {
					myConn.rollback();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
					return false;
				}
			}//end finally
			return false;
  }
	
	public static Boolean insertTransaction(int[] params){
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRslt = null;
		PreparedStatement myPrepStmt = null;
		int rowsAffected;
      try{
      	 myConn = DriverManager.getConnection("jdbc:mysql://localhost/sakila","root","password");
 	  
     		 myStmt = myConn.createStatement();
     		 
         myPrepStmt = myConn.prepareStatement("SELECT DISTINCT sakila.inventory.inventory_id, sakila.film.rental_duration FROM sakila.inventory " + 
        		 	 "INNER JOIN sakila.film " +
        		 	 "ON sakila.film.film_id = sakila.inventory.film_id " +
          		 "WHERE sakila.inventory.film_id = ? LIMIT 1");
         
         //film id
         myPrepStmt.setInt(1, params[0]);

         System.out.println(myPrepStmt.toString());
         myRslt = myPrepStmt.executeQuery();

         //go to first node and read the actor id
         int inventoryId = -1;
         int rentalDays = -1;
         myRslt.next();
         inventoryId = myRslt.getInt("inventory_id");
         rentalDays = myRslt.getInt("rental_duration");
         	
         if(inventoryId == -1 || rentalDays == -1) {
         		throw new Exception("Failed to find film in invetory");
         }
              
         myPrepStmt = myConn.prepareStatement("INSERT INTO sakila.rental " +
         		"(rental_date, inventory_id, customer_id, return_date, staff_id) " +
         		"VALUES (Now(), ?, ?, (SELECT DATE_ADD(Now(), INTERVAL ? DAY)), 1)");
         
         //InventoryId
         myPrepStmt.setInt(1, inventoryId);
         //CustomerId
         myPrepStmt.setInt(2, params[1]);
         //Rental Duration
         myPrepStmt.setInt(3, rentalDays);
         
         System.out.println(myPrepStmt.toString());

         rowsAffected = myPrepStmt.executeUpdate();
         System.out.println("Rows Affected: " + rowsAffected);
         
         if(rowsAffected != 1)
         {
         	throw new SQLException("SQL failed on the rental insert");
         }
         
         return true;
      }
      catch(Exception ex) {
		    JOptionPane.showMessageDialog(null, ex.getMessage()); 
		  }//end catch
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
					return false;
				}
			}//end finally
			return false;
	}
	
	
	public static DefaultComboBoxModel fillComboCity(){
  	Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRslt = null;
		PreparedStatement myPrepStmt = null;
      try{
      	  myConn = DriverManager.getConnection("jdbc:mysql://localhost/sakila","root","password");
      	  
      	  myStmt = myConn.createStatement();
      	  
          myPrepStmt = myConn.prepareStatement("SELECT DISTINCT city FROM sakila.city ");

          myRslt = myPrepStmt.executeQuery();
          
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
	
	public static DefaultComboBoxModel fillComboLanguage()
	{
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRslt = null;
		PreparedStatement myPrepStmt = null;
      try{
      	  myConn = DriverManager.getConnection("jdbc:mysql://localhost/sakila","root","password");
      	  
      	  myStmt = myConn.createStatement();
      	  
          myPrepStmt = myConn.prepareStatement("SELECT DISTINCT name FROM sakila.language");

          myRslt = myPrepStmt.executeQuery();

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
	
	public static DefaultComboBoxModel fillComboRating()
	{
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRslt = null;
		PreparedStatement myPrepStmt = null;
      try{
      	  myConn = DriverManager.getConnection("jdbc:mysql://localhost/sakila","root","password");
      	  
      	  myStmt = myConn.createStatement();
      	  
          myPrepStmt = myConn.prepareStatement("SELECT DISTINCT rating FROM sakila.film");

          myRslt = myPrepStmt.executeQuery();

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
	
	public static DefaultComboBoxModel fillComboCustomer(){
  	Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRslt = null;
		PreparedStatement myPrepStmt = null;
      try{
      	  myConn = DriverManager.getConnection("jdbc:mysql://localhost/sakila","root","password");
      	  
      	  myStmt = myConn.createStatement();
      	  
          myPrepStmt = myConn.prepareStatement("SELECT DISTINCT CONCAT(first_name, \" \", last_name) FROM sakila.customer");

          myRslt = myPrepStmt.executeQuery();
          
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
	
	public static DefaultComboBoxModel fillComboFilm(){
  	Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRslt = null;
		PreparedStatement myPrepStmt = null;
      try{
      	  myConn = DriverManager.getConnection("jdbc:mysql://localhost/sakila","root","password");
      	  
      	  myStmt = myConn.createStatement();
      	  
          myPrepStmt = myConn.prepareStatement("SELECT title FROM sakila.film");

          myRslt = myPrepStmt.executeQuery();
          
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
