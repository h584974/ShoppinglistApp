package database;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "ShoppinglistApp", name = "ShoppingUser")
public class ShoppingUser {
	
	@Id
	private String username;
	
	private String encryptedPassword;
	
	public ShoppingUser(String username, String password) {
		this.username = username;
		this.encryptedPassword = PasswordHasher.encryptPassword(password);
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