package test;

import static org.junit.Assert.*;
import org.junit.Test;
import multiverseTour.User;
import product.Attraction;
import product.Offer;

public class UserTest {

	@Test
	public void newUserTest() {

		User God = new User("God", 999, 999);
		User Eowyn = new User("Eowyn", 10, 8);
		User Gandalf = new User("Gandalf", 100, 5);
		User Sam = new User("Sam", 36, 8);

		assertEquals(God.getMoneyAvailability(), 999, 0.001);
		assertEquals(Eowyn.getTimeRequired(), 8, 0.001);
		assertEquals(Gandalf.getName(), "Gandalf");
		assertEquals( 36,Sam.getMoneyAvailability(), 0.001);

	}
	
	@Test
	public void isOffertViableTest() {
		User u = new User ("God",999,999 );
		assertTrue(u.isOffertViable(999, 999));
	}
	
	@Test
	public void acceptSuggestedOfferTest() {
		User u = new User ("God", 999, 999);
		Offer a = new Attraction("Plaza Roja", 99, 2, 600);
		u.acceptSuggestedOffer(a);
		assertEquals(900,u.getMoneyAvailability(), 0.001);
		assertEquals( 997,u.getTimeRequired(), 0.001);
		
	}
	
	@Test
	public void takeOfferTrueTest() {
		Offer o = new Attraction("Plaza Roja", 99, 2, 600);
		User u=new User ("God", 999, 999);
		assertTrue(u.takeOffer(o));
	}
	
	@Test
	public void takeOfferFalseTest() {
		Offer o = new Attraction("Plaza Roja", 99, 2, 600);
		User u=new User ("God", 999, 999);
		assertFalse(u.takeOffer(o));
	}
}
