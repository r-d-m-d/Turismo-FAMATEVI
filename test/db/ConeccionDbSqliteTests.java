package db;

import static org.junit.Assert.*;

import org.junit.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import db.ConeccionDbSqlite;
public class ConeccionDbSqliteTests {

	@Test
	public void testGetConnectionNotNull() {
		try {
			Connection c=ConeccionDbSqlite.getConnection();
			assertNotNull(c);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
