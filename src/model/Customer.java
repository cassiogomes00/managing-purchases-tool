package model;

public class Customer {
	private int id;
	private String firstName;
	private String lastName;
	private String bio;

	public Customer(int id) {
		this.id = id;
		this.firstName = "";
		this.lastName = "";
		this.bio = "";
	}

	public Customer(String firstName, String lastName, String bio) {
		this.id = 0;
		this.firstName = firstName;
		this.lastName = lastName;
		this.bio = bio;
	}

	public Customer(int id, String firstName, String lastName, String bio) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.bio = bio;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getBio() {
		return bio;
	}
}
