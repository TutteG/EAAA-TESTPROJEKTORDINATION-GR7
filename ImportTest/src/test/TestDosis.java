package test;

import static org.junit.Assert.assertEquals;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import model.Dosis;
import model.Laegemiddel;

public class TestDosis {
	Dosis dosis;
	
	@Before
	public void setUp() {
		dosis = new Dosis(LocalTime.of(8, 0), 1.0);
	}

	@Test
	public void testGetAntal() {
		assertEquals(1.0, dosis.getAntal(), 0.001);
	}
	
	@Test
	public void testSetAntal() {
		dosis.setAntal(2.0);
		assertEquals(2.0, dosis.getAntal(), 0.001);
	}
	
	@Test
	public void testGetTid() {
		assertEquals(LocalTime.of(8, 0), dosis.getTid());
	}
	
	@Test
	public void testSetTid() {
		dosis.setTid(LocalTime.of(9, 0));
		assertEquals(LocalTime.of(9, 0), dosis.getTid());
	}
}
