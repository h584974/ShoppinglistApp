package database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "ShoppinglistApp", name = "Shoppinglist")
public class Shoppinglist {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer List_ID;
	
	public Shoppinglist() {}
	
	public Integer getList_ID() {
		return List_ID;
	}

}