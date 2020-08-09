package SakilaMVC;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import Views.AddNewActorView;
import Views.AddNewCustomerView;
import Views.AddNewFilmView;
import Views.AddNewTransaction;
import Views.AllFilmsReport;
import Views.BestCustReport;
import Views.CategoryReport;
import Views.RangeReport;

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
	JMenuItem repRentalInc, repCatInc, repRangeInc, repBestCus;
	
	
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
			reportMenu.add(repCatInc);
			reportMenu.add(repRangeInc);
			reportMenu.add(repBestCus);
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
		repRentalInc = new JMenuItem("Rental Income Report");
		repCatInc = new JMenuItem("Category Income Report");
		repRangeInc = new JMenuItem("Range Income Report");
		repBestCus = new JMenuItem("Best Customer Report");
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
		repCatInc.addActionListener(handler);
		repRangeInc.addActionListener(handler);
		repBestCus.addActionListener(handler);
	}
	
	
	private class Click_Handler implements ActionListener {
		AddNewCustomerView addCust = null;
		AddNewActorView addActor = null;
		AddNewFilmView addFilm = null;
		AddNewTransaction addTrans = null;
		AllFilmsReport filmsReport = null;
		CategoryReport catRep = null;
		RangeReport rangeRep = null;
		BestCustReport custRep = null;

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
				
				if(addActor == null) {
					addActor = new AddNewActorView();
				}
				else {
					addActor.repaint();
					addActor.setVisible(true);
					addActor.toFront();
				}
			}
			
			if(e.getSource() == addNewFilmAndAct) {
				System.out.println("Add New File and Actors");
				
				if(addFilm == null) {
					addFilm = new AddNewFilmView();
				}
				else {
					addFilm.repaint();
					addFilm.setVisible(true);
					addFilm.toFront();
				}
			}
			
			if(e.getSource() == addNewRentalTrans) {
				System.out.println("Rent a New DVD");
				if(addTrans == null) {
					addTrans = new AddNewTransaction();
				}
				else {
					addTrans.repaint();
					addTrans.setVisible(true);
					addTrans.toFront();
				}
			}
		
			if(e.getSource() == repRentalInc) {
				System.out.println("Show Rental Report");
				
				if(filmsReport == null) {
					filmsReport = new AllFilmsReport();
				}
				else {
					filmsReport.repaint();
					filmsReport.setVisible(true);
					filmsReport.toFront();
				}
			}
			
			if(e.getSource() == repCatInc) {
				System.out.println("Show Category Report");
				if(catRep == null) {
					catRep = new CategoryReport();
				}
				else {
					catRep.repaint();
					catRep.setVisible(true);
					catRep.toFront();
				}
			}
			
			if(e.getSource() == repRangeInc) {
				System.out.println("Show Range Report");
				if(rangeRep == null) {
					rangeRep = new RangeReport();
				}
				else {
					rangeRep.repaint();
					rangeRep.setVisible(true);
					rangeRep.toFront();
				}
			}
			if(e.getSource() == repBestCus) {
				System.out.println("Show Best Customer Report");
				
				if(custRep == null) {
					custRep = new BestCustReport();
				}
				else {
					custRep.repaint();
					custRep.setVisible(true);
					custRep.toFront();
				}
			}
		}
			
		
	}
}
