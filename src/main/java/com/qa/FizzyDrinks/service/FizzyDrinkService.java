package com.qa.FizzyDrinks.service;

import java.util.List;
import java.util.Optional;

import com.qa.FizzyDrinks.domain.FizzyDrink;
import com.qa.FizzyDrinks.repo.FizzyDrinkRepo;

public class FizzyDrinkService implements FizzyDrinkServiceInterface<FizzyDrink> {

	private FizzyDrinkRepo repo;

	public FizzyDrinkService(FizzyDrinkRepo repo) {
		this.repo = repo;
	}

	@Override
	public FizzyDrink createFizzyDrink(FizzyDrink fizzyDrink) {
		return this.repo.save(fizzyDrink);

	}

	@Override
	public List<FizzyDrink> getFizzyDrink() {
		return this.repo.findAll();
	}

	@Override
	public FizzyDrink getFizzyDrinkById(Integer Id) {
		Optional<FizzyDrink> optionalFizzyDrink = this.repo.findById(Id);
		if (optionalFizzyDrink.isPresent()) {
			return optionalFizzyDrink.get();
		}
		return null;
	}

	@Override
	public FizzyDrink updateFizzyDrinkById(FizzyDrink fizzyDrink, Integer Id) {
		Optional<FizzyDrink> optionalFizzyDrink = this.repo.findById(Id);
		FizzyDrink oldFizzy = optionalFizzyDrink.get();
		oldFizzy.setBrand(fizzyDrink.getBrand());
		oldFizzy.setFlavour(fizzyDrink.getFlavour());
		oldFizzy.setName(fizzyDrink.getName());
		oldFizzy.setSugarContent(fizzyDrink.getSugarContent());
		return this.repo.save(oldFizzy);
	}

	@Override
	public FizzyDrink removeFizzyDrink(Integer Id) {
		Optional<FizzyDrink> optionalFizzy = this.repo.findById(Id);
		if (this.repo.existsById(Id) == true) {
			this.repo.deleteById(Id);
			return optionalFizzy.get();
		}
		return null;
	}

}
