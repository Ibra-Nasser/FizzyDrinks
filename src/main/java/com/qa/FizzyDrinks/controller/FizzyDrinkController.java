package com.qa.FizzyDrinks.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.FizzyDrinks.domain.FizzyDrink;
import com.qa.FizzyDrinks.service.FizzyDrinkService;

@RestController
@RequestMapping(path = "/api")
public class FizzyDrinkController {

	private FizzyDrinkService service;

	public FizzyDrinkController(FizzyDrinkService service) {
		this.service = service;
	}

	// Create Object
	@PostMapping("/create")
	public ResponseEntity<FizzyDrink> createFizzyDrink(@RequestBody FizzyDrink fizzyDrink) {

		return new ResponseEntity<FizzyDrink>(this.service.createFizzyDrink(fizzyDrink), HttpStatus.CREATED);
	}

//	// Read All
	@GetMapping("/readAll")
	public ResponseEntity<List<FizzyDrink>> getFizzyDrink() {
		return new ResponseEntity<>(this.service.getFizzyDrink(), HttpStatus.FOUND);
	}

//	// Read by ID
	@GetMapping("/readById/{Id}")
	public ResponseEntity<FizzyDrink> getFizzyDrinkById(@PathVariable Long Id) {
		FizzyDrink returnVal = this.service.getFizzyDrinkById(Id);
		return returnVal != null ? new ResponseEntity<>(returnVal, HttpStatus.FOUND)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
//	// Update by Object ID

	@PutMapping("/update/{Id}")

	public ResponseEntity<FizzyDrink> updateFizzyDrinkById(@RequestBody FizzyDrink fizzyDrink, @PathVariable Long Id) {
		FizzyDrink returnVal = this.service.updateFizzyDrinkById(fizzyDrink, Id);
		return returnVal != null ? new ResponseEntity<>(returnVal, HttpStatus.ACCEPTED)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

//
//	// Delete - return boolean
	@DeleteMapping("/delete/{Id}")
	public ResponseEntity<FizzyDrink> removeFizzyDrink(@PathVariable Long Id) {
		FizzyDrink returnVal = this.service.removeFizzyDrink(Id);
		return returnVal != null ? new ResponseEntity<>(returnVal, HttpStatus.ACCEPTED)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
