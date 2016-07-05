/**
 * interface for exporter classes
 */
package bl;

/**
 * @author Rares-Vasile Cosma
 *
 */
public interface Exporter {

	/**
	 * exports the tickets to all shows into a file
	 * @return true if successful, false otherwise
	 */
	public Boolean export(Integer showId);
}
