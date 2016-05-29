/**
 * Data Access Operations for Tickets
 */
package dl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Ticket;

/**
 * @author Rares-Vasile Cosma
 *
 */
public class TicketDAO {

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
	public TicketDAO() {
		
	}
	
	/**
	 * checks if t exists in the DB
	 * @param t - ticket whose existence is to be checked
	 * @return true if t is in DB, false otherwise
	 */
	public Boolean ticketExists(Ticket t)
	{
		Boolean ok = false;
		Ticket newTicket = null;
		try
		{
			Class.forName(JDBC_DRIVER);
			
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
			sql = "SELECT * FROM tickets WHERE idShow = ? AND row = ? AND nr = ?";
			preparedStmt = conn.prepareStatement(sql);
			preparedStmt.setInt(1, t.getShowId());
			preparedStmt.setInt(2, t.getRow());
			preparedStmt.setInt(3, t.getNr());
			
			rs = preparedStmt.executeQuery();
			
			while(rs.next()){
				//Retrieve by column name
				newTicket = new Ticket();
				newTicket.setId(rs.getInt("id"));
				newTicket.setShowId(rs.getInt("idShow"));
				newTicket.setRow(rs.getInt("row"));
				newTicket.setNr(rs.getInt("nr"));
				ok = true;
			}
			
			preparedStmt.close();
			conn.close();
		}
		catch (Exception e)
		{
			newTicket = null;
		}
		
		return ok;
	}
	
	/**
	 * adds in the DB the ticket
	 * @param t - contains ticket info except the id
	 * @return true if operation successful, false otherwise
	 */
	public Boolean sellTicket (Ticket t)
	{
		Boolean ok = false;
		
		try{
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
			sql = "INSERT INTO tickets (idShow, row, nr) VALUES (?, ?, ?)";
			// prepare the statement
			preparedStmt = conn.prepareStatement(sql);
			preparedStmt.setInt(1, t.getShowId());
			preparedStmt.setInt(2, t.getRow());
			preparedStmt.setInt(3, t.getNr());
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
	 * returns a list o show tickets
	 * @param showId - id of the show to select only concrete elements
	 * @return a list of tickets if successful, null otherwise
	 */
	public List<Ticket> getAllTicketsByShowId(Integer showId) {
		List<Ticket> showTickets = new ArrayList<Ticket>();
		
		try
		{
			Class.forName(JDBC_DRIVER);
			
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
			sql = "SELECT * FROM tickets WHERE idShow = ?";
			preparedStmt = conn.prepareStatement(sql);
			preparedStmt.setInt(1, showId);
			
			rs = preparedStmt.executeQuery();
			
			while(rs.next()){
				//Retrieve by column name
				Ticket newTicket = new Ticket();
				newTicket.setId(rs.getInt("id"));
				newTicket.setShowId(rs.getInt("idShow"));
				newTicket.setRow(rs.getInt("row"));
				newTicket.setNr(rs.getInt("nr"));
				
				showTickets.add(newTicket);
			}
			
			preparedStmt.close();
			conn.close();
		}
		catch (Exception e)
		{
			showTickets = null;
		}
		
		return showTickets;
	}

}
