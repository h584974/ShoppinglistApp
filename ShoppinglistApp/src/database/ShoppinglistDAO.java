package database;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ShoppinglistDAO {
	
	@PersistenceContext(name = "shoppingPU")
	private EntityManager em;
	
	public Shoppinglist getShoppinglist(Integer list_id) {
		return em.find(Shoppinglist.class, list_id);
	}
	
	public void removeShoppinglist(Shoppinglist shoppinglist) {
		Shoppinglist list = em.merge(shoppinglist);
		em.remove(list);
	}
	
	public List<Shoppinglist> getAllUserShoppinglists(String username) {
		return em.createQuery("SELECT s FROM Shoppinglist s",Shoppinglist.class).getResultList();
	}

}