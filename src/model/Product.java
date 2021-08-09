package model;

public class Product {
	private int id;
	private String description;
	private float basePrice;
	private float discount;

	public Product(int id) {
		this.id = id;
		this.description = "";
		this.basePrice = 0;
		this.discount = 0;
	}

	public Product(int id, String description, float basePrice, float discount) {
		this.id = id;
		this.description = description;
		this.basePrice = basePrice;
		this.discount = discount;
	}

	public Product(String description, float basePrice) {
		this.id = 0;
		this.description = description;
		this.basePrice = basePrice;
		this.discount = 0;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public float getBasePrice() {
		return basePrice;
	}

	public float getDiscount() {
		return discount;
	}
}
