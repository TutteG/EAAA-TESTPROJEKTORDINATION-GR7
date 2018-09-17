package test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
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
	DagligFast ordinationTemp;
	Controller controller;
	Dosis dosis1;
	Dosis dosis2;
	Dosis dosis3;
	Dosis dosis4;

	@Before
	public void setUp() throws Exception {
		patient = new Patient("12345-6789", "Niels Ottosen", 87.3);
		ordination = new DagligFast(LocalDate.now(), LocalDate.now().plusDays(0), patient);
		ordinationTemp = new DagligFast(LocalDate.now(), LocalDate.now().plusDays(0), patient);
		dosis1 = ordination.createDosis(LocalTime.now(), 1);
		dosis2 = ordination.createDosis(LocalTime.now().plusMinutes(60), 6);
		dosis3 = ordination.createDosis(LocalTime.now().plusHours(2), 2);

	}

	
	@Test
	public void testSamletDosis() {
		assertEquals(0, ordination.samletDosis(), 0.001);
	}
	@Test
	public void testSamletDosisPlus1() {
		ordination.setSlutDen(LocalDate.now().plusDays(1));
		assertEquals(9, ordination.samletDosis(), 0.001);
	}
	@Test
	public void testSamletDosisPlus60() {
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
		assertEquals(0, ordinationTemp.doegnDosis(), 0.001);
	}
	@Test
	public void testDowgnDosis1() {
		dosis1 = ordinationTemp.createDosis(LocalTime.now(), 1);
		assertEquals(1.0, ordinationTemp.doegnDosis(), 0.001);
	}
	@Test
	public void testDoegnDosis4() {
		dosis1 = ordinationTemp.createDosis(LocalTime.now(), 1);
		dosis2 = ordinationTemp.createDosis(LocalTime.now(), 1);
		dosis3 = ordinationTemp.createDosis(LocalTime.now(), 1);
		dosis4 = ordinationTemp.createDosis(LocalTime.now(), 1);
		assertEquals(4, ordinationTemp.doegnDosis(), 0.001);
	}

	
	@Test
	public void testGetTypeMg() {
		Laegemiddel mg = controller.getService().opretLaegemiddel("Vinopyl", 50.0, 60.0, 70.0, "mg");
		ordination.setLaegemiddel(mg);
		assertEquals("mg", ordination.getType());
	}
	@Test
	public void testGetTypeDråber() {
		Laegemiddel dråber = controller.getService().opretLaegemiddel("Rhynoldahl", 50.0, 60.0, 70.0, "dråber");
		ordination.setLaegemiddel(dråber);
		assertEquals("dråber", ordination.getType());
	}
	@Test
	public void testGetTypeStikPille() {
		Laegemiddel stikPille = controller.getService().opretLaegemiddel("Probeitohl", 50.0, 60.0, 70.0, "Stik pille");
		ordination.setLaegemiddel(stikPille);
		assertEquals("Stik pille", ordination.getType());
	}

	
	@Test
	public void testGetDoser() {
		Dosis[] doser = new Dosis[4];
		doser[0] = dosis1;
		doser[1] = dosis2;
		doser[2] = dosis3;
		assertArrayEquals(doser, ordination.getDoser());
	}

	
	@Test
	public void testCreateDosisNullMinus() {
		assertEquals(null, ordination.createDosis(LocalTime.of(8, 0), -1));
	}
	@Test
	public void testCreateDosisNullZero() {
		assertEquals(null, ordination.createDosis(LocalTime.of(8, 0), 0));
	}	
	@Test
	public void testCreateDosisAntal1() {
		Dosis dosis = ordinationTemp.createDosis(LocalTime.of(8, 0), 1);
		assertEquals(dosis, ordination.createDosis(LocalTime.of(8, 0), 1));
	}
	@Test
	public void testCreateDosisAntal2() {
		Dosis dosis = ordinationTemp.createDosis(LocalTime.of(8, 0), 5);
		assertEquals(dosis, ordination.createDosis(LocalTime.of(8, 0), 5));
	}

}
