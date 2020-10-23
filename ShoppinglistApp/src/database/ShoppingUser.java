package database;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "shoppinglistapp", name = "shoppinguser")
public class ShoppingUser {
	
	@Id
	private String username;
	
	private String encryptedPassword;
	
	public ShoppingUser() {}
	
	public ShoppingUser(String username, String password) {
		this.username = username;
		this.encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	
	@Override
	public String toString() {
		return this.username;
	}
	
}