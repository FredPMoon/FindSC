package servlets;

import java.io.IOException;
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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (Authentication.isLoggedIn(request)) {
            response.sendRedirect("/FindSC");
            return;
        }
        RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/login.jsp");
        rd.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (Authentication.isLoggedIn(request)) {
            response.sendRedirect("/FindSC");
            return;
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username == null || password == null) {
            request.setAttribute("message", "Please enter username and password");
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/login.jsp");
            rd.forward(request, response);
            return;
        }
        User user = Utils.getUser(username);
        if (user == null || !user.getPassword().equals(password)) {
            request.setAttribute("message", "Incorrect username and password combination");
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/login.jsp");
            rd.forward(request, response);
        } else {
            request.getSession().setAttribute("user", user);
            response.sendRedirect("/FindSC");
        }
    }
}
