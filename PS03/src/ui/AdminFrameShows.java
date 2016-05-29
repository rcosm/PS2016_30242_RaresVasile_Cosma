/**
 * administrator frame for CRUD on Shows
 */
package ui;

import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import bl.ShowManager;
import models.Show;

/**
 * @author Rares-Vasile Cosma
 *
 */
public class AdminFrameShows extends DefaultFrame {

	private JTextField tId, tAdministration, tDistribution, tTitle, tNrOfTickets, tPremiere;
	private JLabel lId, lAdministration, lDistribution, lTitle, lNrOfTickets, lPremiere;
	private JButton bClients;
	
	/**
	 * constructor for all the admin interface to CRUD clients
	 */
	public AdminFrameShows() {
		super();
		initRestOfComponents();
		populateTable();
	}
	
	/**
	 * method to add the specific content to the frame
	 */
	public void initRestOfComponents() {
		// text fields
		tId = new JTextField();
		tAdministration = new JTextField();
		tDistribution = new JTextField();
		tTitle = new JTextField();
		tNrOfTickets = new JTextField();
		tPremiere = new JTextField();
		// labels
		lId = new JLabel("ID:");
		lAdministration = new JLabel("Administration:");
		lDistribution = new JLabel("Distribution:");
		lTitle = new JLabel("Title:");
		lNrOfTickets = new JLabel("NrOfTickets:");
		lPremiere = new JLabel("Premiere");
		// buttons
		bClients = new JButton("Clients");

		// add objects to panel
		getPanel().add(lId);
		getPanel().add(lAdministration);
		getPanel().add(lDistribution);
		getPanel().add(lTitle);
		getPanel().add(lNrOfTickets);
		getPanel().add(lPremiere);
		getPanel().add(tId);
		getPanel().add(tAdministration);
		getPanel().add(tDistribution);
		getPanel().add(tTitle);
		getPanel().add(tNrOfTickets);
		getPanel().add(tPremiere);
		getPanel().add(bClients);
		
		// arrange them
		this.lId.setBounds(10,10,100,20);
		this.lAdministration.setBounds(10,35,100,20);
		this.lDistribution.setBounds(10,60,100,20);
		this.lTitle.setBounds(10,85,100,20);
		this.lNrOfTickets.setBounds(10,110,100,20);
		this.lPremiere.setBounds(10,135,100,20);
		this.tId.setBounds(100,10,100,20);
		this.tAdministration.setBounds(100,35,100,20);
		this.tDistribution.setBounds(100,60,100,20);
		this.tTitle.setBounds(100,85,100,20);
		this.tNrOfTickets.setBounds(100,110,100,20);
		this.tPremiere.setBounds(100,135,100,20);
		this.bClients.setBounds(190, 425, 150, 20);
		
		// modify a few names
		getAdd().setText("Add show");
		getUpdate().setText("Update show");
		getDelete().setText("Delete show");
		
		// add action listener
		getAdd().addActionListener(this);
		getDelete().addActionListener(this);
		getUpdate().addActionListener(this);
		this.bClients.addActionListener(this);
	}
	
	/**
	 * method to add data into the table
	 */
	public void populateTable() {
		ShowManager sm = new ShowManager();
		String[] columnNames = {"id", "administration", "distribution", "title", "nr of tickets", "premiere"};
		DefaultTableModel model = new DefaultTableModel( columnNames, 0 );
		Vector<String> rowColumnNames = new Vector<String>();
		rowColumnNames.add("id");
		rowColumnNames.add("administration");
		rowColumnNames.add("distribution");
		rowColumnNames.add("title");
		rowColumnNames.add("nr of tickets");
		rowColumnNames.add("premiere");
		model.addRow(rowColumnNames);
		
		List<Show> allShows = sm.getAllShows(); 
		
		for (Show s : allShows)
		{
			Vector<String> newRow = new Vector<String>();
			newRow.add(s.getId() + "");
			newRow.add(s.getAdministration());
			newRow.add(s.getDistribution());
			newRow.add(s.getTitle());
			newRow.add(s.getNrOfTickets()+"");
			newRow.add(s.getPremiere()+"");
			model.addRow(newRow);
		}
		
		this.getTable().setModel(model);
	}
	
	/**
	 * method to specify what actions to perform when pressing a button
	 */
	@SuppressWarnings("unused")
	public void actionPerformed(ActionEvent ev) {
		String pressedButton = ev.getActionCommand();
		
		if (pressedButton.equals("Logout"))
		{
			LoginFrame f1 = new LoginFrame();
			getFrame().dispose();
		}
		else if (pressedButton.equals("Clients"))
		{
			AdminFrameClients f2 = new AdminFrameClients();
			getFrame().dispose();
		}
		else if (pressedButton.contains("show"))
		{
			ShowManager sm = new ShowManager();
			Show s = new Show();
			if (pressedButton.contains("Add"))
			{
				s.setTitle(this.tTitle.getText());
				s.setDistribution(this.tDistribution.getText());
				s.setNrOfTickets(Integer.parseInt(this.tNrOfTickets.getText()));
				s.setAdministration(this.tAdministration.getText());

				DateFormat formatter; 
				Date date;
				formatter = new SimpleDateFormat("dd-MMM-yy");
				try {
					date = formatter.parse(this.tPremiere.getText());
					s.setPremiere(date);
				} catch (ParseException e) {
					JOptionPane.showMessageDialog(null, "Date format should be: dd-MMM-yy. MMM should be english string month", "Wrong date format!",
							JOptionPane.ERROR_MESSAGE); 
					return;
				}
				
				if (!sm.addShow(s))
				{
					JOptionPane.showMessageDialog(null, "Something went wrong! Could not add show :(", "Add show failed", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "show successfully added!", "Add show succeded", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			else if (pressedButton.contains("Update"))
			{
				Boolean bGoOn = false;
				try
				{
					s.setId(Integer.parseInt(this.tId.getText()));
					bGoOn = true;
				}
				catch (NumberFormatException e)
				{
					JOptionPane.showMessageDialog(null, "Please complete the id of the show with a number before updating the show!",
							"Show information incomplete!", JOptionPane.ERROR_MESSAGE);
				}
				if (this.tAdministration.getText().equals("") || this.tDistribution.getText().equals("") || this.tTitle.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please don't leave any textfield empty before updating the show!",
							"Show information incomplete!", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (bGoOn)
				{
					s.setTitle(this.tTitle.getText());
					s.setDistribution(this.tDistribution.getText());
					s.setNrOfTickets(Integer.parseInt(this.tNrOfTickets.getText()));
					s.setAdministration(this.tAdministration.getText());
					DateFormat formatter ; 
					Date date ; 
					formatter = new SimpleDateFormat("dd-MMM-yy");
					try {
						date = formatter.parse(this.tPremiere.getText());
						s.setPremiere(date);
					} catch (ParseException e) {
						JOptionPane.showMessageDialog(null, "Date format should be: dd-MMM-yy. MMM should be english string month", "Wrong date format!",
								JOptionPane.ERROR_MESSAGE); 
						return;
					}

					if (!sm.updateShow(s))
					{
						JOptionPane.showMessageDialog(null, "Something went wrong! Could not update show information :(",
								"Update show failed", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Show information successfully updated!",
								"Update show succeded", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
			else if (pressedButton.contains("Delete"))
			{
				Boolean bGoOn = false;
				try
				{
					s.setId(Integer.parseInt(this.tId.getText()));
					bGoOn = true;
				}
				catch (NumberFormatException e)
				{
					JOptionPane.showMessageDialog(null, "Please complete the id of the user with a number before deleting the user!",
							"User information incomplete!", JOptionPane.ERROR_MESSAGE);
				}
				if (bGoOn)
				{
					if (!sm.deleteShow(s))
					{
						JOptionPane.showMessageDialog(null, "Something went wrong! Could not delete show :(",
								"Delete show failed", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Show successfully deleted!", "Delete show succeded", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
			populateTable();
		}
	}
}
