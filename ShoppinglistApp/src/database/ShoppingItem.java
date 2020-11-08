package database;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "ShoppinglistApp", name = "ShoppingItem")
public class ShoppingItem {
	
	@Id
	private String item_name;
	
	@Id 
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="List_ID")
	private Shoppinglist shoppinglist;
	
	public ShoppingItem() {}
	
	public ShoppingItem(String itemName, Shoppinglist shoppinglist) {
		this.item_name = itemName;
		this.shoppinglist = shoppinglist;
	}

}