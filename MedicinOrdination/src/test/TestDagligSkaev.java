package test;

import static org.junit.Assert.assertEquals;

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
		ds = new DagligSkaev(LocalDate.now(), LocalDate.now(), p);
		dosis1 = ds.createDosis(LocalTime.of(8, 0), 1);
		dosis2 = ds.createDosis(LocalTime.of(12, 0), 6);
		dosis3 = ds.createDosis(LocalTime.of(18, 0), 7);
		dosis3 = ds.createDosis(LocalTime.of(18, 0), 3);
		dosis3 = ds.createDosis(LocalTime.of(18, 0), 2);
	}

	@Test
	public void testSamletDosis() {
		assertEquals(0, ds.samletDosis(), 0.001);
		ds.setSlutDen(LocalDate.now().plusDays(1));
		assertEquals(19, ds.samletDosis(), 0.001);
		ds.setSlutDen(LocalDate.now().plusDays(60));
		assertEquals(1140, ds.samletDosis(), 0.001);
	}

	@Test
	public void testSamletDosisFail() {
		try {
			ds.setSlutDen(LocalDate.now().minusDays(1));
			assertEquals(0, ds.samletDosis(), 0.001);
		} catch (RuntimeException e) {
			// TODO: handle exception
			assertEquals(e.getMessage(), "No es possiblé");
		}
	}

	@Test
	public void testDoegnDosis() {
		DagligSkaev skaev = new DagligSkaev(LocalDate.now(), LocalDate.now(), p);
		assertEquals(0, skaev.doegnDosis(), 0.001);
		dosis1 = skaev.createDosis(LocalTime.of(8, 0), 1);
		assertEquals(1, skaev.doegnDosis(), 0.001);
		dosis2 = skaev.createDosis(LocalTime.of(8, 0), 9);
		assertEquals(10, skaev.doegnDosis(), 0.001);
	}

	@Test
	public void testDoegnDosisFail() {
		try {
			DagligSkaev skaev = new DagligSkaev(LocalDate.now(), LocalDate.now(), p);
			dosis1 = skaev.createDosis(LocalTime.of(8, 0), -1);
			assertEquals(90, skaev.doegnDosis(), 0.001);
		} catch (RuntimeException e) {
			// TODO: handle exception
			assertEquals(e.getMessage(), "Antal skal være et positivt tal");
		}
		try {
			DagligSkaev skaev = new DagligSkaev(LocalDate.now(), LocalDate.now(), p);
			dosis1 = skaev.createDosis(LocalTime.of(8, 0), 0);
			assertEquals(90, skaev.doegnDosis(), 0.001);
		} catch (RuntimeException e) {
			// TODO: handle exception
			assertEquals(e.getMessage(), "Antal skal være et positivt tal");
		}
	}

	@Test
	public void testCreateDosis() {
		Dosis dosis10 = ds.createDosis(LocalTime.of(8, 0), 1);
		assertEquals(1, dosis10.getAntal(), 0.001);

		dosis10 = ds.createDosis(LocalTime.of(8, 0), 50);
		assertEquals(50, dosis10.getAntal(), 0.001);
	}

	@Test
	public void testCreateDosisFail() {
		try {
			Dosis dosis10 = ds.createDosis(LocalTime.of(8, 0), 0);
			assertEquals(1, dosis10.getAntal(), 0.001);

		} catch (RuntimeException e) {
			// TODO: handle exception
			assertEquals(e.getMessage(), "Antal skal være et positivt tal");

		}
		try {
			Dosis dosis10 = ds.createDosis(LocalTime.of(8, 0), -1);
			assertEquals(1, dosis10.getAntal(), 0.001);

		} catch (RuntimeException e) {
			// TODO: handle exception
			assertEquals(e.getMessage(), "Antal skal være et positivt tal");

		}

	}
}