package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.*;
import model.*;

public class EditPage extends ParentFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	Connection connection;
	String table;
	String operation;
	Object object;

	private JLabel label01;
	private JLabel label02;
	private JLabel label03;

	private JTextField tf01;
	private JTextField tf02;
	private JTextField tf03;

	private JButton buttonCancel;
	private JButton buttonSave;

	public EditPage(Connection connection, String table, String operation, Object object) {
		super(2);

		this.connection = connection;
		this.table = table;
		this.operation = operation;
		this.object = object;

		this.label01 = new JLabel();
		this.label02 = new JLabel();
		this.label03 = new JLabel();

		this.tf01 = new JTextField();
		this.tf02 = new JTextField();
		this.tf03 = new JTextField();

		this.buttonCancel = new JButton("Cancel");
		this.buttonSave = new JButton("Save");

		this.buttonCancel.addActionListener(this);
		this.buttonSave.addActionListener(this);

		formatObject();

		if (operation.equals("create")) {
			initObject();
		}

		setLabels();
		setTf();

		add(label01);
		add(tf01);

		add(label02);
		add(tf02);

		add(label03);
		add(tf03);

		add(buttonCancel);
		add(buttonSave);
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

	public void initObject() {
		String table = this.table;

		switch (table) {
		case "customer":
			this.object = new Customer(0);
			break;

		case "product":
			this.object = new Product(0);
			break;

		case "pucharse":
			this.object = new Pucharse(0, 0, 0);
			break;

		}
	}

	public void setLabels() {
		String table = this.table;

		switch (table) {
		case "customer":
			label01.setText("First name");
			label02.setText("Last name");
			label03.setText("Bio");
			break;

		case "product":
			label01.setText("Description");
			label02.setText("Base price");
			label03.setText("Discount");
			break;

		case "pucharse":
			label01.setText("Client id");
			label02.setText("Product id");
			label03.setText("Product quantity");
			break;

		}
	}

	public void setTf() {
		String table = this.table;

		switch (table) {
		case "customer":
			Customer customer = (Customer) this.object;

			tf01.setText(customer.getFirstName());
			tf02.setText(customer.getLastName());
			tf03.setText(customer.getBio());
			break;

		case "product":
			Product product = (Product) this.object;

			tf01.setText(product.getDescription());
			tf02.setText(Float.toString(product.getBasePrice()));
			tf03.setText(Float.toString(product.getDiscount()));
			break;

		case "pucharse":
			Pucharse pucharse = (Pucharse) this.object;

			tf01.setText(Integer.toString(pucharse.getCustomer().getId()));
			tf02.setText(Integer.toString(pucharse.getProduct().getId()));
			tf03.setText(Integer.toString(pucharse.getProductQuantity()));
			break;

		}
	}

	public void callDAO() throws ClassNotFoundException, SQLException {
		int id = 0;

		switch (table) {
		case "customer":
			id = ((Customer) object).getId();

			String firstName = tf01.getText();
			String lastName = tf02.getText();
			String bio = tf03.getText();

			CustomerDAO customerDao = new CustomerDAO(this.connection);

			if (id == 0) {
				Customer customer = new Customer(firstName, lastName, bio);
				customerDao.insert(customer);
				break;
			}

			Customer customer = new Customer(id, firstName, lastName, bio);
			customerDao.update(customer);

			break;

		case "product":
			id = ((Product) this.object).getId();
			String description = tf01.getText();
			float basePrice = Float.parseFloat(tf02.getText());
			float discount = Float.parseFloat(tf03.getText());

			ProductDAO productDao = new ProductDAO(this.connection);

			if (id == 0) {
				Product product = new Product(description, basePrice);
				productDao.insert(product);
				break;
			}

			Product product = new Product(id, description, basePrice, discount);
			productDao.update(product);

			break;

		case "pucharse":
			int customerId = Integer.parseInt(tf01.getText());
			int productId = Integer.parseInt(tf02.getText());
			int productQuantity = Integer.parseInt(tf03.getText());
			Date date = ((Pucharse) this.object).getDate();

			PucharseDAO pucharseDao = new PucharseDAO(connection);

			if (this.operation.equals("create")) {
				Pucharse pucharse = new Pucharse(customerId, productId, productQuantity);

				pucharseDao.insert(pucharse);
				break;
			}

			Pucharse pucharse = new Pucharse(customerId, productId, productQuantity, date);

			pucharseDao.update(pucharse);
			break;
		}
	}

	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand().toLowerCase();

		if (command.equals("save")) {
			try {
				callDAO();
			} catch (Exception e) {
				new HandleExceptions().showException(e);
			}
		}

		this.setVisible(false);
	}
}
