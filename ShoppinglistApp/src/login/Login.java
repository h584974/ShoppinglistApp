package login;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String errorMessage = (String)request.getSession().getAttribute("errorMessage");
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title>Login</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<form action=\"LoginPost\" method=\"post\">");
		out.println("<fieldset>");
		out.println("<legend>Login</legend>");
		
		if(errorMessage != null && !errorMessage.isBlank())
			out.println("<p>" + errorMessage + "</p>");
		
		out.println("<p>Username&ensp;&ensp;<input type=\"text\" name=\"brukernavn\"></p>");
		out.println("<p>Password&ensp;&ensp;<input type=\"password\" name=\"password\"></p>");
		out.println("<input type=\"submit\" name=\"login\" value=\"Login\">&ensp;&ensp;"
				+ "<input type=\"submit\" name=\"create\" value=\"Create Account\">");
		out.println("</fieldset>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
	}

}