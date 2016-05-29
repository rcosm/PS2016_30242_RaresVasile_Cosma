/**
 * class Show
 */
package models;

import java.util.Date;

/**
 * @author Rares-Vasile Cosma
 *
 */
public class Show {

	private Integer id;
	private String title;
	private String administration;
	private String distribution;
	private Date premiere;
	private Integer nrOfTickets;
	
	public Show() {
		
	}
	
	public Show(Integer id, String title, String administration, String distribution, Date premiere, Integer nrOfTickets) {
		this.setId(id);
		this.setTitle(title);
		this.setAdministration(administration);
		this.setDistribution(distribution);
		this.setPremiere(premiere);
		this.setNrOfTickets(nrOfTickets);
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the administration
	 */
	public String getAdministration() {
		return administration;
	}

	/**
	 * @param administration the administration to set
	 */
	public void setAdministration(String administration) {
		this.administration = administration;
	}

	/**
	 * @return the distribution
	 */
	public String getDistribution() {
		return distribution;
	}

	/**
	 * @param distribution the distribution to set
	 */
	public void setDistribution(String distribution) {
		this.distribution = distribution;
	}

	/**
	 * @return the premiere
	 */
	public Date getPremiere() {
		return premiere;
	}

	/**
	 * @param premiere the premiere to set
	 */
	public void setPremiere(Date premiere) {
		this.premiere = premiere;
	}

	/**
	 * @return the nrOfTickets
	 */
	public Integer getNrOfTickets() {
		return nrOfTickets;
	}

	/**
	 * @param nrOfTickets the nrOfTickets to set
	 */
	public void setNrOfTickets(Integer nrOfTickets) {
		this.nrOfTickets = nrOfTickets;
	}
}
