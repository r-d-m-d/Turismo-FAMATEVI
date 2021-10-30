package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import product.Attraction;
import product.PercentagePromotion;

public class PercentagePromotionTest {

	private static Attraction LAGO = new Attraction("El lago", 47.0, 2.0, 40);
	private static Attraction GALLINERO = new Attraction("El gallinero", 93.0, 5.0, 10);
	private static Attraction MUSEO = new Attraction("El museo maldito", 25.0, 9.0, 9);
	private static Attraction SUSTOS = new Attraction("La casa de los sustos", 19.0, 4.0, 8);
	private static Attraction DUQUE = new Attraction("La mansion del duke de alba", 400.0, 1.0, 7);
	private static Attraction DRACULA = new Attraction("El Castillo de Dracula", 60.0, 2.0, 6);
	private static Attraction UMUMBU = new Attraction("Unumbu", 100.0, 7.0, 54);

	@Test
	public void testGetVisitCost() {
		List<Attraction> aa = new ArrayList<Attraction>();
		aa.add(UMUMBU);
		aa.add(DRACULA);
		aa.add(DUQUE);
		aa.add(SUSTOS);
		aa.add(MUSEO);
		aa.add(GALLINERO);
		aa.add(LAGO);
		PercentagePromotion pp = new PercentagePromotion("Promo de susto", aa, 0.4);
		assertEquals(446, pp.getVisitCost(), 0.01);
	}

	@Test
	public void testHasAnyTrue() {
		List<Attraction> aa = new ArrayList<Attraction>();
		aa.add(UMUMBU);
		aa.add(DRACULA);
		PercentagePromotion pp = new PercentagePromotion("Promo de susto", aa, 0.4);
		assertTrue(pp.hasAny(aa));
	}

	@Test
	public void testHasAnyFalse() {
		List<Attraction> aa = new ArrayList<Attraction>();
		aa.add(UMUMBU);
		aa.add(DRACULA);
		List<Attraction> bb = new ArrayList<Attraction>();
		bb.add(SUSTOS);
		bb.add(MUSEO);
		PercentagePromotion pp = new PercentagePromotion("Promo de susto", aa, 0.4);
		assertFalse(pp.hasAny(bb));
	}

	@Test
	public void testGetName() {
		List<Attraction> aa = new ArrayList<Attraction>();
		aa.add(UMUMBU);
		aa.add(DRACULA);
		PercentagePromotion pp = new PercentagePromotion("Promo de susto", aa, 0.4);
		assertEquals("Promo de susto", pp.getName());
	}

	@Test
	public void testCheckVacancyTrue() {
		List<Attraction> aa = new ArrayList<Attraction>();
		aa.add(UMUMBU);
		aa.add(DRACULA);
		PercentagePromotion pp = new PercentagePromotion("Promo de susto", aa, 0.4);
		pp.subtractAvailability();
		assertTrue(pp.checkVacancy());
	}

	@Test
	public void testCheckVacancyFalse() {
		List<Attraction> aa = new ArrayList<Attraction>();
		aa.add(UMUMBU);
		aa.add(DRACULA);
		PercentagePromotion pp = new PercentagePromotion("Promo de susto", aa, 0.4);
		pp.subtractAvailability();
		pp.subtractAvailability();
		pp.subtractAvailability();
		pp.subtractAvailability();
		pp.subtractAvailability();
		pp.subtractAvailability();
		assertFalse(pp.checkVacancy());
	}

	@Test
	public void testGetTimeRequired() {
		List<Attraction> aa = new ArrayList<Attraction>();
		aa.add(MUSEO);
		aa.add(SUSTOS);
		PercentagePromotion pp = new PercentagePromotion("Promo de susto", aa, 0.4);
		assertEquals(13.0, pp.getTimeRequired(), 0.01);
	}

	@Test
	public void testAppendTo() {
		List<Attraction> o = new ArrayList<Attraction>();
		List<Attraction> aa = new ArrayList<Attraction>();
		aa.add(MUSEO);
		aa.add(SUSTOS);
		PercentagePromotion pp = new PercentagePromotion("Promo de susto", aa, 0.4);
		pp.appendTo(o);
		assertEquals(o, aa);
	}

	@Test
	public void testHasAttractionTrue() {
		List<Attraction> aa = new ArrayList<Attraction>();
		aa.add(MUSEO);
		aa.add(SUSTOS);
		PercentagePromotion pp = new PercentagePromotion("Promo de susto", aa, 0.4);
		assertTrue(pp.hasAttraction(MUSEO));
	}

	@Test
	public void testHasAttractionFalse() {
		List<Attraction> aa = new ArrayList<Attraction>();
		aa.add(MUSEO);
		aa.add(SUSTOS);
		PercentagePromotion pp = new PercentagePromotion("Promo de susto", aa, 0.4);
		assertFalse(pp.hasAttraction(DRACULA));
	}
}
