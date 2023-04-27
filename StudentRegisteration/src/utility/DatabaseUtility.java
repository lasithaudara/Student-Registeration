package utility;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseUtility {

	public Connection databaseLink;

	public Connection getConnection() {
		String databaseName = "student_registeration";
		String databaseUser = "root";
		String databasePassword = "math.hpunura@12345";
		String url = "jdbc:mysql://localhost/" + databaseName;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return databaseLink;

	}
}
