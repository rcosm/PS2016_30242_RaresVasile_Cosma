/**
 * 
 */
package bl;

import dl.TicketDAO;
import models.Ticket;

/**
 * @author Rares-Vasile Cosma
 *
 */
public class TicketManager {

	private TicketDAO tDAO;
	/**
	 * constructor
	 */
	public TicketManager() {
		tDAO = new TicketDAO();
	}
	
	/**
	 * checks if t exists in the DB
	 * @param t
	 * @return true if t exists, false otherwise
	 */
	public Boolean ticketExists (Ticket t)
	{
		return tDAO.ticketExists(t);
	}
	
	/**
	 * adds in the DB the ticket
	 * @param t
	 * @return true if operation successful, false otherwise
	 */
	public Boolean sellTicket (Ticket t)
	{
		return tDAO.sellTicket (t);
	}

}
