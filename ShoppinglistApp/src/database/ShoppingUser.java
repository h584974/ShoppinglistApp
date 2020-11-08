package database;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import security.BCrypt;

@Entity
@Table(schema = "shoppinglistapp", name = "shoppinguser")
public class ShoppingUser {
	
	@Id
	private String username;
	
	@ManyToMany
	@JoinTable(
			name = "shoppinguser_shoppinglist",
			joinColumns = @JoinColumn(name = "username"), 
			inverseJoinColumns = @JoinColumn(name = "list_id")
	)
	List<Shoppinglist> shoppinglists;
	
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
	
	public List<Shoppinglist> getShoppinglists() {
		return this.shoppinglists;
	}
	
	@Override
	public String toString() {
		return this.username;
	}
	
}