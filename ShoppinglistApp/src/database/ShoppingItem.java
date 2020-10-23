package database;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "ShoppinglistApp", name = "ShoppingItem")
public class ShoppingItem {
	
	@Id
	private String itemName;
	
	@Id @ManyToOne @JoinColumn(name="List_ID")
	private Shoppinglist shoppinglist;
	
	public ShoppingItem() {}
	
	public ShoppingItem(String itemName, Shoppinglist shoppinglist) {
		this.itemName = itemName;
		this.shoppinglist = shoppinglist;
	}

}