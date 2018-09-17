package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import controller.Controller;
import model.Laegemiddel;
import model.Ordination;
import model.Patient;

public class TestController {
	Patient patient;
	Laegemiddel laegemiddel;

	@Before
	public void setUp() {
		patient = new Patient("123456-7890", "Niels Ottosen", 87.3);
		laegemiddel = new Laegemiddel("Rohypnol", 1, 2, 3, "Kilogram");
	}

	@Test
	public void testOpretPNOrdinationSlutdatoFoerStartdato() {
		try {
			Controller.getService().opretPNOrdination(LocalDate.now(), LocalDate.now().minusDays(1), patient,
					laegemiddel, 1);
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			assertEquals(e.getMessage(), "Slutdato er foer startdato");
		}
	}

	@Test
	public void testOpretPNOrdinationNotNull() {
		Ordination ordination = Controller.getService().opretPNOrdination(LocalDate.now(), LocalDate.now(), patient,
				laegemiddel, 1);
		assertNotNull(ordination);
	}

	@Test
	public void testOpretDagligFastOrdinationNotNull() {

	}
}
