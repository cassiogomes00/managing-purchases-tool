package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Pucharse {
	private Customer customer;
	private Product product;
	private int productQuantity;
	private Date date;

	public Pucharse(Customer customer, Product product, int productQuantity) {
		this.customer = customer;
		this.product = product;
		this.productQuantity = productQuantity;
		this.date = new Date();
	}

	public Pucharse(int customerId, int productId, int productQuantity) {
		this.customer = new Customer(customerId);
		this.product = new Product(productId);
		this.productQuantity = productQuantity;
		this.date = new Date();
	}

	public Pucharse(int customerId, int productId, int productQuantity, Date date) {
		this.customer = new Customer(customerId);
		this.product = new Product(productId);
		this.productQuantity = productQuantity;
		this.date = date;
	}

	public Pucharse(Customer customer, Product product, int productQuantity, Date date) {
		this.customer = customer;
		this.product = product;
		this.productQuantity = productQuantity;
		this.date = date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setDate(String date) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		this.date = format.parse(date);
	}

	public Customer getCustomer() {
		return customer;
	}

	public Product getProduct() {
		return product;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public Date getDate() {
		return date;
	}
}
