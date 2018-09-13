package test;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import model.DagligSkaev;
import model.Dosis;
import model.Patient;

public class TestDagligSkaev {

	Patient p;
	DagligSkaev ds;
	Dosis dosis1;
	Dosis dosis2;
	Dosis dosis3;

	@Before
	public void setUp() {
		p = new Patient("123456-7890", "Niels Ottosen", 87.3);
		ds = new DagligSkaev(LocalDate.now(), LocalDate.now().plusDays(7), p);
		dosis1 = ds.createDosis(LocalTime.of(8, 0), 1);
		dosis2 = ds.createDosis(LocalTime.of(12, 0), 6);
		dosis3 = ds.createDosis(LocalTime.of(18, 0), 2);
	}

	@Test
	public void testSamletDosis() {

	}
}
