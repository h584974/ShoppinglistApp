package database;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(schema = "ShoppinglistApp", name = "Shoppinglist")
public class Shoppinglist {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer List_ID;
	
	@OneToMany(mappedBy = "List_ID")
	private List<Userlists> userlists = new ArrayList<>();
	
	private String title;
	
	public Shoppinglist() {}
	
	public Shoppinglist(String title) {
		this.title = title;
	}
	
	public Integer getList_ID() {
		return List_ID;
	}
	
	public String getTitle() {
		return this.title;
	}

}