package com.qa.FizzyDrinks.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FizzyDrinkTest {

	private FizzyDrink testFizzy = new FizzyDrink(1L, "Fanta", "Orange", 10, "orange");

	@Test
	public void testNoArgsConstructor() {
		FizzyDrink noArgFizzy = new FizzyDrink();
		assertEquals(null, noArgFizzy.getId());
		assertEquals(null, noArgFizzy.getBrand());
		assertEquals(null, noArgFizzy.getName());
		assertEquals(0, noArgFizzy.getSugarContent());
		assertEquals(null, noArgFizzy.getFlavour());
	}

	@Test
	public void testArgsConstructor() {
		FizzyDrink argFizzy = new FizzyDrink("Fanta", "Orange", 10, "orange");
		assertEquals(null, argFizzy.getId());
		assertEquals("Fanta", argFizzy.getBrand());
		assertEquals("Orange", argFizzy.getName());
		assertEquals(10, argFizzy.getSugarContent());
		assertEquals("orange", argFizzy.getFlavour());
	}

	@Test
	public void testToString() {
		assertEquals("FizzyDrink [id=1, brand=Fanta, name=Orange, sugarContent=10, flavour=orange]",
				testFizzy.toString());
	}
}