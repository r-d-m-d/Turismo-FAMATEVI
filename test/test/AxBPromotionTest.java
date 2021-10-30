package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import product.Attraction;
import product.AxBPromotion;

public class AxBPromotionTest {

	@Test
	public void testAxBPromotionConstructor() {
		Attraction LAGO = new Attraction("El lago", 47.0, 2.0, 40);
		Attraction GALLINERO = new Attraction("El gallinero", 93.0, 5.0, 10);
		Attraction MUSEO = new Attraction("El museo maldito", 25.0, 9.0, 9);
		List<Attraction> included=new ArrayList<Attraction>();
		List<Attraction> extra=new ArrayList<Attraction>();
		included.add(LAGO);
		included.add(GALLINERO);
		extra.add(MUSEO);
		AxBPromotion axb = new AxBPromotion("Campo", included, extra);
		assertNotNull(axb);
		
		
	}
	
	@Test
	public void testcheckVacancyTrue() {
		Attraction LAGO = new Attraction("El lago", 47.0, 2.0, 40);
		Attraction GALLINERO = new Attraction("El gallinero", 93.0, 5.0, 10);
		Attraction MUSEO = new Attraction("El museo maldito", 25.0, 9.0, 9);
		List<Attraction> included=new ArrayList<Attraction>();
		List<Attraction> extra=new ArrayList<Attraction>();
		included.add(LAGO);
		included.add(GALLINERO);
		extra.add(MUSEO);
		AxBPromotion axb = new AxBPromotion("Campo", included, extra);
		
		assertTrue(axb.checkVacancy());
	}

}
