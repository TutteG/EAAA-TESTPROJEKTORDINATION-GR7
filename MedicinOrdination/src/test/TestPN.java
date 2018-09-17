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

	@Before
	public void setUp() {
		patient = new Patient("12345-6789", "Niels Ottosen", 87.3);

	}

	@Test
	public void testGivDosis() {
		LocalDate ld1 = LocalDate.now().minusDays(-1);
		LocalDate ld2 = LocalDate.now();
		LocalDate ld3 = LocalDate.now().plusDays(4);
		LocalDate ld4 = LocalDate.now().plusDays(7);
		LocalDate ld5 = LocalDate.now().plusDays(8);
		PN pn = new PN(LocalDate.now(), LocalDate.now().plusDays(7), patient, 3);
		assertFalse(pn.givDosis(ld1));
		assertTrue(pn.givDosis(ld2));
		assertTrue(pn.givDosis(ld3));
		assertTrue(pn.givDosis(ld4));
		assertFalse(pn.givDosis(ld5));
	}

	@Test
	public void testDoegnDosis1() {
		PN pn1 = new PN(LocalDate.now(), LocalDate.now().plusDays(7), patient, 1);
		assertEquals(-1, pn1.doegnDosis());
	}

	@Test
	public void testDoegnDosis2() {
		PN pn1 = new PN(LocalDate.now().minusDays(5), LocalDate.now().plusDays(7), patient, 1);
		assertEquals(0, pn1.doegnDosis(), 00.1);

	}

	@Test
	public void testDoegnDosis3() {
		PN pn1 = new PN(LocalDate.now(), LocalDate.now().plusDays(7), patient, 1);
		for (int i = 0; i < 5; i++) {
			pn1.givDosis(LocalDate.now());
		}
		assertEquals(-1, pn1.doegnDosis(), 00.1);
	}

	@Test
	public void testDoegnDosis4() {
		PN pn1 = new PN(LocalDate.now(), LocalDate.now().plusDays(1), patient, 1);
		pn1.givDosis();
		assertEquals(1, pn1.doegnDosis(), 00.1);
	}

	@Test
	public void testDoegnDosis5() {
		PN pn1 = new PN(LocalDate.now().minusDays(5), LocalDate.now(), patient, 1);
		for (int i = 0; i < 10; i++) {
			pn1.givDosis(LocalDate.now());
		}
		assertEquals(-1, pn1.doegnDosis(), 00.1);
	}

}
