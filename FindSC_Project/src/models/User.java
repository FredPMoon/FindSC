package models;

public class User {
	private int id;
	private String username;
	private String password;
	private String uscEmail;
	private String phoneNumber;

	public User(int id, String username, String password, String uscEmail, String phoneNumber) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.uscEmail = uscEmail;
		this.phoneNumber = phoneNumber;
	}

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getUscEmail() {
		return uscEmail;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
}
