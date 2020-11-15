package database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "ShoppinglistApp", name = "Shoppinglist")
public class Shoppinglist {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer list_id;
	
	private String title;
	
	public Shoppinglist() {}
	
	public Shoppinglist(String title) {
		this.title = title;
	}
	
	public Integer getList_id() {
		return list_id;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public boolean equals(Shoppinglist s2) {
		return this.list_id == s2.list_id;
	}

}