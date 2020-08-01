import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

/*
 * Date: August 1st 2020
 * Coders: 
 *   James Jablonski
 *   Darshan B
 *   Maad Abduljaleel
 *   Nikihl
 * Program Name: ProjectSakilaView
 * Purpose: Displays the data contained in the model
 */

public class ProjectSakilaView extends JFrame
{
	
	public JTable table;
	
	public ProjectSakilaView(TableModel model)
	{
		//boilerplate
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500,400);
		this.setLocationRelativeTo(null);
		//GUI this.setLayout(??);
		
		table = new JTable(model);
		
		JScrollPane scrollPane = new JScrollPane(table);
		this.add(scrollPane);
	}
	
	public void updateTable(TableModel model)
	{
		this.table.setModel(model);
	}
	
}
