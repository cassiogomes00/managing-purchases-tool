package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import model.*;
import utilities.ConnectionUtil;

public class PucharseDAO {
	private Connection connection;
	private ConnectionUtil connectionUtil;

	public PucharseDAO(Connection connection) {
		this.connection = connection;
		this.connectionUtil = new ConnectionUtil(connection);
	}

	public Connection getConnection() {
		return connection;
	}

	public ConnectionUtil getConnectionUtil() {
		return connectionUtil;
	}

	public boolean insert(Pucharse pucharse) throws SQLException, ClassNotFoundException {
		ConnectionUtil connectionUtil = this.getConnectionUtil();

		String query = "INSERT INTO pucharse (customer_id, product_id, pucharse_product_quantity, pucharse_date) VALUES (?, ?, ?, ?)";
		PreparedStatement prepStatement = connectionUtil.getPreparedStatement(query);

		int customerId = pucharse.getCustomer().getId();
		int productId = pucharse.getProduct().getId();
		int productQuantity = pucharse.getProductQuantity();
		Date date = pucharse.getDate();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());

		prepStatement.setInt(1, customerId);
		prepStatement.setInt(2, productId);
		prepStatement.setInt(3, productQuantity);
		prepStatement.setDate(4, sqlDate);

		int lines = prepStatement.executeUpdate();

		if (lines != 1) {
			throw new SQLException("Pucharse insertion failed");
		}

		connection.commit();

		return true;
	}

	public Pucharse findById(int customerId, int productId, Date date) throws SQLException, ClassNotFoundException {
		ConnectionUtil connectionUtil = this.getConnectionUtil();

		CustomerDAO customerDao = new CustomerDAO(connection);
		ProductDAO productDao = new ProductDAO(connection);

		String query = "SELECT * FROM pucharse WHERE (customer_id = ?) AND (product_id = ?) AND (pucharse_date = ?)";
		PreparedStatement prepStatement = connectionUtil.getPreparedStatement(query);
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());

		prepStatement.setInt(1, customerId);
		prepStatement.setInt(2, productId);
		prepStatement.setDate(3, sqlDate);

		ResultSet res = prepStatement.executeQuery();

		res.last();

		if (res.getRow() != 1) {
			throw new SQLException("Pucharse search failed");
		}

		res.first();

		int productQuantity = res.getInt("pucharse_product_quantity");

		Customer customer = customerDao.findById(customerId);
		Product product = productDao.findById(productId);

		return new Pucharse(customer, product, productQuantity, date);
	}

	public ResultSet find() throws SQLException, ClassNotFoundException {
		String query = "SELECT * FROM pucharse";
		PreparedStatement prepStatement = connectionUtil.getPreparedStatement(query);
		ResultSet res = prepStatement.executeQuery();

		res.first();

		return res;
	}

	public boolean update(Pucharse pucharse) throws ClassNotFoundException, SQLException {
		ConnectionUtil connectionUtil = this.getConnectionUtil();

		String query = "UPDATE pucharse SET pucharse_product_quantity = ? WHERE (customer_id = ?) AND (product_id = ?) AND (pucharse_date = ?)";
		PreparedStatement prepStatement = connectionUtil.getPreparedStatement(query);

		int customerId = pucharse.getCustomer().getId();
		int productId = pucharse.getProduct().getId();
		int productQuantity = pucharse.getProductQuantity();
		Date date = pucharse.getDate();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());

		prepStatement.setInt(1, productQuantity);
		prepStatement.setInt(2, customerId);
		prepStatement.setInt(3, productId);
		prepStatement.setDate(4, sqlDate);

		int lines = prepStatement.executeUpdate();

		if (lines != 1) {
			throw new SQLException("Pucharse update failed");
		}

		connection.commit();

		return true;
	}

	public boolean deleteById(int customerId, int productId, Date date) throws ClassNotFoundException, SQLException {
		ConnectionUtil connectionUtil = this.getConnectionUtil();

		String query = "DELETE FROM pucharse WHERE (customer_id = ?) AND (product_id = ?) AND (pucharse_date = ?)";
		PreparedStatement prepStatement = connectionUtil.getPreparedStatement(query);
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());

		prepStatement.setInt(1, customerId);
		prepStatement.setInt(2, productId);
		prepStatement.setDate(3, sqlDate);

		int lines = prepStatement.executeUpdate();

		if (lines != 1) {
			throw new SQLException("Pucharse deletion failed");
		}

		connection.commit();

		return true;
	}
}
