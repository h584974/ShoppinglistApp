package utils;

import java.util.Arrays;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginUtils {
	
	public static boolean userIsLoggedIn(HttpServletRequest request) {
		
		String username = null;

		try {
			username = Arrays.stream(request.getCookies()).filter(c -> c.getName().equals("loginCookie")).findFirst().get().getValue();
		}
		catch(Exception e) {}
		
		return username != null;
		
	}
	
	public static String getLoggedInUsername(HttpServletRequest request) {
		
		String username = null;

		try {
			username = Arrays.stream(request.getCookies()).filter(c -> c.getName().equals("loginCookie")).findFirst().get().getValue();
		}
		catch(Exception e) {}
		
		return username;
		
	}
	
	public static void logoutUser(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			Cookie cookie = Arrays.stream(request.getCookies()).filter(c -> c.getName().equals("loginCookie")).findFirst().get();
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		catch(Exception e) {}
		
	}

}
