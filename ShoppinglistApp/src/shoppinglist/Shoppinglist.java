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
		request.setCharacterEncoding("UTF-8");
		int list_id = Integer.parseInt(request.getParameter("list_id"));
		database.Shoppinglist shoppinglist = listDAO.getShoppinglist(list_id);
		
		if(shoppinglist == null) {
			response.sendRedirect("MainPage");
		}
		else {
			request.setAttribute("shoppinglist", shoppinglist);
			request.getRequestDispatcher("WEB-INF/Shoppinglist.jsp").forward(request, response);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int list_id = -1;
		
		try {
			list_id = Integer.parseInt(request.getParameter("list_id"));
		}
		catch(NumberFormatException e) {
			e.printStackTrace();
		}
		
		if(list_id < 0) {
			response.sendRedirect("MainPage");
		}
		else {
			response.sendRedirect("Shoppinglist?list_id=" + list_id);
		}
	}

}