package shoppinglist;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import database.ShoppinglistDAO;

@WebServlet("/Shoppinglist")
public class Shoppinglist extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ShoppinglistDAO listDAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		database.Shoppinglist shoppinglist = null;
		
		try {
			shoppinglist = (database.Shoppinglist)request.getSession().getAttribute("shoppinglist");
		}
		catch(Throwable e) {}
		
		request.getSession().invalidate();
		
		if(shoppinglist == null) {
			request.getRequestDispatcher("WEB-INF/MainPage.jsp").forward(request, response);
		}
		else {
			request.setAttribute("shoppinglist", shoppinglist);
			request.getRequestDispatcher("WEB-INF/Shoppinglist.jsp").forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int list_id = -1;
		
		try {
			list_id = Integer.parseInt(request.getParameter("list_id"));
		}
		catch(NumberFormatException e) {
			e.printStackTrace();
		}
		
		database.Shoppinglist shoppinglist = listDAO.getShoppinglist(list_id);
		if(shoppinglist == null) {
			response.sendRedirect("MainPage");
		}
		else {
			request.getSession().setAttribute("shoppinglist", shoppinglist);
			response.sendRedirect("Shoppinglist");
		}
	}

}