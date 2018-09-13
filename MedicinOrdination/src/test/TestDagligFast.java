package test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import controller.Controller;
import jdk.nashorn.internal.ir.annotations.Ignore;
import model.DagligFast;
import model.Dosis;
import model.Laegemiddel;
import model.Patient;

public class TestDagligFast {
	Patient patient;
	DagligFast ordination;
	DagligFast ordination2;
	DagligFast ordination3;
	Controller controller;
	Dosis dosis1;
	Dosis dosis2;
	Dosis dosis3;

	@Before
	public void setUp() throws Exception {
		patient = new Patient("12345-6789", "Niels Ottosen", 87.3);
		ordination = new DagligFast(LocalDate.now(), LocalDate.now().plusDays(0), patient);
		Dosis dosis1 = ordination.createDosis(LocalTime.now(), 1);
		Dosis dosis2 = ordination.createDosis(LocalTime.now().plusMinutes(60), 6);
		Dosis dosis3 = ordination.createDosis(LocalTime.now().plusHours(2), 2);

	}

	@Test
	public void testSamletDosis() {
		assertEquals(0, ordination.samletDosis(), 0.001);
		ordination.setSlutDen(LocalDate.now().plusDays(1));
		assertEquals(9, ordination.samletDosis(), 0.001);
		ordination.setSlutDen(LocalDate.now().plusDays(60));
		assertEquals(540, ordination.samletDosis(), 0.001);
	}

	@Test
	public void testSamletDosisException() {
		try {
			ordination.setSlutDen(LocalDate.now().minusDays(1));
			assertEquals(0, ordination.samletDosis(), 0.001);
		} catch (RuntimeException e) {
			assertEquals(e.getMessage(), "No es possiblé");
		}

	}

	@Test
	public void testDoegnDosis() {
		ordination2 = new DagligFast(LocalDate.now(), LocalDate.now().plusDays(0), patient);
		assertEquals(0, ordination2.doegnDosis(), 0.001);
		Dosis dosis01 = ordination2.createDosis(LocalTime.now(), 1);
		assertEquals(1.0, ordination2.doegnDosis(), 0.001);
		Dosis dosis02 = ordination2.createDosis(LocalTime.now(), 1);
		Dosis dosis03 = ordination2.createDosis(LocalTime.now(), 1);
		Dosis dosis04 = ordination2.createDosis(LocalTime.now(), 1);
		assertEquals(4, ordination2.doegnDosis(), 0.001);
	}

	@Test
	public void testGetType() {
		Laegemiddel mg = controller.getService().opretLaegemiddel("Vinopyl", 50.0, 60.0, 70.0, "mg");
		ordination.setLaegemiddel(mg);
		assertEquals("mg", ordination.getType());
		Laegemiddel dråber = controller.getService().opretLaegemiddel("Rhynoldahl", 50.0, 60.0, 70.0, "dr�ber");
		ordination.setLaegemiddel(dråber);
		assertEquals("dr�ber", ordination.getType());
		Laegemiddel rektalSonde = controller.getService().opretLaegemiddel("beer bong", 50.0, 60.0, 70.0,
				"rektalSonde");
		ordination.setLaegemiddel(rektalSonde);
		assertEquals("rektalSonde", ordination.getType());
	}

	@Ignore
	public void testGetDoser() {
		Dosis[] doser = new Dosis[4];
		doser[0] = dosis1;
		doser[1] = dosis2;
		doser[2] = dosis3;
		assertArrayEquals(doser, ordination.getDoser());
	}

	@Test
	public void testCreateDosis() {
		ordination3 = new DagligFast(LocalDate.now(), LocalDate.now().plusDays(0), patient);
		Dosis dosis1 = ordination3.createDosis(LocalTime.of(8, 0), -1);
		assertEquals(null, ordination.createDosis(LocalTime.of(8, 0), -1));
		Dosis dosis2 = ordination3.createDosis(LocalTime.of(8, 0), 0);
		assertEquals(null, ordination.createDosis(LocalTime.of(8, 0), 0));
	}

}
