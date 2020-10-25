package mainPage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import database.Shoppinglist;
import database.ShoppinglistDAO;
import database.UserDAO;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MainPageGet")
public class MainPageGet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ShoppinglistDAO listDAO;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = Arrays.stream(request.getCookies()).filter(c -> c.getName().equalsIgnoreCase("authenticatorCookie")).findAny().get().getValue();
		List<Shoppinglist> shoppinglists = listDAO.getShoppinglistsForUser(username);
		request.getSession().setAttribute("shoppinglists", shoppinglists);
		
	}

}