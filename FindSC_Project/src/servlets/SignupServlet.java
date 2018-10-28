package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helpers.Authentication;
import helpers.Utils;
import models.User;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(Authentication.isLoggedIn(request)) {	//	if user is already logged in
			response.sendRedirect("index.jsp");
		} else {	//	if user isn't logged in 
	        RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/signup.jsp");
	        dispatch.forward(request, response);
		}
	}

    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(Authentication.isLoggedIn(request)) {	//	if user is already logged in
		    response.sendRedirect("index.jsp");
			return;
		}
		if(this.checkEmpty(request).length() != 0) {	//	if some of the required fields are empty
			System.out.print("empty fields");
			request.setAttribute("message", this.checkEmpty(request));
            RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/signup.jsp");
            dispatch.forward(request, response);
			return;
		}
		boolean isSuccess = this.signup(request);
		if(isSuccess == false) { //	if username already exists in database
			request.setAttribute("message", "Username already exists.");
			RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/signup.jsp");
            dispatch.forward(request, response);
			return;
		} else { //	if successfully signed up
			response.sendRedirect("index.jsp");
			return;
		}
		
	}

    // check if any fields (except phone number) is empty or confirm password
    // mismatches with password
    private String checkEmpty(HttpServletRequest request) {
        String result = "";
        if (request.getParameter("username") == null || request.getParameter("username").length() == 0) {
            result += "username is empty\n";
        }
        if (request.getParameter("password") == null || request.getParameter("password").length() == 0) {
            result += "password is empty\n";
        }
        if (request.getParameter("confirm password") == null || request.getParameter("confirm password").length() == 0) {
            result += "confirm password is empty\n";
        }
        if (!request.getParameter("password").equals(request.getParameter("confirm password"))) {
            result += "password doesn't match with confirm password\n";
        }
        if (request.getParameter("usc_email") == null || request.getParameter("usc_email").length() == 0) {
            result += "usc email is empty\n";
        }
        return result;
    }

    private boolean signup(HttpServletRequest request) {
        System.out.print("in signup()");
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String usc_email = request.getParameter("usc_email");
            String phoneNumber = "";
            if (request.getParameter("phone_number") != null) {
                phoneNumber = request.getParameter("phone_number");
            }
            conn = Utils.getDBConnection();
            ps = conn.prepareStatement("SELECT * FROM Users WHERE username=?");
            ps.setString(1, request.getParameter("username"));
            rs = ps.executeQuery();
            if (rs.next()) { // if username already exists in database
                return false;
            }
            ps = conn.prepareStatement("INSERT INTO Users (username, password, usc_email, phone_number) VALUES (?, ?, ?, ?)");
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, usc_email);
            ps.setString(4, phoneNumber);
            ps.executeUpdate();
            System.out.print("inserted");

            PreparedStatement ps1 = conn.prepareStatement("SELECT * FROM Users WHERE username=?");
            ps1.setString(1, username);
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                int id = rs1.getInt("id");
                request.getSession().setAttribute("user", new User(id, username, password, usc_email, phoneNumber));
            }
        } catch (SQLException sqle) {
            System.out.println("sqle: " + sqle.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException sqle) {
                System.out.println("sqle closing conn, st, rs: " + sqle.getMessage());
            }
        }
        return true;
    }

}
