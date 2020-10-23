package database;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserDAO {
	
	@PersistenceContext(name = "ShoppingPU")
	private EntityManager em;
	
	public ShoppingUser getUser(String username) {
		return em.find(ShoppingUser.class, username);
	}
	
	public void addUser(ShoppingUser user) {
		ShoppingUser newUser = em.merge(user);
		em.persist(newUser);
	}

}