package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.time.LocalTime;

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
	public void testOpretDagligFastOrdinationNotNulZeroDays() {
		Ordination ordination = Controller.getService().opretDagligFastOrdination(LocalDate.now(),
				LocalDate.now().plusDays(0), patient, laegemiddel, 1, 2, 3, 4);
		assertNotNull(ordination);
	}

	@Test
	public void testOpretDagligFastOrdinationNotNullOneDay() {
		Ordination ordination = Controller.getService().opretDagligFastOrdination(LocalDate.now(),
				LocalDate.now().plusDays(1), patient, laegemiddel, 1, 2, 3, 4);
		assertNotNull(ordination);
	}

	@Test
	public void testOpretDagligFastOrdinationSlutdatoFoerStartdato() {

		try {

			Ordination ordination = Controller.getService().opretDagligFastOrdination(LocalDate.now(),
					LocalDate.now().minusDays(1), patient, laegemiddel, 1, 2, 3, 4);
		} catch (Exception e) {
			assertEquals(e.getMessage(), "Startdato må ikke være efter slutdato");
		}
	}

	@Test
	public void testOpretDagligSkaevOrdinationNotNullZeroDays() {
		LocalTime[] timeArray = { LocalTime.now(), LocalTime.now().plusHours(2), LocalTime.now().plusHours(4),
				LocalTime.now().plusHours(6) };
		double[] doubleArray = { 1.0, 1.0, 1.0, 1.0 };
		Ordination ordination = Controller.getService().opretDagligSkaevOrdination(LocalDate.now(), LocalDate.now(),
				patient, laegemiddel, timeArray, doubleArray);
		assertNotNull(ordination);
	}

	@Test
	public void testOpretDagligSkaevOrdinationNotNullOneDay() {
		LocalTime[] timeArray = { LocalTime.now(), LocalTime.now().plusHours(2), LocalTime.now().plusHours(4),
				LocalTime.now().plusHours(6) };
		double[] doubleArray = { 1.0, 1.0, 1.0, 1.0 };
		Ordination ordination = Controller.getService().opretDagligSkaevOrdination(LocalDate.now(),
				LocalDate.now().plusDays(1), patient, laegemiddel, timeArray, doubleArray);
		assertNotNull(ordination);
	}

	@Test
	public void testOpretDagligSkaevOrdinationSlutdatoFoerStartdato() {
		try {
			LocalTime[] timeArray = { LocalTime.now(), LocalTime.now().plusHours(2), LocalTime.now().plusHours(4),
					LocalTime.now().plusHours(6) };
			double[] doubleArray = { 1.0, 1.0, 1.0, 1.0 };
			Ordination ordination = Controller.getService().opretDagligSkaevOrdination(LocalDate.now(),
					LocalDate.now().minusDays(1), patient, laegemiddel, timeArray, doubleArray);
			assertNotNull(ordination);
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(),
					"Startdato skal være før slutdato, klokkeslet og antalEnheder skal have samme længde");
		}
	}

	@Test
	public void testOpretDagligSkaevOrdinationArrayWithDifferentLength() {
		try {
			LocalTime[] timeArray = { LocalTime.now(), LocalTime.now().plusHours(2), LocalTime.now().plusHours(4),
					LocalTime.now().plusHours(6) };
			double[] doubleArray = { 1.0, 1.0, 1.0, 1.0, 1, 0 };
			Ordination ordination = Controller.getService().opretDagligSkaevOrdination(LocalDate.now(), LocalDate.now(),
					patient, laegemiddel, timeArray, doubleArray);
			assertNotNull(ordination);
		} catch (IllegalArgumentException e) {
			assertEquals(e.getMessage(),
					"Startdato skal være før slutdato, klokkeslet og antalEnheder skal have samme længde");
		}
	}

	@Test
	public void testOrdinationPNAnvendtDagIndenforPeriode() {

	}

	@Test
	public void testOrdinationPNAnvendtDagUdenforPeriode() {

	}
}
