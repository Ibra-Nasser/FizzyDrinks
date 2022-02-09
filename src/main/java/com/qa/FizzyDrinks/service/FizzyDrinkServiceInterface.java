package com.qa.FizzyDrinks.service;

import java.util.List;

public interface FizzyDrinkServiceInterface<T> {
	// Create Object
	T createFizzyDrink(T t);

	// Read All
	List<T> getFizzyDrink();

	// Read by ID
	T getFizzyDrinkById(Long Id);

	// Update by Object ID
	T updateFizzyDrinkById(T t, Long Id);

	// Delete - return boolean
	T removeFizzyDrink(Long Id);

}
