package servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import helpers.Utils;
import models.Item;
import models.User;

/**
 * Servlet implementation class ViewItemServlet
 */
@WebServlet("/view_item")
public class ViewItemServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewItemServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int itemId = -1;
        String itemParameter = request.getParameter("itemid");
        try {
            itemId = Integer.parseInt(itemParameter);
        } catch (NumberFormatException ex) {

        }
        if (itemId == -1) {
            response.sendRedirect("/FindSC/view_items");
            return;
        }
        Item item = Utils.getItem(itemId);
        if (item == null) {
            response.sendRedirect("/FindSC/view_items");
            return;
        } else {
            request.setAttribute("item", item);
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/view_item.jsp");
            rd.forward(request, response);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int itemId = -1;
        String itemParameter = request.getParameter("itemid");
        String comment = request.getParameter("comment");
        if(comment == null || comment.isEmpty()) {
            response.sendRedirect("/FindSC/view_items");
            return;
        }
        try {
            itemId = Integer.parseInt(itemParameter);
        } catch (NumberFormatException ex) {

        }
        if (itemId == -1) {
            response.sendRedirect("/FindSC/view_items");
            return;
        }
        Item item = Utils.getItem(itemId);
        if (item == null) {
            response.sendRedirect("/FindSC/view_items");
            return;
        }else {
            Utils.insertComment(((User)request.getSession().getAttribute("user")), itemId, comment);
            WebSocketEndpoint.broadcast(itemId, null);
            response.sendRedirect("view_item?itemid=" + itemId);
        }
    }

}
