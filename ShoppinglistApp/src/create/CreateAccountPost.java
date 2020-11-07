package create;

import validation.Validator;
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

@WebServlet("/CreateAccountPost")
public class CreateAccountPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final Validator validator = new Validator();
	
	private final String ERROR_INVALID_USERNAME = "Invalid Username";
	private final String ERROR_INVALID_PASSWORD = "Invalid Password";
	private final String ERROR_PASSWORDS_DO_NOT_MATCH = "Passwords do not match";
	
	@EJB
	private UserDAO userDAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String passwordRepeat = request.getParameter("passwordrepeat");
		request.getSession().invalidate();
		boolean validated = true;
		
		if(!validator.validateUsername(username)) {
			request.getSession().setAttribute("ERROR_INVALID_USERNAME", ERROR_INVALID_USERNAME);
			validated = false;
		}
		if(!validator.validatePassword(password)) {
			request.getSession().setAttribute("ERROR_INVALID_PASSWORD", ERROR_INVALID_PASSWORD);
			validated = false;
		}
		if(!validator.validatePasswordRepeat(passwordRepeat, password)) {
			request.getSession().setAttribute("ERROR_PASSWORDS_DO_NOT_MATCH", ERROR_PASSWORDS_DO_NOT_MATCH);
			validated = false;
		}
		
		if(validated) {	
			try {
				ShoppingUser newUser = new ShoppingUser(username,password);
				userDAO.addUser(newUser);
			}
			catch(Throwable e) {
				response.sendRedirect("CreateAccount");
			}
			
			Cookie loggedIn = new Cookie("loggedIn",username);
			loggedIn.setMaxAge(600);
			response.sendRedirect("MainPage");
		}
		else {
			response.sendRedirect("CreateAccount");
		}
	}

}