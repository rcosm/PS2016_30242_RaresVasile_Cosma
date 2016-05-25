/**
 * administrator frame for CRUD on Tickets
 */
package ui;

import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import bl.ShowManager;
import bl.TicketManager;
import models.Show;
import models.Ticket;

/**
 * @author Rares-Vasile Cosma
 *
 */
public class ClientFrameTickets extends DefaultFrame {

	private TicketManager tm;
	private ShowManager sm;
	
	private JTextField tIdShow, tRow, tNo;
	private JLabel lIdShow, lRow, lNo;
	
	/**
	 * constructor for all the client interface to CRUD tickets
	 */
	public ClientFrameTickets() {
		super();
		sm = new ShowManager();
		tm = new TicketManager();
		initRestOfComponents();
		populateTable();
	}
	
	/**
	 * method to add the specific content to the frame
	 */
	public void initRestOfComponents() {
		// text fields
		tIdShow = new JTextField();
		tRow = new JTextField();
		tNo = new JTextField();
		// labels
		lIdShow = new JLabel("ID Show:");
		lRow = new JLabel("Row:");
		lNo = new JLabel("No:");

		// add objects to panel
		getPanel().add(lIdShow);
		getPanel().add(lRow);
		getPanel().add(lNo);
		getPanel().add(tIdShow);
		getPanel().add(tRow);
		getPanel().add(tNo);
		
		// arrange them
		this.lIdShow.setBounds(10,10,100,20);
		this.lRow.setBounds(10,35,100,20);
		this.lNo.setBounds(10,60,100,20);
		this.tIdShow.setBounds(100,10,100,20);
		this.tRow.setBounds(100,35,100,20);
		this.tNo.setBounds(100,60,100,20);
		
		// modify a few names
		getAdd().setText("Sell Ticket");
		getUpdate().setVisible(false);
		getDelete().setVisible(false);
		
		// add action listener
		getAdd().addActionListener(this);
		getDelete().addActionListener(this);
		getUpdate().addActionListener(this);
	}
	
	/**
	 * method to add data into the table
	 */
	public void populateTable() {
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

	@SuppressWarnings("unused")
	@Override
	public void actionPerformed(ActionEvent ev) {
		String pressedButton = ev.getActionCommand();
		
		if (pressedButton.equals("Logout"))
		{
			LoginFrame f1 = new LoginFrame();
			getFrame().dispose();
		}
		else if (pressedButton.equals("Sell Ticket"))
		{
			Ticket ticketToAdd = new Ticket();
			try
			{
				ticketToAdd.setNr(Integer.parseInt(this.tNo.getText()));
				ticketToAdd.setShowId(Integer.parseInt(this.tIdShow.getText()));
				ticketToAdd.setRow(Integer.parseInt(this.tRow.getText()));
			}
			catch (Exception ioe)
			{
				JOptionPane.showMessageDialog(null, "Show ID, Row and No should all be valid numbers",
						"Parse Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			Show s = sm.getShowInfo(ticketToAdd.getShowId());
			if (s == null)
			{
				JOptionPane.showMessageDialog(null, "Could not get the information about the selected show",
						"Query error!", JOptionPane.ERROR_MESSAGE);
				return;
			}
			// validate the no of tickets
			if (s.getNrOfTickets() == 0)
			{
				JOptionPane.showMessageDialog(null, "No tickets left!",
						"No more tickets!", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			// validate the row and no
			if (tm.ticketExists(ticketToAdd))
			{
				JOptionPane.showMessageDialog(null, "Ticket already sold",
						"Sold ticket!", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (!tm.sellTicket(ticketToAdd))
			{
				JOptionPane.showMessageDialog(null, "Error at sell operation!",
						"Sell ERROR", JOptionPane.ERROR_MESSAGE);
				return;
			}
			s.setNrOfTickets(s.getNrOfTickets()-1);
			if (!sm.updateShow(s))
			{
				JOptionPane.showMessageDialog(null, "Error at show update!",
						"Sell ERROR", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			JOptionPane.showMessageDialog(null, "Ticket successfully sold!",
					"Sell success", JOptionPane.INFORMATION_MESSAGE);
			
			populateTable();
		}
	}

}
