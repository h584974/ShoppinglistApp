package login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginPost")
public class LoginPost extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String username = request.getParameter("username");
		
		if(login == null || login.isBlank()) {
			response.sendRedirect("CreateAccount");
		}
		else {
			if(username == null || username.isBlank() || request.getParameter("password") == null || 
			request.getParameter("password").isBlank()) {
				String errorMessage = "Username and Password must be submitted";
				request.getSession().setAttribute("errorMessage", errorMessage);
				response.sendRedirect("Login");
			}
			else {
				
			}
		}
	}

}