package com.qa.FizzyDrinks.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.qa.FizzyDrinks.domain.FizzyDrink;
import com.qa.FizzyDrinks.repo.FizzyDrinkRepo;

@Service
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
	public FizzyDrink getFizzyDrinkById(Long Id) {
		Optional<FizzyDrink> optionalFizzyDrink = this.repo.findById(Id);
		if (optionalFizzyDrink.isPresent()) {
			return optionalFizzyDrink.get();
		}
		return null;
	}

	@Override
	public FizzyDrink updateFizzyDrinkById(FizzyDrink fizzyDrink, Long Id) {
		Optional<FizzyDrink> optionalFizzyDrink = this.repo.findById(Id);
		FizzyDrink oldFizzy = optionalFizzyDrink.get();
		oldFizzy.setBrand(fizzyDrink.getBrand());
		oldFizzy.setFlavour(fizzyDrink.getFlavour());
		oldFizzy.setName(fizzyDrink.getName());
		oldFizzy.setSugarContent(fizzyDrink.getSugarContent());
		return this.repo.save(oldFizzy);
	}

	@Override
	public FizzyDrink removeFizzyDrink(Long Id) {
		Optional<FizzyDrink> optionalFizzy = this.repo.findById(Id);
		if (this.repo.existsById(Id) == true) {
			this.repo.deleteById(Id);
			return optionalFizzy.get();
		}
		return null;
	}

}
