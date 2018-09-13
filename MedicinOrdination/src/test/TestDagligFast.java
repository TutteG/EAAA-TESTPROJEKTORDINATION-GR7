package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import model.DagligFast;
import model.Dosis;
import model.Patient;

public class TestDagligFast {
	Patient patient;
	DagligFast ordination;

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
			
		} catch (RuntimeException e) {
			assertEquals(e.getMessage(), "For mange karakterer givet");
		}

	}
}
