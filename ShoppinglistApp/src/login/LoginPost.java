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

@WebServlet("/LoginPost")
public class LoginPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private UserDAO userDAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(login == null || login.isBlank()) {
			if(password.length() < 8 || password.length() > 64) {
				request.getSession().setAttribute("message", "Password must be between 8 and 64 characters long");
			}
			else if(username.length() < 3 || username.length() > 20) {
				request.getSession().setAttribute("message", "Username must be between 3 and 20 characters long");
			}
			else {
				ShoppingUser user = new ShoppingUser(username,password);
				userDAO.addUser(user);
				if(userDAO.getUser(username) == null) {
					request.getSession().setAttribute("message", "Failed to create account");
				}
				else {
					request.getSession().setAttribute("message", "Account has been created");
				}
			}
			response.sendRedirect("Login");
		}
		else {
			if(username == null || username.isBlank() || password == null || password.isBlank()) {
				request.getSession().setAttribute("message", "Username and Password must be submitted");
				response.sendRedirect("Login");
			}
			else {
				boolean loginSuccessful = LoginAuthenticator.authenticateLogin(username, password);
				if(loginSuccessful) {
					Cookie authenticatorCookie = new Cookie("authenticatorCookie",username);
					authenticatorCookie.setSecure(true);
					authenticatorCookie.setMaxAge(600);
					request.getSession().setMaxInactiveInterval(600);
					response.addCookie(authenticatorCookie);
					response.sendRedirect("MainPageGet");
				}
				else {
					request.getSession().setAttribute("message", "Username or Password were not correct, please try again");
					response.sendRedirect("Login");
				}
			}
		}
	}

}