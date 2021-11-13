package db.daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import multiverseTour.User;
import product.Attraction;

public class AttractionDao {
	public LinkedList<Attraction> findAll() {
		Connection conn = db.ConeccionDbSqlite.getConnection();
		String consulta = "SELECT nombre,costo,tiempo,cupo FROM attractions ORDER BY costo DESC,tiempo DESC;";
		Statement stmt;
		LinkedList<Attraction> attractions = new LinkedList<Attraction>();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(consulta);
			while (rs.next()) {
				System.out.println(rs.getString("nombre")+ rs.getDouble("costo")+
						rs.getDouble("tiempo")+(int) rs.getDouble("cupo"));
				Attraction a = new Attraction(rs.getString("nombre"), rs.getDouble("costo"),
						rs.getDouble("tiempo"),(int) rs.getDouble("cupo"));
				attractions.add(a);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return attractions;
	}

	int countAttractions() {
		Connection conn = db.ConeccionDbSqlite.getConnection();
		String consulta = "Select count(1) AS numero_de_usuarios from users";
		Statement stmt;
		int numberOfUsers = 0;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(consulta);
			rs.next();
			numberOfUsers = rs.getInt("numero_de_usuarios");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return numberOfUsers;
	}
}
