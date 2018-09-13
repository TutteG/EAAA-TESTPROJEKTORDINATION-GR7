package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import controller.Controller;
import model.DagligFast;
import model.Dosis;
import model.Laegemiddel;
import model.Patient;

public class TestDagligFast {
	Patient patient;
	DagligFast ordination;
	DagligFast ordination2;
	Controller controller;

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
		
	}
}
