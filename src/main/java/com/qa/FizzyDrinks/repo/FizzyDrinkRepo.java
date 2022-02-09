package com.qa.FizzyDrinks.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.FizzyDrinks.domain.FizzyDrink;

@Repository
public interface FizzyDrinkRepo extends JpaRepository<FizzyDrink, Long> {

}
