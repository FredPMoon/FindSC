package models;

import java.sql.Timestamp;

public class Comment {
    private int id;
    private int userId;
    private int itemId;
    private String comment;
    private Timestamp timestamp;
    private User user;

    public Comment(int id, int userId, int itemId, String comment, Timestamp timestamp, User user) {
        this.id = id;
        this.userId = userId;
        this.itemId = itemId;
        this.comment = comment;
        this.timestamp = timestamp;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getItemId() {
        return itemId;
    }

    public String getComment() {
        return comment;
    }

    public Timestamp getDate() {
        return timestamp;
    }

    public User getUser() {
        return user;
    }
}
