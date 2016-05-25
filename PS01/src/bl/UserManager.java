/**
 * Manager class for all users
 */
package bl;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import dl.UserDAO;
import models.User;

/**
 * @author Rares-Vasile Cosma
 *
 */
public class UserManager {

	private UserDAO uDAO;
	private Cipher cipher;
	private static byte[] key = new byte[] {
            (byte) 0x02, (byte) 0xb2, (byte) 0xc4, (byte) 0x9e,
            (byte) 0xf9, (byte) 0x44, (byte) 0x99, (byte) 0xc9,
            (byte) 0x80, (byte) 0x65, (byte) 0xcd, (byte) 0x8f,
            (byte) 0x69, (byte) 0x2b, (byte) 0x74, (byte) 0x34};
	
	private static byte[] iv = new byte[] {
            (byte) 0x69, (byte) 0x2b, (byte) 0x74, (byte) 0x34,
            (byte) 0x02, (byte) 0xb2, (byte) 0xc4, (byte) 0x9e,
            (byte) 0xf9, (byte) 0x44, (byte) 0x99, (byte) 0xc9,
            (byte) 0x80, (byte) 0x65, (byte) 0xcd, (byte) 0x8f};
	
	/**
	 * constructor
	 */
	public UserManager() {
		uDAO = new UserDAO();
		try {
            
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "AES"), new IvParameterSpec(iv));
		} catch (NoSuchAlgorithmException e) {
			System.out.println("NoSuchAlgorithmException");
		} catch (NoSuchPaddingException e) {
			System.out.println("NoSuchPaddingException");
		} catch (InvalidKeyException e) {
			System.out.println("InvalidKeyException");
		} catch (InvalidAlgorithmParameterException e) {
			System.out.println("InvalidAlgorithmParameterException");
		}
	}
	
	/**
	 * encrypts a string
	 * @param str string to encrypt
	 * @return encrypted string
	 */
	public String encrypt(String str) {
        try {
            // Encode the string into bytes using utf-8
            byte[] utf8 = str.getBytes("UTF8");
            
            // Encrypt
			byte[] enc = cipher.doFinal(utf8);

            // Encode bytes to base64 to get a string
			String a = new sun.misc.BASE64Encoder().encode(enc);
            return a;
        } catch (javax.crypto.BadPaddingException e) {
        	System.out.println("BadPaddingException");
        } catch (IllegalBlockSizeException e) {
        	System.out.println("IllegalBlockSizeException");
        } catch (UnsupportedEncodingException e) {
        	System.out.println("UnsupportedEncodingException");
		}
        return null;
    }

    /*public String decrypt(String str) {
        try {
            // Decode base64 to get bytes
            byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);

            // Decrypt
            byte[] utf8 = cipher.doFinal(dec);

            // Decode using utf-8
            String a = new String(utf8, "UTF8"); 
            return a;
        } catch (javax.crypto.BadPaddingException e) {
        	System.out.println("BadPaddingException");
        } catch (IllegalBlockSizeException e) {
        	System.out.println("IllegalBlockSizeException");
        } catch (UnsupportedEncodingException e) {
        	System.out.println("UnsupportedEncodingException");
        } catch (java.io.IOException e) {
        	System.out.println("IOException");
		}
        return null;
    }*/
	
	/**
	 * login method
	 * @param u - represents the user credentials for the login
	 * @return the role of the user if the user exists or null if the user doesn't exist
	 */
	public String login(User u)
	{
		String a = this.encrypt(u.getPassword());
		u.setPassword(a);
		return uDAO.getUserByUserNameAndPass(u).getRole();
	}
	
	/**
	 * method to get all the users
	 * @return a list with all users
	 */
	public List<User> getAllUsers()
	{
		List<User> allUsers = null;
		allUsers = uDAO.getAllUsers();
		return allUsers;
	}
	
	/**
	 * encrypts the password of the user then it adds the user to the DB
	 * @param userToAdd - user
	 * @return true if successful, otherwise false
	 */
	public Boolean addUser (User userToAdd)
	{
		userToAdd.setPassword(this.encrypt(userToAdd.getPassword()));
		return uDAO.addUser(userToAdd);
	}
	
	/**
	 * encrypts the password of the user then it adds the user to the DB
	 * @param userToUpdate
	 * @return true if successful, otherwise false
	 */
	public Boolean updateUser (User userToUpdate)
	{
		userToUpdate.setPassword(this.encrypt(userToUpdate.getPassword()));
		return uDAO.updateUser(userToUpdate);
	}
	
	/**
	 * deletes a user from the DB
	 * @param userToDelete
	 * @return true if successful, otherwise false
	 */
	public Boolean deleteUser (User userToDelete)
	{
		return uDAO.deleteUser(userToDelete);
	}

}
