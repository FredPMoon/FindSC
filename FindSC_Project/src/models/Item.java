package models;

import java.sql.Timestamp;
import java.util.List;

public class Item {
    private int id;
    private String name;
    private String category;
    private String location;
    private String image;
    private String reward;
    private int userId;
    private User author;
    private String description;
    private Timestamp timestamp;
    private List<Comment> comments;

    public Item(int id, String name, String category, String image, String location, String reward, int userId, User author, String description, Timestamp timestamp, List<Comment> comments) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.image = image;
        this.location = location;
        this.reward = reward;
        this.userId = userId;
        this.author = author;
        this.description = description;
        this.timestamp = timestamp;
        this.comments = comments;
    }

    public Item(int id, String name, String category, String image, String location, String reward, int userId, User author, String description, Timestamp timestamp) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.image = image;
        this.location = location;
        this.reward = reward;
        this.userId = userId;
        this.author = author;
        this.description = description;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getLocation() {
        return location;
    }

    public int getUserId() {
        return userId;
    }
    public String getImage() {
        return image;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public String getReward() {
        return reward;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public User getAuthor() {
        return author;
    }
}
