package database;

import java.util.List;
import java.util.stream.Collectors;
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
	
	public List<Shoppinglist> getAllShoppinglists() {
		return em.createQuery("SELECT s FROM Shoppinglist s",Shoppinglist.class).getResultList();
	}
	
	public List<Shoppinglist> getAllUserShoppinglists(String username) {
		List<Shoppinglist> shoppinglists = getAllShoppinglists();
		List<Shoppinguser_Shoppinglist> relationList = em.createQuery("SELECT u FROM Shoppinguser_Shoppinglist u",Shoppinguser_Shoppinglist.class).getResultList();
		shoppinglists = shoppinglists.stream().filter(s -> relationList.stream().anyMatch(r -> r.getShoppinglist().getList_id() == s.getList_id())).collect(Collectors.toList());
		return shoppinglists;
	}

}