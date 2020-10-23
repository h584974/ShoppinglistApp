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
		String message = (String)request.getSession().getAttribute("message");
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=\"stylesheet\" href=\"CSS/LoginStyle.css\">");
		out.println("<meta charset=\"UTF-8\">");
		out.println("<title>Login</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<form action=\"LoginPost\" method=\"post\">");
		out.println("<fieldset>");
		out.println("<legend>Login</legend>");
		
		if(message != null && !message.isBlank())
			out.println("<div class=\"message\"><p>" + message + "</p></div>");
		
		out.println("<p>Username&ensp;&ensp;<input type=\"text\" name=\"username\"></p>");
		out.println("<p>Password&ensp;&ensp;<input type=\"password\" name=\"password\"></p>");
		out.println("<input type=\"submit\" name=\"login\" value=\"Login\">&ensp;&ensp;"
				+ "<input type=\"submit\" value=\"Create Account\">");
		out.println("</fieldset>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
	}

}