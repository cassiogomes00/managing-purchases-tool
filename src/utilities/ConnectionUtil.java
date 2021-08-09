package utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionUtil {
	private Connection connection;

	public ConnectionUtil(Connection connection) {
		this.connection = connection;
	}

	public Connection getConnection() {
		return this.connection;
	}

	public PreparedStatement getPreparedStatement(String query) throws ClassNotFoundException, SQLException {
		Connection connection = this.getConnection();
		PreparedStatement prepStatement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE,
				ResultSet.CONCUR_UPDATABLE);

		return prepStatement;
	}
}
