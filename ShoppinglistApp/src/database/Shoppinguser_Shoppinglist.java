package database;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "shoppinglistapp", name = "shoppinguser_shoppinglist")
public class Shoppinguser_Shoppinglist {
	
	@Id
    @ManyToOne
    @JoinColumn(name = "username")
	private ShoppingUser user;
	
	@Id
	@ManyToOne
    @JoinColumn(name = "list_id")
	private Shoppinglist shoppinglist;
	
	public Shoppinguser_Shoppinglist() {}
	
	public Shoppinguser_Shoppinglist(ShoppingUser user, Shoppinglist shoppinglist) {
		this.user = user;
		this.shoppinglist = shoppinglist;
	}

	public ShoppingUser getUser() {
		return user;
	}

	public Shoppinglist getShoppinglist() {
		return shoppinglist;
	}

}