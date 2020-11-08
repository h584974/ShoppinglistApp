package database;

import java.util.Arrays;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ShoppinglistDAO {
	
	@EJB
	private UserDAO userDAO;
	
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
		List<Userlists> userlists = null;
		
		try {
			userlists = userDAO.getUser(username).getUserlists();
		}
		catch(Throwable e) {}
		
		if(userlists == null || userlists.isEmpty()) {
			return null;
		}
		else {
			List<Shoppinglist> shoppinglists = Arrays.asList(userlists.get(0).getShoppinglist());
			userlists.remove(0);
			userlists.forEach(u -> shoppinglists.add(u.getShoppinglist()));
			return shoppinglists;
		}
	}

}