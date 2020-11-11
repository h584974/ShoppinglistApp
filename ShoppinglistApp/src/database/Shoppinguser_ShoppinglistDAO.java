package database;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class Shoppinguser_ShoppinglistDAO {
	
	@PersistenceContext(name = "shoppingPU")
	private EntityManager em;
	
	public synchronized void removeUserListRelation(Shoppinguser_Shoppinglist relation) {
		Shoppinguser_Shoppinglist r = em.merge(relation);
		em.remove(r);
	}
	
	public List<Shoppinguser_Shoppinglist> getAllUserListRelations() {
		return em.createQuery("SELECT u FROM Shoppinguser_Shoppinglist u",Shoppinguser_Shoppinglist.class).getResultList();
	}

}