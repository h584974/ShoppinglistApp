package create;

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
import security.Validator;

@WebServlet("/CreateAccount")
public class CreateAccount extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private final Validator validator = new Validator();
	private final String ERROR_USERNAME_TAKEN = "Username is taken";
	private final String ERROR_INVALID_USERNAME = "Invalid Username";
	private final String ERROR_INVALID_PASSWORD = "Invalid Password";
	private final String ERROR_PASSWORDS_DO_NOT_MATCH = "Passwords do not match";
	
	@EJB
	private UserDAO userDAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String errorUsername = request.getParameter("errorUsername");
		String errorPassword = request.getParameter("errorPassword");
		String errorPasswordRepeat = request.getParameter("errorPasswordRepeat");
		String username = request.getParameter("username");
		request.setAttribute("username", username);
		request.setAttribute("errorUsername", errorUsername);
		request.setAttribute("errorPassword", errorPassword);
		request.setAttribute("errorPasswordRepeat", errorPasswordRepeat);
		request.getRequestDispatcher("WEB-INF/CreateAccount.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String passwordRepeat = request.getParameter("passwordrepeat");
		String errors = null;
		
		if(!validator.validateUsername(username)) {
			errors = "?errorUsername=" + ERROR_INVALID_USERNAME;
		}
		if(!validator.validatePassword(password)) {
			if(errors == null) {
				errors = "?errorPassword=" + ERROR_INVALID_PASSWORD;
			}
			else {
				errors += "&errorPassword=" + ERROR_INVALID_PASSWORD;
			}
		}
		if(!validator.validatePasswordRepeat(passwordRepeat, password)) {
			if(errors == null) {
				errors = "?errorPasswordRepeat=" + ERROR_PASSWORDS_DO_NOT_MATCH;
			}
			else {
				errors += "&errorPasswordRepeat=" + ERROR_PASSWORDS_DO_NOT_MATCH;
			}
		}
		
		if(errors == null) {	
			try {
				ShoppingUser newUser = new ShoppingUser(username,password);
				userDAO.addUser(newUser);
			}
			catch(Throwable e) {
				errors = "";
			}
			
			if(errors == null) {
				Cookie loggedIn = new Cookie("loggedIn",username);
				loggedIn.setMaxAge(600);
				response.addCookie(loggedIn);
				response.sendRedirect("MainPage");
			}
			else {
				errors = "?errorUsername=" + ERROR_USERNAME_TAKEN;
				response.sendRedirect("CreateAccount" + errors + "&username=" + username);
			}
		}
		else {
			response.sendRedirect("CreateAccount" + errors + "&username=" + username);
		}
	}

}