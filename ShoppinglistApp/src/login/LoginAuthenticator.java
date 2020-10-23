package login;

import javax.ejb.EJB;
import database.BCrypt;
import database.ShoppingUser;
import database.UserDAO;

public class LoginAuthenticator {
	
	@EJB
	private static UserDAO userDAO;
	
	public static boolean authenticateLogin(String username, String password) {
		if(username == null || username.isBlank() || password == null || password.isBlank()) {
			return false;
		}
		else {
			ShoppingUser user = userDAO.getUser(username);
			if(user != null) {
				return BCrypt.checkpw(password, user.getEncryptedPassword());
			}
			else {
				return false;
			}
		}
	}

}