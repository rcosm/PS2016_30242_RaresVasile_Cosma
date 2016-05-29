/**
 * login frame
 */
package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import bl.UserManager;
import models.User;

/**
 * @author Rares-Vasile Cosma
 *
 */
public class LoginFrame implements ActionListener {

	private JFrame f;
	private JPanel p;
	private JLabel lUserName, lPassword;
	private JTextField tUserName;
	private JPasswordField pPassword;
	private JButton bLogin, bExit;
	
	/**
	 * empty constructor that initializes the login frame
	 */
	public LoginFrame () {
		initComponents();
	}
	
	/**
	 * initializes the login interface
	 */
	private void initComponents() {
		// we need a new frame, so we first instantiate the frame.
		f = new JFrame();
		// how big
		f.setSize(300, 150);
		f.setVisible(true);
		// no resizing
		f.setResizable(false);
		// set frame to start middle screen
		f.setLocationRelativeTo(null);
		// exit application when closing the frame
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// ofc we need a panel...
		p = new JPanel();
		p.setLayout(null);
		f.getContentPane().add(p);
		
		// memory for Labels, TextFields & buttons
		this.lUserName = new JLabel("User:");
		this.lPassword = new JLabel("Password:");
		this.tUserName = new JTextField();
		this.pPassword = new JPasswordField();
		this.bLogin = new JButton("Login");
		this.bExit = new JButton("Exit");
		
		// add everything to the panel
		p.add(lUserName);
		p.add(lPassword);
		p.add(tUserName);
		p.add(pPassword);
		p.add(bLogin);
		p.add(bExit);
		
		// set positions in frame
		this.lUserName.setBounds(80, 20, 100, 20);
		this.lPassword.setBounds(50, 45, 100, 20);
		this.tUserName.setBounds(120, 20, 100, 20);
		this.pPassword.setBounds(120, 45, 100, 20);
		this.bLogin.setBounds(100, 70, 100, 20);
		this.bExit.setBounds(100, 95, 100, 20);
		
		// action listeners to buttons
		this.bExit.addActionListener(this);
		this.bLogin.addActionListener(this);
	}

	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent ev) {
		String pressedButton = ev.getActionCommand();
		
		if (pressedButton.equals("Exit"))
		{
			this.f.dispose();
		}
		else if (pressedButton.equals("Login"))
		{
			UserManager um = new UserManager();
			User u = new User();
			u.setUsername(this.tUserName.getText());
			u.setPassword(this.pPassword.getText());
			
			String userRole = um.login(u);
			
			if (userRole == null)
			{
				JOptionPane.showMessageDialog(null, "Invalid username or password", "Login failed", JOptionPane.ERROR_MESSAGE);
			}
			else if (userRole.equals("admin"))
			{
				@SuppressWarnings("unused")
				AdminFrameClients adminFrame = new AdminFrameClients();
				this.f.dispose();
			}
			else if (userRole.equals("user"))
			{
				@SuppressWarnings("unused")
				ClientFrameTickets clientFrame = new ClientFrameTickets();
				this.f.dispose();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "user or password invalid", "User Not Found!", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
