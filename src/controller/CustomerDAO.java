package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Customer;
import utilities.ConnectionUtil;

public class CustomerDAO {
	private Connection connection;
	private ConnectionUtil connectionUtil;

	public CustomerDAO(Connection connection) {
		this.connection = connection;
		this.connectionUtil = new ConnectionUtil(connection);
	}

	public Connection getConnection() {
		return connection;
	}

	public ConnectionUtil getConnectionUtil() {
		return connectionUtil;
	}

	public boolean insert(Customer customer) throws SQLException, ClassNotFoundException {
		ConnectionUtil connectionUtil = this.getConnectionUtil();

		String query = "INSERT INTO customer (customer_first_name, customer_last_name, customer_bio) VALUES (?, ?, ?)";
		PreparedStatement prepStatement = connectionUtil.getPreparedStatement(query);

		String firstName = customer.getFirstName();
		String lastName = customer.getLastName();
		String bio = customer.getBio();

		prepStatement.setString(1, firstName);
		prepStatement.setString(2, lastName);
		prepStatement.setString(3, bio);

		int lines = prepStatement.executeUpdate();

		if (lines != 1) {
			throw new SQLException("Customer insertion failed");
		}

		connection.commit();

		return true;
	}

	public Customer findById(int id) throws SQLException, ClassNotFoundException {
		ConnectionUtil connectionUtil = this.getConnectionUtil();

		String query = "SELECT * FROM customer WHERE customer_id = ?";
		PreparedStatement prepStatement = connectionUtil.getPreparedStatement(query);

		prepStatement.setInt(1, id);

		ResultSet res = prepStatement.executeQuery();

		res.last();

		if (res.getRow() != 1) {
			throw new SQLException("Customer search failed");
		}

		res.first();

		String firstName = res.getString("customer_first_name");
		String lastName = res.getString("customer_last_name");
		String bio = res.getString("customer_bio");

		return new Customer(id, firstName, lastName, bio);
	}

	public ResultSet find() throws SQLException, ClassNotFoundException {
		ConnectionUtil connectionUtil = this.getConnectionUtil();

		String query = "SELECT * FROM customer";
		PreparedStatement prepStatement = connectionUtil.getPreparedStatement(query);
		ResultSet res = prepStatement.executeQuery();

		res.first();

		return res;
	}

	public boolean update(Customer customer) throws ClassNotFoundException, SQLException {
		ConnectionUtil connectionUtil = this.getConnectionUtil();

		String query = "UPDATE customer SET customer_first_name = ?, customer_last_name = ?, customer_bio = ? WHERE customer_id = ?";
		PreparedStatement prepStatement = connectionUtil.getPreparedStatement(query);

		int id = customer.getId();
		String firstName = customer.getFirstName();
		String lastName = customer.getLastName();
		String bio = customer.getBio();

		prepStatement.setString(1, firstName);
		prepStatement.setString(2, lastName);
		prepStatement.setString(3, bio);
		prepStatement.setInt(4, id);

		int lines = prepStatement.executeUpdate();

		if (lines != 1) {
			throw new SQLException("Customer update failed");
		}

		connection.commit();

		return true;
	}

	public boolean deleteById(int id) throws ClassNotFoundException, SQLException {
		ConnectionUtil connectionUtil = this.getConnectionUtil();

		String query = "DELETE FROM customer WHERE customer_id = ?";
		PreparedStatement prepStatement = connectionUtil.getPreparedStatement(query);

		prepStatement.setInt(1, id);

		int lines = prepStatement.executeUpdate();

		if (lines != 1) {
			throw new SQLException("Customer deletion failed");
		}

		connection.commit();

		return true;
	}
}
