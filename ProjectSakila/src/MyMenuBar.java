import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Views.AddNewCustomerView;

/*
 * Date: August 1st 2020
 * Coders: 
 *   James Jablonski
 *   Darshan Bhavsar
 *   Maad Abduljaleel
 *   Nikhil Balachandran
 * Program Name: MyMenuBar
 * Purpose: Custom JMenuBar class to implement required menu bar for the project.
 */

public class MyMenuBar extends JMenuBar {
	JMenu fileMenu, addMenu, reportMenu;
	JMenuItem fileExit;
	JMenuItem addNewCust, addNewActor, addNewFilmAndAct, addNewRentalTrans;
	JMenuItem repRentalInc;
	
	public MyMenuBar() {
		//Init Menus.
		initMenus();
		
		//Init Menu Items.
		initMenuItems();
		
		//Attaching Event Handler to all JMenuItems.
		attachEventListeners();
		
		//Add MenuItems to their respective Menus.
		addMenuItemsToMenu();
		
		//Adding Menus to MenuBar.
		addMenusToMenuBar();
	}
	
	private void addMenusToMenuBar() {
		this.add(fileMenu);
		this.add(addMenu);
		this.add(reportMenu);
	}
	
	private void addMenuItemsToMenu() {
		//Add JMenuItems to Different Menus if they're already initialized.
		
		if(fileMenu != null) {
			fileMenu.add(fileExit);
		}
		
		if(addMenu != null) {
			addMenu.add(addNewCust);
			addMenu.add(addNewActor);
			addMenu.add(addNewFilmAndAct);
			addMenu.add(addNewRentalTrans);
		}
		
		if(reportMenu != null) {
			reportMenu.add(repRentalInc);
		}
	}
	
	private void initMenus() {
		fileMenu = new JMenu("File");
		addMenu = new JMenu("Add");
		reportMenu = new JMenu("Reports");
	}
	
	private void initMenuItems() {
		//File Menu Items.
		fileExit = new JMenuItem("Exit");
		
		//Add Menu Items.
		addNewCust = new JMenuItem("Add Customer");
		addNewActor = new JMenuItem("Add Actor");
		addNewFilmAndAct = new JMenuItem("Add Film & Actor(s)");
		addNewRentalTrans = new JMenuItem("Rent a DVD");
		
		//Reports Menu
		repRentalInc = new JMenuItem("Rental Income");
	}
	
	private void attachEventListeners() {
		Click_Handler handler = new Click_Handler();
		
		//Attaching Event Handler to File Menu Options.
		fileExit.addActionListener(handler);
		
		//Attaching Event Handler to Add Menu Options.
		addNewCust.addActionListener(handler);
		addNewActor.addActionListener(handler);
		addNewFilmAndAct.addActionListener(handler);
		addNewRentalTrans.addActionListener(handler);
		
		//Attaching Event Handlers to Report Menu Options.
		repRentalInc.addActionListener(handler);
	}
	
	
	private class Click_Handler implements ActionListener {
		AddNewCustomerView addCust = null;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == fileExit) {
				System.out.println("Exiting..");
				System.exit(0);
			}
			
			if(e.getSource() == addNewCust) {
				System.out.println("Add New Customer");
				
				if(addCust == null) {
					addCust = new AddNewCustomerView();
				}
				else {
					addCust.repaint();
					addCust.setVisible(true);
					addCust.toFront();
				}
			}
			
			if(e.getSource() == addNewActor) {
				System.out.println("Add New Actor");
			}
			
			if(e.getSource() == addNewFilmAndAct) {
				System.out.println("Add New File and Actors");
			}
			
			if(e.getSource() == addNewRentalTrans) {
				System.out.println("Rent a New DVD");
			}
			
			if(e.getSource() == repRentalInc) {
				System.out.println("Show Rental Report");
			}
		}
		
	}
}
