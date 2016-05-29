/**
 * administrator frame for CRUD on Tickets
 */
package ui;

import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import bl.Exporter;
import bl.ExporterFactory;
import bl.ShowManager;
import bl.TicketManager;
import models.Show;
import models.Ticket;

/**
 * @author Rares-Vasile Cosma
 *
 */
public class ClientFrameTickets extends DefaultFrame {

	private JTextField tIdShow, tRow, tNo;
	private JLabel lIdShow, lRow, lNo, lExportType;
	private JComboBox<String> cExportType;
	
	/**
	 * constructor for all the client interface to CRUD tickets
	 */
	public ClientFrameTickets() {
		super();
		initRestOfComponents();
		populateTable();
	}
	
	/**
	 * method to add the specific content to the frame
	 */
	public void initRestOfComponents() {
		String[] sComboBoxValues = {"JSON", "CSV"};
		// text fields
		tIdShow = new JTextField();
		tRow = new JTextField();
		tNo = new JTextField();
		// labels
		lIdShow = new JLabel("ID Show:");
		lRow = new JLabel("Row:");
		lNo = new JLabel("No:");
		lExportType = new JLabel("Export Type:");
		// combobox
		cExportType = new JComboBox<String>(sComboBoxValues);

		// add objects to panel
		getPanel().add(lIdShow);
		getPanel().add(lRow);
		getPanel().add(lNo);
		getPanel().add(lExportType);
		getPanel().add(tIdShow);
		getPanel().add(tRow);
		getPanel().add(tNo);
		getPanel().add(cExportType);
		
		// arrange them
		this.lIdShow.setBounds(10,10,100,20);
		this.lRow.setBounds(10,35,100,20);
		this.lNo.setBounds(10,60,100,20);
		this.lExportType.setBounds(10,85,100,20);
		this.tIdShow.setBounds(100,10,100,20);
		this.tRow.setBounds(100,35,100,20);
		this.tNo.setBounds(100,60,100,20);
		this.cExportType.setBounds(100,85,100,20);
		
		// modify a few names
		getAdd().setText("Sell Ticket");
		getUpdate().setText("Export show tickets");
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
			TicketManager tm = new TicketManager();
			ShowManager sm = new ShowManager();
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
		else if (pressedButton.equals("Export show tickets"))
		{
			// create the factory
			ExporterFactory factory = new ExporterFactory();
			Integer selectedIndex = this.cExportType.getSelectedIndex();
			Integer showId;
			// create the exporter
			Exporter exp = factory.createExporter(selectedIndex);
			
			// export to the specified format for the indicated show id
			try
			{
				showId = Integer.parseInt(this.tIdShow.getText());
			}
			catch (Exception e)
			{
				JOptionPane.showMessageDialog(null, "Please complete the show id before exporting",
						"Incomplete form!", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if (exp.export(showId))
			{
				JOptionPane.showMessageDialog(null, "Successfully exported show's tickets!",
						"Export successful", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Error exporting the show!",
						"Export ERROR", JOptionPane.ERROR_MESSAGE);	
			}
		}
	}

}
