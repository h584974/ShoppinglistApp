package database;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserDAO {
	
	@PersistenceContext(name = "shoppingPU")
	private EntityManager em;
	
	public ShoppingUser getUser(String username) {
		return em.find(ShoppingUser.class, username);
	}
	
	public synchronized void addUser(ShoppingUser user) {
		em.persist(user);
	}
	
	public synchronized void deleteUser(ShoppingUser user) {
		ShoppingUser u = em.merge(user);
		em.remove(u);
	}

}