/**
 * default administrator frame
 */
package ui;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * @author Rares-Vasile Cosma
 */
abstract class DefaultFrame implements ActionListener{

	private JFrame f;
	private JPanel p;
	private JTable table;
	private JButton bLogout, bAdd, bDelete, bUpdate;
	
	/**
	 * prepares the admin frame basic components
	 */
	public DefaultFrame() {
		initComponents();
		
	}
	
	/**
	 * initializes everything for the interface to be ready to use
	 */
	private void initComponents() {
		// initialize the frame
		f = new JFrame();
		f.setSize(700, 500);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		
		// initialize the panel
		p = new JPanel();
		p.setLayout(null);
		f.getContentPane().add(p);
		
		// initialize all the other stuff
		// buttons
		table = new JTable();
		bLogout = new JButton("Logout");
		bAdd = new JButton("Add");
		bDelete = new JButton("Delete");
		bUpdate = new JButton("Update");

		// now try and put them in the right order
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setModel(new DefaultTableModel(5,5));
		
		// add all the stuff and MORE to the panel
		p.add(table);
		p.add(bAdd);
		p.add(bDelete);
		p.add(bUpdate);
		p.add(bLogout);
		
		// set bounds
		this.table.setBounds(230, 10, 440, 400);
		this.bAdd.setBounds(25, 160, 140, 20);
		this.bDelete.setBounds(25, 185, 140, 20);
		this.bUpdate.setBounds(25, 210, 140, 20);
		this.bLogout.setBounds(580, 425, 90, 20);
		
		// action listeners
		this.bLogout.addActionListener(this);
	}
	
	/**
	 * @return the frame of the interface
	 */
	public JFrame getFrame(){
		return f;
	}
	
	/**
	 * @return the left panel of the interface
	 */
	public JPanel getPanel(){
		return p;
	}
	
	/**
	 * @return the add button
	 */
	public JButton getAdd(){
		return bAdd;
	}
	
	/**
	 * @return the update button
	 */
	public JButton getUpdate(){
		return bUpdate;
	}
	
	/**
	 * @return the delete button
	 */
	public JButton getDelete(){
		return bDelete;
	}
	
	/**
	 * @return the table
	 */
	public JTable getTable(){
		return table;
	}
}
