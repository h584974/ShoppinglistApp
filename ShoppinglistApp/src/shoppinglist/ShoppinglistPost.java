package shoppinglist;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.Shoppinglist;
import database.ShoppinglistDAO;

@WebServlet("/ShoppinglistPost")
public class ShoppinglistPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ShoppinglistDAO listDAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int list_id = -1;
		
		try {
			list_id = Integer.parseInt(request.getParameter("list_id"));
		}
		catch(NumberFormatException e) {
			e.printStackTrace();
		}
		
		Shoppinglist shoppinglist = listDAO.getShoppinglist(list_id);
		if(shoppinglist == null) {
			response.sendRedirect("MainPage");
		}
		else {
			request.getSession().setAttribute("shoppinglist", shoppinglist);
			response.sendRedirect("Shoppinglist");
		}
	}

}
