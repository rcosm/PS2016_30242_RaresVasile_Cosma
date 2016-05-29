/**
 * Data Access Operations for Show class
 */
package dl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import models.Show;

/**
 * @author Rares-Vasile Cosma
 *
 */
public class ShowDAO {

	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost/ps01";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "root";
	
	// stuff
	Connection conn;
	PreparedStatement preparedStmt;
	String sql;
	Statement stmt;
	ResultSet rs;
	
	/**
	 * constructor
	 */
	public ShowDAO() {
		
	}
	
	/**
	 * adds a new show
	 * @param showToAdd - id should not be provided, id is auto-incremental 
	 * @return true if successful, false otherwise
	 */
	public Boolean addShow (Show showToAdd)
	{
		Boolean ok = false;
		
		Date sqlDate = new Date(showToAdd.getPremiere().getTime());
		
		try{
			// use jdbc
			Class.forName(JDBC_DRIVER);
			// connect to db
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			// what do we wanna do?
			sql = "INSERT INTO shows (title, administration, distribution, premiere, nrOfTickets) VALUES (?, ?, ?, ?, ?)";
			// prepare the statement
			preparedStmt = conn.prepareStatement(sql);
			preparedStmt.setString(1, showToAdd.getTitle());
			preparedStmt.setString(2, showToAdd.getAdministration());
			preparedStmt.setString(3, showToAdd.getDistribution());
			preparedStmt.setDate  (4, sqlDate);
			preparedStmt.setInt   (5, showToAdd.getNrOfTickets());
			// execute the SQL statement
			preparedStmt.execute();
			
			preparedStmt.close();
			conn.close();
			
			ok = true;
		}
		catch (Exception e)
		{
			
		}
		
		return ok;
	}
	
	/**
	 * deletes a show by it's id
	 * @param showToDelete - the only needed param here is the id of the show
	 * @return true if successful, false otherwise
	 */
	public Boolean deleteShow (Show showToDelete)
	{
		Boolean ok = false;
		
		try
		{
			// use jdbc
			Class.forName(JDBC_DRIVER);
			// connect to db
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			// what do we wanna do?
			sql = "DELETE FROM shows WHERE id = ?";
			// prepare the statement
			preparedStmt = conn.prepareStatement(sql);
			preparedStmt.setInt(1, showToDelete.getId());
			// execute
			preparedStmt.execute();
			
			preparedStmt.close();
			conn.close();
			
			ok = true;
		}
		catch (Exception e)
		{
			
		}
		
		return ok;
	}
	
	/**
	 * updates a show by id
	 * @param showToUpdate - should contain an existing id
	 * @return true if successful, false otherwise
	 */
	public Boolean updateShow (Show showToUpdate)
	{
		Boolean ok = false;
		
		Date sqlDate = new Date(showToUpdate.getPremiere().getTime());
		
		try
		{
			// use jdbc
			Class.forName(JDBC_DRIVER);
			// connect to db
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			// what do we wanna do?
			sql = "UPDATE shows SET title = ?, administration = ?, distribution = ?, premiere = ?, nrOfTickets = ? WHERE id = ?";
			// prepare the statement
			preparedStmt = conn.prepareStatement(sql);
			preparedStmt.setString(1, showToUpdate.getTitle());
			preparedStmt.setString(2, showToUpdate.getAdministration());
			preparedStmt.setString(3, showToUpdate.getDistribution());
			preparedStmt.setDate  (4, sqlDate);
			preparedStmt.setInt   (5, showToUpdate.getNrOfTickets());
			preparedStmt.setInt(6, showToUpdate.getId());
			// execute
			preparedStmt.execute();
			
			preparedStmt.close();
			conn.close();
			
			ok = true;
		}
		catch (Exception e)
		{
			
		}
		
		return ok;
	}
	
	/**
	 * gets all the shows
	 * @return a list of shows
	 */
	public List<Show> getAllShows ()
	{
		List<Show> allShows = new ArrayList<Show>();
		
		try
		{
			Class.forName(JDBC_DRIVER);
			
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
			stmt = conn.createStatement();
			sql = "SELECT * FROM shows";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				//Retrieve by column name
				Show s = new Show();
				s.setId(rs.getInt("id"));
		        s.setTitle(rs.getString("title"));
		        s.setAdministration(rs.getString("administration"));
		        s.setDistribution(rs.getString("distribution"));
		        s.setNrOfTickets(rs.getInt("nrOfTickets"));
		        s.setPremiere(new java.util.Date(rs.getDate("premiere").getTime()));
		        allShows.add(s);
			}
			
			rs.close();
		    stmt.close();
		    conn.close();
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "An error occured while trying to execute the SQL query!",
					"ERROR", JOptionPane.ERROR_MESSAGE);
			allShows = null;
		}
		
		return allShows;
	}
	
	/**
	 * returns a show with all the filled info
	 * @param id - unique id of the show
	 * @return the show filled with all the info
	 */
	public Show getShowInfo(Integer id)
	{
		Show s = null;
		
		try
		{
			Class.forName(JDBC_DRIVER);
			
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
			sql = "SELECT * FROM shows WHERE id = ?";
			preparedStmt = conn.prepareStatement(sql);
			preparedStmt.setInt(1, id);
			
			rs = preparedStmt.executeQuery();
			
			while(rs.next()){
				//Retrieve by column name
				s = new Show();
				s.setId(rs.getInt("id"));
		        s.setTitle(rs.getString("title"));
		        s.setAdministration(rs.getString("administration"));
		        s.setDistribution(rs.getString("distribution"));
		        s.setNrOfTickets(rs.getInt("nrOfTickets"));
		        s.setPremiere(new java.util.Date(rs.getDate("premiere").getTime()));
			}
			
			preparedStmt.close();
			conn.close();
		}
		catch (Exception e)
		{
			s = null;
		}
		
		return s;
	}

}
