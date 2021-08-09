import java.sql.Connection;
import java.util.Date;

import utilities.*;
import view.StartPage;
import model.*;
import controller.*;

public class App {
	public static void main(String[] args) {
		try {
			JDBCUtil jdbcUtil = new JDBCUtil("./dbSettings.properties");
			Connection connection = jdbcUtil.getConnection();

			new StartPage(connection);
		} catch (Exception e) {
			System.out.println("Exception message: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
