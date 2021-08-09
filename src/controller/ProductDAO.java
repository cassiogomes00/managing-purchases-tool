package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Product;
import utilities.ConnectionUtil;

public class ProductDAO {
	private Connection connection;
	private ConnectionUtil connectionUtil;

	public ProductDAO(Connection connection) {
		this.connection = connection;
		this.connectionUtil = new ConnectionUtil(connection);
	}

	public Connection getConnection() {
		return connection;
	}

	public ConnectionUtil getConnectionUtil() {
		return connectionUtil;
	}

	public boolean insert(Product product) throws SQLException, ClassNotFoundException {
		ConnectionUtil connectionUtil = this.getConnectionUtil();

		String query = "INSERT INTO product (product_description, product_base_price, product_discount) VALUES (?, ?, ?)";
		PreparedStatement prepStatement = connectionUtil.getPreparedStatement(query);

		String description = product.getDescription();
		float basePrice = product.getBasePrice();
		float discount = product.getDiscount();

		prepStatement.setString(1, description);
		prepStatement.setFloat(2, basePrice);
		prepStatement.setFloat(3, discount);

		int lines = prepStatement.executeUpdate();

		if (lines != 1) {
			throw new SQLException("Product insertion failed");
		}

		connection.commit();

		return true;
	}

	public Product findById(int id) throws SQLException, ClassNotFoundException {
		ConnectionUtil connectionUtil = this.getConnectionUtil();

		String query = "SELECT * FROM product WHERE product_id = ?";
		PreparedStatement prepStatement = connectionUtil.getPreparedStatement(query);

		prepStatement.setInt(1, id);

		ResultSet res = prepStatement.executeQuery();

		res.last();

		if (res.getRow() != 1) {
			throw new SQLException("Product search failed");
		}

		res.first();

		String description = res.getString("product_description");
		float basePrice = res.getFloat("product_base_price");
		float discount = res.getFloat("product_discount");

		return new Product(id, description, basePrice, discount);
	}

	public ResultSet find() throws SQLException, ClassNotFoundException {
		ConnectionUtil connectionUtil = this.getConnectionUtil();

		String query = "SELECT * FROM product";
		PreparedStatement prepStatement = connectionUtil.getPreparedStatement(query);
		ResultSet res = prepStatement.executeQuery();

		res.first();

		return res;
	}

	public boolean update(Product product) throws ClassNotFoundException, SQLException {
		ConnectionUtil connectionUtil = this.getConnectionUtil();

		String query = "UPDATE product SET product_description = ?, product_base_price = ?, product_discount = ? WHERE product_id = ?";
		PreparedStatement prepStatement = connectionUtil.getPreparedStatement(query);

		int id = product.getId();
		String description = product.getDescription();
		float basePrice = product.getBasePrice();
		float discount = product.getDiscount();

		prepStatement.setString(1, description);
		prepStatement.setFloat(2, basePrice);
		prepStatement.setFloat(3, discount);
		prepStatement.setInt(4, id);

		int lines = prepStatement.executeUpdate();

		if (lines != 1) {
			throw new SQLException("Product update failed");
		}

		connection.commit();

		return true;
	}

	public boolean deleteById(int id) throws ClassNotFoundException, SQLException {
		ConnectionUtil connectionUtil = this.getConnectionUtil();

		String query = "DELETE FROM product WHERE product_id = ?";
		PreparedStatement prepStatement = connectionUtil.getPreparedStatement(query);

		prepStatement.setInt(1, id);

		int lines = prepStatement.executeUpdate();

		if (lines != 1) {
			throw new SQLException("Product deletion failed");
		}

		connection.commit();

		return true;
	}
}
