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

	private ShowDAO sDAO;
	
	/**
	 * constructor
	 */
	public ShowManager() {
		sDAO = new ShowDAO();
	}
	
	/**
	 * method to get all the users
	 * @return a list with all users
	 */
	public List<Show> getAllShows()
	{
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
		return sDAO.addShow(showToAdd);
	}
	
	/**
	 * encrypts the password of the user then it adds the user to the DB
	 * @param showToUpdate
	 * @return true if successful, otherwise false
	 */
	public Boolean updateShow (Show showToUpdate)
	{
		return sDAO.updateShow(showToUpdate);
	}
	
	/**
	 * deletes a user from the DB
	 * @param showToDelete
	 * @return true if successful, otherwise false
	 */
	public Boolean deleteShow (Show showToDelete)
	{
		return sDAO.deleteShow(showToDelete);
	}
	
	
	public Show getShowInfo (Integer id)
	{
		return sDAO.getShowInfo(id);
	}

}
