package test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import input.files.InputFile;
import product.Attraction;

public class AttractionTest {

	@Test(expected = FileNotFoundException.class)
	public void testInputFile() throws FileNotFoundException {
		InputFile.loadFile("qjwieqweqw", null, null);
	}

	@Test
	public void testAttractionConstructor() {
		Attraction a = new Attraction("Plaza Roja", 1400, 2, 600);
		assertNotNull(a);
	}

	@Test
	public void testtoString() {
		Attraction a = new Attraction("Plaza Roja", 1400, 2, 600);
		assertEquals("Plaza Roja\t$1400.0\t2.0 horas\n", a.toString());
	}

	@Test
	public void testgetName() {
		Attraction a = new Attraction("Plaza Roja", 1400, 2, 600);
		assertEquals("Plaza Roja", a.getName());
	}

	@Test
	public void testgetVisitCost() {
		Attraction a = new Attraction("Plaza Roja", 1400, 2, 600);
		assertEquals(1400, a.getVisitCost(), 0.01);
	}

	@Test
	public void testcheckVacancyTrue() {
		Attraction a = new Attraction("Plaza Roja", 1400, 2, 600);
		assertTrue(a.checkVacancy());
	}

	@Test
	public void testcheckVacancyFalse() {
		Attraction a = new Attraction("Mirador", 1400, 2, 2);
		a.subtractAvailability();
		a.subtractAvailability();
		assertFalse(a.checkVacancy());
	}

	@Test
	public void testgetTimeRequired() {
		Attraction a = new Attraction("Mirador", 1400, 2, 2);
		assertEquals(2, a.getTimeRequired(), 0.01);
	}

	@Test
	public void testappendTo() {
		Attraction a = new Attraction("Mirador", 1400, 2, 2);
		List<Attraction> b = new ArrayList<Attraction>();
		a.appendTo(b);
		assertEquals(a, b.get(0));
	}

}
