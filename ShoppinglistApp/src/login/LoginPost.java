package login;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import database.BCrypt;
import database.ShoppingUser;
import database.UserDAO;

@WebServlet("/LoginPost")
public class LoginPost extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private final String ERROR_WRONG_USERNAME = "Wrong username";
	private final String ERROR_WRONG_PASSWORD = "Wrong password";
	
	@EJB
	private UserDAO userDAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		ShoppingUser user = userDAO.getUser(username);
		
		if(user == null) {
			request.getSession().setAttribute("errorMessage", ERROR_WRONG_USERNAME);
			response.sendRedirect("Login");
		}
		else {
			if(BCrypt.checkpw(password, user.getEncryptedPassword())) {
				request.getSession().invalidate();
				Cookie loggedIn = new Cookie("loggedIn",username);
				loggedIn.setMaxAge(600);
				response.sendRedirect("MainPage");
			}
			else {
				request.getSession().setAttribute("username", username);
				request.getSession().setAttribute("errorMessage", ERROR_WRONG_PASSWORD);
				response.sendRedirect("Login");
			}
		}
	}

}