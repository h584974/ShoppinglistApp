package login;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import database.ShoppingUser;
import database.UserDAO;
import utils.BCrypt;

@WebServlet("/Login")
public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private final String ERROR_WRONG_USERNAME = "Wrong username";
	private final String ERROR_WRONG_PASSWORD = "Wrong password";
	
	@EJB
	private UserDAO userDAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String errorUsername = request.getParameter("errorUsername");
		String errorPassword = request.getParameter("errorPassword");
		String username = request.getParameter("username");
		request.setAttribute("errorUsername", errorUsername);
		request.setAttribute("errorPassword", errorPassword);
		request.setAttribute("username", username);
		request.getRequestDispatcher("WEB-INF/Login.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		ShoppingUser user = userDAO.getUser(username);
		
		if(user == null) {
			response.sendRedirect("Login?errorUsername=" + ERROR_WRONG_USERNAME);
		}
		else {
			if(BCrypt.checkpw(password, user.getEncryptedPassword())) {
				request.getSession().invalidate();
				Cookie loggedIn = new Cookie("loggedIn",username);
				loggedIn.setMaxAge(600);
				response.addCookie(loggedIn);
				response.sendRedirect("MainPage");
			}
			else {
				response.sendRedirect("Login?username=" + username + "&errorPassword=" + ERROR_WRONG_PASSWORD);
			}
		}
	}

}