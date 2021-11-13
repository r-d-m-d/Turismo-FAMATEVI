package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConeccionDbSqlite {
	private static Connection conn = null;

	public static Connection getConnection() {
		if (conn == null)
			try {
				conn = DriverManager.getConnection("jdbc:sqlite:TP2.bd");
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				throw new Error("No se encontro la base de datos");
			}
		return conn;
	}
}
