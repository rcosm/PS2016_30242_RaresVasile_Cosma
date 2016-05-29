/**
 * Manager class for all shows
 */
package bl;

import java.util.List;

import models.Show;
import dl.ShowDAO;

/**
 * @author Rares-Vasile Cosma
 *
 */
public class ShowManager {

	/**
	 * constructor
	 */
	public ShowManager() {
		
	}
	
	/**
	 * method to get all the users
	 * @return a list with all users
	 */
	public List<Show> getAllShows()
	{
		ShowDAO sDAO = new ShowDAO();
		List<Show> allShows = null;
		allShows = sDAO.getAllShows();
		return allShows;
	}
	
	/**
	 * encrypts the password of the user then it adds the user to the DB
	 * @param showToAdd - user
	 * @return true if successful, otherwise false
	 */
	public Boolean addShow (Show showToAdd)
	{
		ShowDAO sDAO = new ShowDAO();
		return sDAO.addShow(showToAdd);
	}
	
	/**
	 * encrypts the password of the user then it adds the user to the DB
	 * @param showToUpdate
	 * @return true if successful, otherwise false
	 */
	public Boolean updateShow (Show showToUpdate)
	{
		ShowDAO sDAO = new ShowDAO();
		return sDAO.updateShow(showToUpdate);
	}
	
	/**
	 * deletes a user from the DB
	 * @param showToDelete
	 * @return true if successful, otherwise false
	 */
	public Boolean deleteShow (Show showToDelete)
	{
		ShowDAO sDAO = new ShowDAO();
		return sDAO.deleteShow(showToDelete);
	}
	
	
	public Show getShowInfo (Integer id)
	{
		ShowDAO sDAO = new ShowDAO();
		return sDAO.getShowInfo(id);
	}

}
