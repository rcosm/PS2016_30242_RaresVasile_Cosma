/**
 * Data Access Operations for User class
 */
package dl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.User;

/**
 * @author Rares-Vasile Cosma
 *
 */
public class UserDAO {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/ps01";

	//  Database credentials
	static final String USER = "root";
	static final String PASS = "root";
	   
	public UserDAO() {
		
	}
	
	/**
	 * method used to get a user by user credentials: username and password
	 * @param u contains user credentials
	 * @return the user queried using the user's credentials
	 */
	public User getUserByUserNameAndPass(User u){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql;
		try {
			Class.forName(JDBC_DRIVER);
			
			// System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
			// System.out.println("Creating statement...");
			stmt = conn.createStatement();
			sql = "SELECT * FROM users WHERE `username` = '" + u.getUsername() + "' AND `password` = '" + u.getPassword() + "'";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				//Retrieve by column name
				u.setId(rs.getInt("id"));
		        u.setName(rs.getString("name"));
		        u.setUsername(rs.getString("username"));
		        u.setPassword(rs.getString("password"));
		        u.setRole(rs.getString("role"));
			}
			
			rs.close();
		    stmt.close();
		    conn.close();
		    
		} catch (ClassNotFoundException e) {
			System.out.println("Could not load " + JDBC_DRIVER + "!");
		} catch (SQLException e) {
			System.out.println("Could not execute sql query!");
		}
		return u;
	}
	
	/**
	 * gets all existing users
	 * @return a list with all users
	 */
	public List<User> getAllUsers() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql;
		List<User> allUsers = new ArrayList<User>();
		try {
			Class.forName(JDBC_DRIVER);
			
			// System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
			// System.out.println("Creating statement...");
			stmt = conn.createStatement();
			sql = "SELECT * FROM users";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				//Retrieve by column name
				User u = new User();
				u.setId(rs.getInt("id"));
		        u.setName(rs.getString("name"));
		        u.setUsername(rs.getString("username"));
		        u.setRole(rs.getString("role"));
		        allUsers.add(u);
			}
			
			rs.close();
		    stmt.close();
		    conn.close();
		    
		} catch (ClassNotFoundException e) {
			System.out.println("Could not load " + JDBC_DRIVER + "!");
			allUsers = null;
		} catch (SQLException e) {
			System.out.println("Could not execute sql query!");
			allUsers = null;
		}
		return allUsers;
	}
	
	/**
	 * adds a user to the DB
	 * @param userToAdd
	 * @return true if successful, otherwise false 
	 */
	public Boolean addUser(User userToAdd)
	{
		Boolean ok = false;
		Connection conn = null;
		PreparedStatement preparedStmt = null;
		
		try
		{
			Class.forName(JDBC_DRIVER);
			
			// System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
			String query = "INSERT INTO users (name, username, password, role) VALUES (?, ?, ?, ?)";
			 
			// create the mysql insert preparedstatement
			preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString (1, userToAdd.getName());
			preparedStmt.setString (2, userToAdd.getUsername());
			preparedStmt.setString (3, userToAdd.getPassword());
			preparedStmt.setString (4, userToAdd.getRole());
	 
			// execute the preparedstatement
			preparedStmt.execute();
			
			conn.close();
			ok = true;
		}
		catch (Exception e)
		{
			
		}
		
		return ok;
	}
	
	/**
	 * updates a user information based on the id of the user
	 * @param userToUpdate
	 * @return true if successful, otherwise false
	 */
	public Boolean updateUser (User userToUpdate)
	{
		Boolean ok = false;
		Connection conn = null;
		PreparedStatement preparedStmt = null;
		
		try
		{
			Class.forName(JDBC_DRIVER);
			
			// System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
			String query = "UPDATE users SET name = ?, username = ?, password = ?, role = ? WHERE id = ?";
			
			preparedStmt = conn.prepareStatement(query);
			preparedStmt.setString (1, userToUpdate.getName());
			preparedStmt.setString (2, userToUpdate.getUsername());
			preparedStmt.setString (3, userToUpdate.getPassword());
			preparedStmt.setString (4, userToUpdate.getRole());
			preparedStmt.setInt	   (5, userToUpdate.getId());
			
			// execute the preparedstatement
			ok = preparedStmt.execute();
			
			conn.close();
			ok = true;
		}
		catch (Exception e)
		{
			
		}
		
		return ok;
	}
	
	/**
	 * deletes a user to the DB
	 * @param userToDelete
	 * @return true if successful, otherwise false
	 */
	public Boolean deleteUser (User userToDelete)
	{
		Boolean ok = false;
		Connection conn = null;
		PreparedStatement preparedStmt = null;
		
		try
		{
			Class.forName(JDBC_DRIVER);
			
			// System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
			String query = "DELETE FROM users WHERE id = ?";
			
			preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt (1, userToDelete.getId());
			
			// execute the preparedstatement
			preparedStmt.execute();
			
			conn.close();
			ok = true;
		}
		catch (Exception e)
		{
			
		}
		
		return ok;
	}

}
