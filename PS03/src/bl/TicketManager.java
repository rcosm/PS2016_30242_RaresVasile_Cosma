/**
 * 
 */
package bl;

import java.util.List;

import dl.TicketDAO;
import models.Ticket;

/**
 * @author Rares-Vasile Cosma
 *
 */
public class TicketManager {

	/**
	 * constructor
	 */
	public TicketManager() {
		
	}
	
	/**
	 * checks if t exists in the DB
	 * @param t
	 * @return true if t exists, false otherwise
	 */
	public Boolean ticketExists (Ticket t)
	{
		TicketDAO tDAO = new TicketDAO();
		return tDAO.ticketExists(t);
	}
	
	/**
	 * adds in the DB the ticket
	 * @param t
	 * @return true if operation successful, false otherwise
	 */
	public Boolean sellTicket (Ticket t)
	{
		TicketDAO tDAO = new TicketDAO();
		return tDAO.sellTicket (t);
	}

	/**
	 * method returns all the tickets sold for a show
	 * @param showId - show id for which to find the tickets
	 * @return a list of tickets if successful, null otherwise
	 */
	public List<Ticket> getAllTicketsByShowId(Integer showId) {
		TicketDAO tDAO = new TicketDAO();
		return tDAO.getAllTicketsByShowId(showId);
	}

}
