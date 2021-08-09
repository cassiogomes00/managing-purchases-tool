package utilities;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtil {
	private String url;
	private String user;
	private String password;

	public JDBCUtil(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
	}

	public JDBCUtil(Properties props) {
		this.url = props.getProperty("url");
		this.user = props.getProperty("user");
		this.password = props.getProperty("password");
	}

	public JDBCUtil(String filePath) throws IOException {
		FileUtil fileUtil = new FileUtil(filePath);
		Properties props = fileUtil.getProperties();

		this.url = props.getProperty("url");
		this.user = props.getProperty("user");
		this.password = props.getProperty("password");
	}

	public String getUrl() {
		return url;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public Connection getConnection() throws SQLException, ClassNotFoundException {
		String url = this.getUrl();
		String user = this.getUser();
		String password = this.getPassword();
		Connection connection = (Connection) DriverManager.getConnection(url, user, password);

		connection.setAutoCommit(false);

		return connection;
	}

}
