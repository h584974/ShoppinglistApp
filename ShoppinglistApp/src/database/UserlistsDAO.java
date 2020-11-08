package database;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserlistsDAO {
	
	@PersistenceContext(name = "shoppingPU")
	private EntityManager em;
	
	public List<Userlists> getUserlists(String username) {
		return em.createQuery("SELECT u FROM Userlists u WHERE u.username = '" + username + "'", Userlists.class).getResultList();
	}

}
