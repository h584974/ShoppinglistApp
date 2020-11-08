package mainPage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import database.Shoppinglist;
import database.ShoppinglistDAO;
import database.UserDAO;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MainPage")
public class MainPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final String ERROR_USERNAME_NOT_FOUND = "Something went went wrong - Oops";
	
	@EJB
	private ShoppinglistDAO listDAO;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = null;
		
		try {
			username = Arrays.stream(request.getCookies()).filter(c -> c.getName().equalsIgnoreCase("loggedIn")).findAny().get().getValue();
		}
		catch(NoSuchElementException e) {}
		
		if(username == null) {
			request.setAttribute("errorMessage", ERROR_USERNAME_NOT_FOUND);
			request.getRequestDispatcher("WEB-INF/Login.jsp").forward(request, response);
		}
		else {
			List<Shoppinglist> shoppinglists = listDAO.getAllUserShoppinglists(username);
			request.setAttribute("shoppinglists", shoppinglists);
			request.getRequestDispatcher("WEB-INF/MainPage.jsp").forward(request, response);
		}
	}

}