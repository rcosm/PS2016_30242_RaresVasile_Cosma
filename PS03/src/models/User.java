/**
 * class User
 */
package models;

/**
 * @author Rares-Vasile Cosma
 *
 */
public class User {

	private Integer id;
	private String name;
	private String username;
	private String password;
	private String role;
	
	public User() {
		this.name = null;
		this.username = null;
		this.password = null;
		this.role = null;
		this.id = 0;
	}
	
	public User(Integer id, String name, String username, String password, String role) {
		this.setId(id);
		this.setName(name);
		this.setUsername(username);
		this.setPassword(password);
		this.setRole(role);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

}
