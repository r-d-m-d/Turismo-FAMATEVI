package db.daos;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

import multiverseTour.User;

public class UserDaoTests {

	@Test
	public void testFindAll() {
		LinkedList<User> ud=(new UserDao()).findAll();
		assertNotNull(ud);
	}
	@Test
	public void testFindAllDevuelveTodos() {
		LinkedList<User> ud=(new UserDao()).findAll();
		assertEquals(ud.size(),(new UserDao()).countUsers());
	}
}
