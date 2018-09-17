package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import controller.Controller;
import jdk.nashorn.internal.ir.annotations.Ignore;
import model.DagligFast;
import model.Laegemiddel;
import model.Ordination;
import model.PN;
import model.Patient;

public class TestController {
	Patient patient;
	Laegemiddel laegemiddel;

	@Before
	public void setUp() {
		patient = new Patient("123456-7890", "Niels Ottosen", 87.3);
		laegemiddel = new Laegemiddel("Rohypnol", 1, 2, 3, "Kilogram");
		Patient p1 = Controller.getService().opretPatient("880614-1234", "Elin Thomsen", 20);
		Patient p2 = Controller.getService().opretPatient("880614-1234", "Elin Thomsen", 30);
		Patient p3 = Controller.getService().opretPatient("880614-1234", "Elin Thomsen", 40);
		Patient p4 = Controller.getService().opretPatient("880614-1234", "Elin Thomsen", 50);
		Patient p5 = Controller.getService().opretPatient("880614-1234", "Elin Thomsen", 60);
		Patient p6 = Controller.getService().opretPatient("880614-1234", "Elin Thomsen", 70);
		Patient p7 = Controller.getService().opretPatient("880614-1234", "Elin Thomsen", 80);
		Patient p8 = Controller.getService().opretPatient("880614-1234", "Elin Thomsen", 90);
		Patient p9 = Controller.getService().opretPatient("880614-1234", "Elin Thomsen", 100);
		Patient p10 = Controller.getService().opretPatient("880614-1234", "Elin Thomsen", 110);
		Patient p11 = Controller.getService().opretPatient("880614-1234", "Elin Thomsen", 120);
		Patient p12 = Controller.getService().opretPatient("880614-1234", "Elin Thomsen", 130);
		Patient p13 = Controller.getService().opretPatient("880614-1234", "Elin Thomsen", 10);
		Patient p14 = Controller.getService().opretPatient("880614-1234", "Elin Thomsen", 65);
		Patient p15 = Controller.getService().opretPatient("880614-1234", "Elin Thomsen", 75);
		Laegemiddel lm1 = Controller.getService().opretLaegemiddel("Panodil", 2.0, 5.0, 3.0, "mg");
		Laegemiddel lm2 = Controller.getService().opretLaegemiddel("Ipren", 3.0, 4.0, 7.0, "mg");
		DagligFast o1 = Controller.getService().opretDagligFastOrdination(LocalDate.now(), LocalDate.now().plusDays(10),
				p1, lm1, 1.0, 1.0, 1.0, 1.0);
		DagligFast o2 = Controller.getService().opretDagligFastOrdination(LocalDate.now(), LocalDate.now().plusDays(10),
				p2, lm1, 1.0, 1.0, 1.0, 1.0);
		DagligFast o3 = Controller.getService().opretDagligFastOrdination(LocalDate.now(), LocalDate.now().plusDays(10),
				p3, lm2, 1.0, 1.0, 1.0, 1.0);
		DagligFast o4 = Controller.getService().opretDagligFastOrdination(LocalDate.now(), LocalDate.now().plusDays(10),
				p4, lm1, 1.0, 1.0, 1.0, 1.0);
		DagligFast o5 = Controller.getService().opretDagligFastOrdination(LocalDate.now(), LocalDate.now().plusDays(10),
				p5, lm1, 1.0, 1.0, 1.0, 1.0);
		DagligFast o6 = Controller.getService().opretDagligFastOrdination(LocalDate.now(), LocalDate.now().plusDays(10),
				p6, lm1, 1.0, 1.0, 1.0, 1.0);
		DagligFast o7 = Controller.getService().opretDagligFastOrdination(LocalDate.now(), LocalDate.now().plusDays(10),
				p7, lm1, 1.0, 1.0, 1.0, 1.0);
		DagligFast o8 = Controller.getService().opretDagligFastOrdination(LocalDate.now(), LocalDate.now().plusDays(10),
				p8, lm2, 1.0, 1.0, 1.0, 1.0);
		DagligFast o9 = Controller.getService().opretDagligFastOrdination(LocalDate.now(), LocalDate.now().plusDays(10),
				p9, lm1, 1.0, 1.0, 1.0, 1.0);
		DagligFast o10 = Controller.getService().opretDagligFastOrdination(LocalDate.now(),
				LocalDate.now().plusDays(10), p10, lm1, 1.0, 1.0, 1.0, 1.0);
		DagligFast o11 = Controller.getService().opretDagligFastOrdination(LocalDate.now(),
				LocalDate.now().plusDays(10), p11, lm1, 1.0, 1.0, 1.0, 1.0);
		DagligFast o12 = Controller.getService().opretDagligFastOrdination(LocalDate.now(),
				LocalDate.now().plusDays(10), p12, lm2, 1.0, 1.0, 1.0, 1.0);
		DagligFast o13 = Controller.getService().opretDagligFastOrdination(LocalDate.now(),
				LocalDate.now().plusDays(10), p13, lm1, 1.0, 1.0, 1.0, 1.0);
		DagligFast o14 = Controller.getService().opretDagligFastOrdination(LocalDate.now(),
				LocalDate.now().plusDays(10), p14, lm1, 1.0, 1.0, 1.0, 1.0);
		DagligFast o15 = Controller.getService().opretDagligFastOrdination(LocalDate.now(),
				LocalDate.now().plusDays(10), p15, lm1, 1.0, 1.0, 1.0, 1.0);
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
	public void testOrdinationPNAnvendtDagUdenforPeriode() {

		try {
			PN ordination = Controller.getService().opretPNOrdination(LocalDate.now(), LocalDate.now().plusDays(1),
					patient, laegemiddel, 1);
			Controller.getService().ordinationPNAnvendt(ordination, LocalDate.now().plusDays(2));

		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			assertEquals(e.getMessage(), "Dato er invalid");
		}
	}

	@Test
	public void testOpretLaegemiddel1() {
		Laegemiddel test = Controller.getService().opretLaegemiddel("Panodil", 2.0, 5.0, 3.0, "mg");
		assertNotNull(test);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testOpretLaegemiddel2() {
		Laegemiddel test = Controller.getService().opretLaegemiddel("Panodil", 2.0, 5.0, -3.0, "mg");
		assertNotNull(test);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testOpretLaegemiddel3() {
		Laegemiddel test = Controller.getService().opretLaegemiddel("Panodil", 2.0, -5.0, 3.0, "mg");
		assertNotNull(test);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testOpretLaegemiddel4() {
		Laegemiddel test = Controller.getService().opretLaegemiddel("Panodil", -2.0, 5.0, 3.0, "mg");
		assertNotNull(test);
	}

	@Test
	public void testOpretLaegemiddel5() {
		Laegemiddel test = Controller.getService().opretLaegemiddel("Panodil", 98.0, 68.0, 98.0, "mg");
		assertNotNull(test);
	}

	@Test
	public void testOpretLaegemiddel6() {
		Laegemiddel test = Controller.getService().opretLaegemiddel("Panodil", 70.0, 80.0, 90.0, "mg");
		assertNotNull(test);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testOpretPatient1() {
		Patient p = Controller.getService().opretPatient("880614-1234", "Elin Thomsen", -5);
		assertNotNull(p);

	}

	@Test
	public void testOpretPatient2() {
		Patient p = Controller.getService().opretPatient("880614-1234", "Elin Thomsen", 0);
		assertNotNull(p);
	}

	@Test
	public void testOpretPatient3() {
		Patient p = Controller.getService().opretPatient("880614-1234", "Elin Thomsen", 25);
		assertNotNull(p);
	}

	@Test
	public void testOpretPatient4() {
		Patient p = Controller.getService().opretPatient("880614-1234", "Elin Thomsen", 50);
		assertNotNull(p);
	}

	@Test
	public void testOpretPatient5() {
		Patient p = Controller.getService().opretPatient("880614-1234", "Elin Thomsen", 120);
		assertNotNull(p);
	}

	@Test
	public void testOpretPatient6() {
		Patient p = Controller.getService().opretPatient("880614-1234", "Elin Thomsen", 150);
		assertNotNull(p);
	}

	@Test
	public void testOpretPatient7() {
		Patient p = Controller.getService().opretPatient("880614-1234", "Elin Thomsen", 800);
		assertNotNull(p);
	}

	@Ignore
	public void antalOrdinationerPrVaegtPrLaegemiddel() {

		// Controller.getService().antalOrdinationerPrVægtPrLægemiddel(25.0, 75.0, )
	}
}
