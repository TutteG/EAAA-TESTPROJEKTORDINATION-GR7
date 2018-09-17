package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.Patient;

public class TestPatient {
	Patient patient;
	
	@Before
	public void setUp() {
		patient = new Patient("2902941593", "Jens Markmager", 92.8);
	}

	@Test
	public void testGetCpr() {
		assertEquals("2902941593", patient.getCprnr());
	}
	
	@Test
	public void testGetNavn() {
		assertEquals("Jens Markmager", patient.getNavn());
	}
	@Test
	public void testSetNavn() {
		patient.setNavn("Richard Canada");
		assertEquals("Richard Canada", patient.getNavn());
	}
	
	@Test
	public void testGetVaegt() {
		assertEquals(92.8, patient.getVaegt(), 0.001);
	}
	@Test
	public void testSetVaegt() {
		patient.setVaegt(87.5);
		assertEquals(87.5, patient.getVaegt(), 0.001);
	}
}
