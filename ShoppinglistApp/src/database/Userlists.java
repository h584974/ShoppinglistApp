package database;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "ShoppinglistApp", name = "Userlists")
public class Userlists {
	
	@Id
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "username")
    private ShoppingUser user;
	
	@Id
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "list_id")
    private Shoppinglist shoppinglist;
	
	public Userlists() {}
	
	public Shoppinglist getShoppinglist() {
		return this.shoppinglist;
	}
	
}