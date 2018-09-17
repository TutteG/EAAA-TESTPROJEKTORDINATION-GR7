package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import model.Laegemiddel;

public class TestLaegemiddel {
	Laegemiddel laegemiddel;
	
	@Before
	public void setUp() {
		laegemiddel = new Laegemiddel("Riptonhol", 0.2, 0.5, 0.8, "Liter");
	}

	@Test
	public void testGetNavn() {
		assertEquals("Riptonhol", laegemiddel.getNavn());
	}
	
	@Test
	public void testGetEnhed() {
		assertEquals("Liter", laegemiddel.getEnhed());
	}
	
	@Test
	public void testGetEnhedPrKgPrDoegnLet() {
		assertEquals(0.2, laegemiddel.getEnhedPrKgPrDoegnLet(), 0.001);
	}
	@Test
	public void testGetEnhedPrKgPrDoegnNormal() {
		assertEquals(0.5, laegemiddel.getEnhedPrKgPrDoegnNormal(), 0.001);
	}
	@Test
	public void testGetEnhedPrKgPrDoegnTung() {
		assertEquals(0.8, laegemiddel.getEnhedPrKgPrDoegnTung(), 0.001);
	}
}
