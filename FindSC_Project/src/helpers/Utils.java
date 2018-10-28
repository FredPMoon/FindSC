package helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import models.Comment;
import models.Item;
import models.User;

public class Utils {
    public static Connection getDBConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://162.243.151.121/findsc?user=root&password=Shunkaizzz101;&useSSL=false");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static User getUser(int userId) {
        try (Connection conn = getDBConnection()) {
            if (conn == null)
                return null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Users WHERE id=?");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String uscEmail = rs.getString("usc_email");
                String phoneNumber = rs.getString("phone_number");
                return new User(userId, username, password, uscEmail, phoneNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User getUser(String username) {
        try (Connection conn = getDBConnection()) {
            if (conn == null)
                return null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Users WHERE username=?");
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int userId = rs.getInt("id");
                String password = rs.getString("password");
                String uscEmail = rs.getString("usc_email");
                String phoneNumber = rs.getString("phone_number");
                return new User(userId, username, password, uscEmail, phoneNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Item getItem(int itemId) {
        Item item = null;
        try (Connection conn = getDBConnection()) {
            if (conn == null)
                return null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Items WHERE id=?");
            ps.setInt(1, itemId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String category = rs.getString("category");
                String image = Base64.getEncoder().encodeToString(rs.getBlob("image").getBytes(1, (int) rs.getBlob("image").length()));
                String location = rs.getString("location");
                String reward = rs.getString("reward");
                int userId = rs.getInt("user_id");
                String description = rs.getString("description");
                Timestamp timestamp = new Timestamp(rs.getTimestamp("timestamp").getTime() - 8 * 1000 * 3600);

                User author = getUser(userId);

                List<Comment> comments = new ArrayList<Comment>();
                PreparedStatement psComment = conn.prepareStatement("SELECT * FROM Comments INNER JOIN Users ON Comments.user_id = Users.id WHERE Comments.item_id=? ORDER BY date ASC");
                psComment.setInt(1, itemId);
                ResultSet rsComment = psComment.executeQuery();
                while (rsComment.next()) {
                    int commentUserId = rsComment.getInt("user_id");
                    String username = rsComment.getString("username");
                    String password = rsComment.getString("password");
                    String uscEmail = rsComment.getString("usc_email");
                    String phoneNumber = rsComment.getString("phone_number");
                    User user = new User(commentUserId, username, password, uscEmail, phoneNumber);
                    int commentId = rsComment.getInt("id");
                    String commentString = rsComment.getString("comment");
                    Timestamp commentTimestamp = new Timestamp(rsComment.getTimestamp("date").getTime() - 8 * 3600 * 1000);
                    Comment comment = new Comment(commentId, commentUserId, itemId, commentString, commentTimestamp, user);
                    comments.add(comment);
                }
                item = new Item(id, name, category, image, location, reward, userId, author, description, timestamp, comments);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    public static void insertComment(User user, int itemId, String comment) {
        try (Connection conn = getDBConnection()) {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Comments(item_id, user_id, comment) VALUES (?, ?, ?)");
            ps.setInt(1, itemId);
            ps.setInt(2, user.getId());
            ps.setString(3, comment);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Set<String> getAllEmails() {
        Set<String> emails = new HashSet<String>();
        try (Connection conn = getDBConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT usc_email from Users");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String email = rs.getString("usc_email");
                emails.add(email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emails;
    }

    public static List<Item> getItemsAfterId(int idAfter) {
        return getItems(idAfter, null);
    }

    public static List<Item> getItems() {
        return getItemsAfterId(0);
    }

    public static List<Item> getItemsByFilter(String filter) {
        return getItems(0, filter);
    }

    public static List<Item> getItems(int adAfter, String filter) {
        List<Item> items = new ArrayList<Item>();
        try (Connection conn = getDBConnection()) {
            if (conn == null)
                return null;
            String queryString = "SELECT * FROM Items WHERE id > ?";
            if (filter != null) {
                queryString += " AND category=?";
            }
            PreparedStatement ps = conn.prepareStatement(queryString);
            ps.setInt(1, adAfter);
            if (filter != null) {
                ps.setString(2, filter);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String category = rs.getString("category");
                String image = Base64.getEncoder().encodeToString(rs.getBlob("image").getBytes(1, (int) rs.getBlob("image").length()));
                String location = rs.getString("location");
                String reward = rs.getString("reward");
                int userId = rs.getInt("user_id");
                String description = rs.getString("description");
                Timestamp timestamp = new Timestamp(rs.getTimestamp("timestamp").getTime() - 8 * 3600 * 1000);
                User author = getUser(userId);
                Item item = new Item(id, name, category, image, location, reward, userId, author, description, timestamp);
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public static int getHighestItemId() {
        try (Connection conn = getDBConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Items ORDER BY id DESC LIMIT 0, 1");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
}
