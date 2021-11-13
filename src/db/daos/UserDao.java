package db.daos;

import java.sql.Connection;
import java.util.LinkedList;

import multiverseTour.User;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao {
	public LinkedList<User> findAll() {
		Connection conn = db.ConeccionDbSqlite.getConnection();
		String consulta = "Select name,money,time from users";
		Statement stmt;
		LinkedList<User> listOfUsers = new LinkedList<User>();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(consulta);
			while (rs.next()) {
				User u = new User(rs.getString("name"), (int) rs.getDouble("money"), rs.getDouble("time"));
				listOfUsers.add(u);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listOfUsers;
	}
	int countUsers() {
		Connection conn = db.ConeccionDbSqlite.getConnection();
		String consulta = "Select count(1) AS numero_de_usuarios from users";
		Statement stmt;
		int numberOfUsers=0;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(consulta);
			rs.next() ;
			numberOfUsers=rs.getInt("numero_de_usuarios");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return numberOfUsers;
	}
}
