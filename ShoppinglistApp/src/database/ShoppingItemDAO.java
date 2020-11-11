package database;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ShoppingItemDAO {
	
	@PersistenceContext(name = "shoppingPU")
	private EntityManager em;
	
	public synchronized void removeItem(ShoppingItem item) {
		ShoppingItem i = em.merge(item);
		em.remove(i);
	}
	
	public List<ShoppingItem> getAllItems() {
		return em.createQuery("SELECT i FROM shoppingitem i", ShoppingItem.class).getResultList();
	}
	
}