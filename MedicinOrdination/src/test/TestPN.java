package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import model.PN;
import model.Patient;

public class TestPN {
	Patient patient;
	PN pn;

	@Before
	public void setUp() {
		patient = new Patient("12345-6789", "Niels Ottosen", 87.3);
		pn = new PN(LocalDate.now(), LocalDate.now().plusDays(7), patient, 3);

	}

	@Test
	public void testGivDosisMinus() {
		LocalDate ld1 = LocalDate.now().minusDays(1);
		assertFalse(pn.givDosis(ld1));
	}

	@Test
	public void testGivDosisZero() {
		LocalDate ld = LocalDate.now();
		assertTrue(pn.givDosis(ld));
	}

	@Test
	public void testGivDosisPlus4() {
		LocalDate ld3 = LocalDate.now().plusDays(4);
		assertTrue(pn.givDosis(ld3));
	}

	@Test
	public void testGivDosisPlus7() {
		LocalDate ld4 = LocalDate.now().plusDays(7);
		assertTrue(pn.givDosis(ld4));
	}

	@Test
	public void testGivDosisPlus8() {
		LocalDate ld5 = LocalDate.now().plusDays(8);
		assertFalse(pn.givDosis(ld5));
	}

	@Test
	public void testDoegnDosis1() {
		try {
			pn = new PN(LocalDate.now(), LocalDate.now(), patient, 1);
		} catch (ArithmeticException e) {
			assertEquals("No es possiblé", e.getMessage());
		}
	}

	@Test
	public void testDoegnDosis2() {
		pn = new PN(LocalDate.now(), LocalDate.now().plusDays(5), patient, 0);
		assertEquals(0, pn.doegnDosis(), 00.1);

	}

	@Test
	public void testDoegnDosis3() {
		pn = new PN(LocalDate.now(), LocalDate.now().plusDays(7), patient, 5);
		try {	
			for (int i = 0; i < 5; i++) {
				pn.givDosis(LocalDate.now());
			}
		} catch (ArithmeticException e) {
			assertEquals("No es possiblé", e.getMessage());
		}
	}

	@Test
	public void testDoegnDosis4() {
		pn = new PN(LocalDate.now(), LocalDate.now().plusDays(1), patient, 1);
		pn.givDosis(LocalDate.now());
		assertEquals(1, pn.doegnDosis(), 00.1);
	}

	@Test
	public void testDoegnDosis5() {
		pn = new PN(LocalDate.now().minusDays(5), LocalDate.now(), patient, 1);
		try {
		for (int i = 0; i < 10; i++) {
			pn.givDosis(LocalDate.now());
		}
		}catch(ArithmeticException e) {
		assertEquals("No es possiblé", e.getMessage());
		}
	}

}
