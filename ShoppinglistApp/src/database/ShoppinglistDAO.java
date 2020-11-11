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
		List<Shoppinguser_Shoppinglist> ul = em.createQuery("SELECT u FROM Shoppinguser_Shoppinglist u",Shoppinguser_Shoppinglist.class).getResultList();
		return null;
	}

}