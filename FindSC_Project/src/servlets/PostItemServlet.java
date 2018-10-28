
package servlets;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import helpers.Authentication;
import helpers.SendEmail;
import helpers.Utils;
import models.Item;
import models.User;

/**
 * Servlet implementation class PostItemServlet
 */
@WebServlet("/post")
@MultipartConfig
public class PostItemServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!Authentication.isLoggedIn(request)) {
            response.sendRedirect("/FindSC/signup");
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/item_posting.jsp");
            rd.forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // start to check item input information
        if (!Authentication.isLoggedIn(request)) { // if user is not already logged in
            response.sendRedirect("/FindSC/signup");
            return;
        }
        if (this.checkEmpty(request).length() != 0) { // if some of the required fields are empty
            request.setAttribute("message", this.checkEmpty(request));
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/item_posting.jsp");
            rd.forward(request, response);
            return;
        }
        // update new item to the database
        int itemId = this.update(request);
        if (itemId == -1) { // if username already exists in database
            request.setAttribute("message", "post item fails.");
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/item_posting.jsp");
            rd.forward(request, response);
            return;
        } else { // if successfully signed up
            response.sendRedirect("view_item?itemid=" + itemId);
            return;
        }

    }

    private String checkEmpty(HttpServletRequest request) {
        String result = "";
        if (request.getParameter("name") == null || request.getParameter("name").length() == 0) {
            result += "Item Name cannot be empty</br>";
        }
        if (request.getParameter("location") == null || request.getParameter("location").length() == 0) {
            result += "Item Location cannot be empty</br>";
        }
        if (request.getParameter("category") == null || request.getParameter("category").length() == 0) {
            result += "Item Category cannot be empty</br>";
        }
        return result;
    }

    // update function to the database
    private int update(HttpServletRequest request) {
        try (Connection conn = Utils.getDBConnection()) {

            Part filePart = request.getPart("image");
            if (filePart == null || filePart.getSize() == 0) {
                return -1;
            }
            InputStream inputStream = filePart.getInputStream();

            String itemName = request.getParameter("name");
            String itemLocation = request.getParameter("location");
            String itemCategory = request.getParameter("category");
            String reward = request.getParameter("reward");
            String description = request.getParameter("description");
            User user = (User) request.getSession().getAttribute("user");
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Items (user_id, name, category, location, description, reward, image) VALUES (?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, user.getId());
            ps.setString(2, itemName);
            ps.setString(3, itemCategory);
            ps.setString(4, itemLocation);
            ps.setString(5, description);
            ps.setString(6, reward);
            ps.setBlob(7, inputStream);

            ps.executeUpdate();
            ps.close();

            PreparedStatement psId = conn.prepareStatement("SELECT LAST_INSERT_ID()");
            ResultSet rsId = psId.executeQuery();
            rsId.next();
            int itemId = rsId.getInt(1);

            Item item = new Item(itemId, itemName, itemCategory, null, itemLocation, reward, user.getId(), user, description, new Timestamp(System.currentTimeMillis()), null);
            SendEmail.items.offer(item);

            return itemId;

        } catch (SQLException | IOException | ServletException sqle) {
            System.out.println("sqle: " + sqle.getMessage());
            return -1;
        }
    }

}
