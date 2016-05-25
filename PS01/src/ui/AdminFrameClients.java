/**
 * administrator frame for CRUD on Clients
 */
package ui;

import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import bl.UserManager;
import models.User;

/**
 * @author Rares-Vasile Cosma
 *
 */
public class AdminFrameClients extends DefaultFrame {

	private UserManager um;
	
	private JTextField tId, tUserName, tPassword, tName, tRole;
	private JLabel lId, lUserName, lPassword, lName, lRole;
	private JButton bShows;
	
	/**
	 * constructor for all the admin interface to CRUD clients
	 */
	public AdminFrameClients() {
		super();
		um = new UserManager();
		initRestOfComponents();
		populateTable();
	}
	
	/**
	 * method to add the specific content to the frame
	 */
	public void initRestOfComponents() {
		// text fields
		tId = new JTextField();
		tUserName = new JTextField();
		tPassword = new JTextField();
		tName = new JTextField();
		tRole = new JTextField();
		// labels
		lId = new JLabel("ID:");
		lUserName = new JLabel("User Name:");
		lPassword = new JLabel("Password:");
		lName = new JLabel("Name:");
		lRole = new JLabel("Role:");
		// buttons
		bShows = new JButton("Shows");

		// add objects to panel
		getPanel().add(lId);
		getPanel().add(lUserName);
		getPanel().add(lPassword);
		getPanel().add(lName);
		getPanel().add(lRole);
		getPanel().add(tId);
		getPanel().add(tUserName);
		getPanel().add(tPassword);
		getPanel().add(tName);
		getPanel().add(tRole);
		getPanel().add(bShows);
		
		// arrange them
		this.lId.setBounds(10,10,100,20);
		this.lUserName.setBounds(10,35,100,20);
		this.lPassword.setBounds(10,60,100,20);
		this.lName.setBounds(10,85,100,20);
		this.lRole.setBounds(10,110,100,20);
		this.tId.setBounds(100,10,100,20);
		this.tUserName.setBounds(100,35,100,20);
		this.tPassword.setBounds(100,60,100,20);
		this.tName.setBounds(100,85,100,20);
		this.tRole.setBounds(100,110,100,20);
		this.bShows.setBounds(190, 425, 150, 20);
		
		// modify a few names
		getAdd().setText("Add user");
		getUpdate().setText("Update user");
		getDelete().setText("Delete user");
		
		// add action listener
		getAdd().addActionListener(this);
		getDelete().addActionListener(this);
		getUpdate().addActionListener(this);
		this.bShows.addActionListener(this);
	}
	
	/**
	 * method to add data into the table
	 */
	public void populateTable() {
		String[] columnNames = {"id", "username", "name", "role"};
		DefaultTableModel model = new DefaultTableModel( columnNames, 0 );
		Vector<String> rowColumnNames = new Vector<String>();
		rowColumnNames.add("id");
		rowColumnNames.add("username");
		rowColumnNames.add("name");
		rowColumnNames.add("role");
		model.addRow(rowColumnNames);
		
		List<User> allUsers = um.getAllUsers(); 
		
		for (User u : allUsers)
		{
			Vector<String> newRow = new Vector<String>();
			newRow.add(u.getId() + "");
			newRow.add(u.getUsername());
			newRow.add(u.getName());
			newRow.add(u.getRole());
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
		else if (pressedButton.equals("Shows"))
		{
			AdminFrameShows f2 = new AdminFrameShows();
			getFrame().dispose();
		}
		else if (pressedButton.contains("user"))
		{
			User u = new User();
			if (pressedButton.contains("Add"))
			{
				u.setName(this.tName.getText());
				u.setPassword(this.tPassword.getText());
				u.setRole(this.tRole.getText());
				u.setUsername(this.tUserName.getText());
				
				if (!um.addUser(u))
				{
					JOptionPane.showMessageDialog(null, "Something went wrong! Could not add user :(", "Add user failed", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "User successfully added!", "Add user succeded", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			else if (pressedButton.contains("Update"))
			{
				Boolean bGoOn = false;
				try
				{
					u.setId(Integer.parseInt(this.tId.getText()));
					bGoOn = true;
				}
				catch (NumberFormatException e)
				{
					JOptionPane.showMessageDialog(null, "Please complete the id of the user with a number before updating the user!",
							"User information incomplete!", JOptionPane.ERROR_MESSAGE);
				}
				if (this.tUserName.getText().equals("") || this.tPassword.getText().equals("") || this.tName.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please don't leave any textfield empty before updating the user!",
							"User information incomplete!", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (!this.tRole.getText().equals("admin") && !this.tRole.getText().equals("user"))
				{
					JOptionPane.showMessageDialog(null, "Role should be 'admin' or 'user'!",
							"User information incomplete!", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (bGoOn)
				{
					u.setName(this.tName.getText());
					u.setPassword(this.tPassword.getText());
					u.setRole(this.tRole.getText());
					u.setUsername(this.tUserName.getText());

					if (!um.updateUser(u))
					{
						JOptionPane.showMessageDialog(null, "Something went wrong! Could not update user information :(",
								"Update user failed", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "User information successfully updated!",
								"Update user succeded", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
			else if (pressedButton.contains("Delete"))
			{
				Boolean bGoOn = false;
				try
				{
					u.setId(Integer.parseInt(this.tId.getText()));
					bGoOn = true;
				}
				catch (NumberFormatException e)
				{
					JOptionPane.showMessageDialog(null, "Please complete the id of the user with a number before deleting the user!",
							"User information incomplete!", JOptionPane.ERROR_MESSAGE);
				}
				if (bGoOn)
				{
					if (!um.deleteUser(u))
					{
						JOptionPane.showMessageDialog(null, "Something went wrong! Could not delete user :(",
								"Delete user failed", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "User successfully deleted!", "Delete user succeded", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
			populateTable();
		}
	}
}
