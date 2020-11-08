package database;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import security.BCrypt;

@Entity
@Table(schema = "shoppinglistapp", name = "shoppinguser")
public class ShoppingUser {
	
	@Id
	private String username;
	
	@OneToMany(mappedBy = "username")
	private List<Userlists> userlists;
	
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
		return this.encryptedPassword;
	}
	
	public List<Userlists> getUserlists() {
		return this.userlists;
	}
	
	@Override
	public String toString() {
		return this.username;
	}
	
}