package database;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ShoppinglistDAO {
	
	@PersistenceContext(name = "shoppingPU")
	private EntityManager em;
	
	@EJB
	private ShoppingItemDAO itemDAO;
	
	@EJB
	private Shoppinguser_ShoppinglistDAO user_listDAO;
	
	public Shoppinglist getShoppinglist(Integer list_id) {
		return em.find(Shoppinglist.class, list_id);
	}
	
	public synchronized void removeShoppinglist(Shoppinglist shoppinglist) {
		int list_id = shoppinglist.getList_id();
		
		List<ShoppingItem> itemlist = itemDAO.getAllItems();
		List<Shoppinguser_Shoppinglist> relationList = user_listDAO.getAllUserListRelations();
		itemlist.stream().filter(i -> i.getShoppinglist().getList_id() == list_id).forEach(i -> itemDAO.removeItem(i));
		relationList.stream().filter(r -> r.getShoppinglist().getList_id() == list_id).forEach(r -> user_listDAO.removeUserListRelation(r));
		
		Shoppinglist list = em.merge(shoppinglist);
		em.remove(list);
	}
	
	public List<Shoppinglist> getAllShoppinglists() {
		return em.createQuery("SELECT s FROM Shoppinglist s",Shoppinglist.class).getResultList();
	}
	
	public List<Shoppinglist> getAllUserShoppinglists(String username) {
		List<Shoppinglist> shoppinglists = getAllShoppinglists();
		List<Shoppinguser_Shoppinglist> relationList = user_listDAO.getAllUserListRelations();
		shoppinglists = shoppinglists.stream().filter(s -> relationList.stream().filter(r -> r.getUser().getUsername().equals(username)).anyMatch(r -> r.getShoppinglist().equals(s))).collect(Collectors.toList());
		return shoppinglists;
	}

}