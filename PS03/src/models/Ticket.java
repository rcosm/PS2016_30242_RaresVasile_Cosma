/**
 * class Ticket
 */
package models;

/**
 * @author Rares-Vasile Cosma
 *
 */
public class Ticket {

	private Integer id;
	private Integer showId;
	private Integer row;
	private Integer nr;
	
	public Ticket() {
		
	}
	
	public Ticket(Integer showId, Integer row, Integer nr) {
		this.setShowId(showId);
		this.setRow(row);
		this.setNr(nr);
	}

	/**
	 * @return the showId
	 */
	public Integer getShowId() {
		return showId;
	}

	/**
	 * @param showId the showId to set
	 */
	public void setShowId(Integer showId) {
		this.showId = showId;
	}

	/**
	 * @return the row
	 */
	public Integer getRow() {
		return row;
	}

	/**
	 * @param row the row to set
	 */
	public void setRow(Integer row) {
		this.row = row;
	}

	/**
	 * @return the nr
	 */
	public Integer getNr() {
		return nr;
	}

	/**
	 * @param nr the nr to set
	 */
	public void setNr(Integer nr) {
		this.nr = nr;
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

}
