package helpers;

import javax.servlet.http.HttpServletRequest;

public class Authentication {
	public static boolean isLoggedIn(HttpServletRequest request) {
		return request.getSession().getAttribute("user") != null;
	}
}
