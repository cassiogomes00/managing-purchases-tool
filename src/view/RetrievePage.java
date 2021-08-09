package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;

import controller.*;
import model.*;

public class RetrievePage extends ParentFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	Connection connection;
	String table;
	Object object;
	ResultSet res;

	JLabel labelTitle01;
	JLabel labelTitle02;
	JLabel labelTitle03;
	JLabel labelTitle04;

	JLabel labelValue01;
	JLabel labelValue02;
	JLabel labelValue03;
	JLabel labelValue04;

	JButton buttonFirst;
	JButton buttonPrevious;
	JButton buttonNext;
	JButton buttonLast;
	JButton buttonEdit;
	JButton buttonDelete;
	JButton buttonReload;
	JButton buttonBack;

	public RetrievePage(Connection connection, String table) throws ClassNotFoundException, SQLException {
		super(2);

		this.connection = connection;
		this.table = table;

		labelTitle01 = new JLabel();
		labelTitle02 = new JLabel();
		labelTitle03 = new JLabel();
		labelTitle04 = new JLabel();

		labelValue01 = new JLabel();
		labelValue02 = new JLabel();
		labelValue03 = new JLabel();
		labelValue04 = new JLabel();

		buttonFirst = new JButton("First");
		buttonPrevious = new JButton("Previous");
		buttonNext = new JButton("Next");
		buttonLast = new JButton("Last");
		buttonEdit = new JButton("Edit");
		buttonDelete = new JButton("Delete");
		buttonReload = new JButton("Reload");
		buttonBack = new JButton("Back");

		buttonFirst.addActionListener(this);
		buttonPrevious.addActionListener(this);
		buttonNext.addActionListener(this);
		buttonLast.addActionListener(this);
		buttonEdit.addActionListener(this);
		buttonDelete.addActionListener(this);
		buttonReload.addActionListener(this);
		buttonBack.addActionListener(this);

		formatObject();
		setLabels();
		getRes();
		setObject();
		setValues();

		add(labelTitle01);
		add(labelValue01);

		add(labelTitle02);
		add(labelValue02);

		if (table.equals("product") || table.equals("pucharse")) {
			add(labelTitle03);
			add(labelValue03);
		}

		if (table.equals("pucharse")) {
			add(labelTitle04);
			add(labelValue04);
		}

		add(buttonFirst);
		add(buttonPrevious);
		add(buttonNext);
		add(buttonLast);
		add(buttonEdit);
		add(buttonDelete);
		add(buttonReload);
		add(buttonBack);
	}

	public void formatObject() {
		String table = this.table;

		switch (table) {
		case "customer":
			this.object = (Customer) this.object;
			break;

		case "product":
			this.object = (Product) this.object;
			break;

		case "pucharse":
			this.object = (Pucharse) this.object;
			break;

		}
	}

	public void setLabels() {
		String table = this.table;

		switch (table) {
		case "customer":
			labelTitle01.setText("Name");
			labelTitle02.setText("Bio");
			break;

		case "product":
			labelTitle01.setText("Description");
			labelTitle02.setText("Base price");
			labelTitle03.setText("Final price");
			break;

		case "pucharse":
			labelTitle01.setText("Client");
			labelTitle02.setText("Product");
			labelTitle03.setText("Quantity");
			labelTitle04.setText("Final price");
			break;

		}
	}

	public void setValues() {
		String table = this.table;

		switch (table) {
		case "customer":
			Customer customer = (Customer) this.object;
			String name = customer.getFirstName() + " " + customer.getLastName();
			String bio = customer.getBio();

			labelValue01.setText(name);
			labelValue02.setText(bio);
			break;

		case "product":
			Product product = (Product) this.object;

			String description = product.getDescription();
			String basePrice = Float.toString(product.getBasePrice());
			String finalPrice = Float.toString(product.getBasePrice() * (1 - product.getDiscount()));

			labelValue01.setText(description);
			labelValue02.setText(basePrice);
			labelValue03.setText(finalPrice);
			break;

		case "pucharse":
			Pucharse pucharse = (Pucharse) this.object;

			String customerName = pucharse.getCustomer().getFirstName() + " " + pucharse.getCustomer().getLastName();
			String productDescription = pucharse.getProduct().getDescription();
			String productQuantity = Integer.toString(pucharse.getProductQuantity());
			String price = Float.toString(pucharse.getProduct().getBasePrice()
					* (1 - pucharse.getProduct().getDiscount()) * pucharse.getProductQuantity());

			labelValue01.setText(customerName);
			labelValue02.setText(productDescription);
			labelValue03.setText(productQuantity);
			labelValue04.setText(price);
			break;

		}
	}

	public void getRes() throws ClassNotFoundException, SQLException {
		switch (table) {
		case "customer":
			CustomerDAO customerDao = new CustomerDAO(connection);

			res = customerDao.find();
			res.first();

			break;

		case "product":
			ProductDAO productDao = new ProductDAO(connection);

			res = productDao.find();
			res.first();

			break;

		case "pucharse":
			PucharseDAO pucharseDao = new PucharseDAO(connection);

			res = pucharseDao.find();
			res.first();

			break;
		}
	}

	public void setObject() throws SQLException, ClassNotFoundException {
		int id = 0;

		switch (table) {
		case "customer":
			CustomerDAO customerDao = new CustomerDAO(connection);

			id = res.getInt("customer_id");
			object = customerDao.findById(id);

			break;

		case "product":
			ProductDAO productDao = new ProductDAO(connection);

			id = res.getInt("product_id");
			object = productDao.findById(id);

			break;

		case "pucharse":
			PucharseDAO pucharseDao = new PucharseDAO(connection);

			int customerId = res.getInt("customer_id");
			int productId = res.getInt("product_id");
			Date date = res.getDate("pucharse_date");
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());

			object = pucharseDao.findById(customerId, productId, sqlDate);

			break;
		}
	}

	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand().toLowerCase();

		switch (command) {
		case "first":
			try {
				res.first();
				setObject();
				setValues();
			} catch (Exception e) {
				new HandleExceptions().showException(e);
				this.setVisible(false);
			}

			break;
		case "previous":
			try {
				res.previous();
				setObject();
				setValues();
			} catch (Exception e) {
				new HandleExceptions().showException(e);
				this.setVisible(false);
			}
			break;
		case "next":
			try {
				res.next();
				setObject();
				setValues();
			} catch (Exception e) {
				new HandleExceptions().showException(e);
				this.setVisible(false);
			}
			break;
		case "last":
			try {
				res.last();
				setObject();
				setValues();
			} catch (Exception e) {
				new HandleExceptions().showException(e);
				this.setVisible(false);
			}
			break;
		case "edit":
			new EditPage(connection, table, "update", object);
			break;
		case "delete":
			switch (table) {
			case "customer":
				CustomerDAO customerDao = new CustomerDAO(connection);
				Customer customer = (Customer) object;

				try {
					customerDao.deleteById(customer.getId());
					getRes();
					res.first();
					setObject();
					setValues();
				} catch (Exception e) {
					new HandleExceptions().showException(e);
					this.setVisible(false);
				}

				break;

			case "product":
				ProductDAO productDao = new ProductDAO(connection);
				Product product = (Product) object;

				try {
					productDao.deleteById(product.getId());
					getRes();
					res.first();
					setObject();
					setValues();
				} catch (Exception e) {
					new HandleExceptions().showException(e);
					this.setVisible(false);
				}

				break;

			case "pucharse":
				PucharseDAO pucharseDao = new PucharseDAO(connection);
				Pucharse pucharse = (Pucharse) object;

				int customerId = pucharse.getCustomer().getId();
				int productId = pucharse.getProduct().getId();
				Date date = pucharse.getDate();
				java.sql.Date sqlDate = new java.sql.Date(date.getTime());

				try {
					pucharseDao.deleteById(customerId, productId, sqlDate);
					getRes();
					res.first();
					setObject();
					setValues();
				} catch (Exception e) {
					new HandleExceptions().showException(e);
					this.setVisible(false);
				}

				break;
			}
			break;

		case "reload":
			try {
				getRes();
				res.first();
				setObject();
				setValues();
			} catch (Exception e) {
				new HandleExceptions().showException(e);
				this.setVisible(false);
			}
			break;
		case "back":
			this.setVisible(false);
			break;
		}

	}
}
