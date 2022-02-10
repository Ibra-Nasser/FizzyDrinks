package com.qa.FizzyDrinks.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.FizzyDrinks.domain.FizzyDrink;
import com.qa.FizzyDrinks.repo.FizzyDrinkRepo;

@SpringBootTest
@ActiveProfiles("test")
public class FizzyDrinkServiceTest {

	private FizzyDrink newFizzy;
	private FizzyDrink savedFizzy;

	@Autowired
	private FizzyDrinkService service;

	@MockBean
	private FizzyDrinkRepo repo;

	@BeforeEach
	void setUp() {
		newFizzy = new FizzyDrink("Fanta", "Orange", 10, "orange");
		savedFizzy = new FizzyDrink(1L, "Fanta", "Orange", 10, "orange");
	}

	@Test
	void testCreate() {
		// When
		Mockito.when(this.repo.save(newFizzy)).thenReturn(savedFizzy);
		// Then
		assertThat(this.service.createFizzyDrink(newFizzy)).isEqualTo(savedFizzy);
		// Verify
		Mockito.verify(this.repo, Mockito.times(1)).save(newFizzy);
	}

	@Test
	void testUpdate() {
		// given id
		Long Id = 1L;
		// New Fizzy for input
		FizzyDrink toUpdate = new FizzyDrink("Coca", "Cola", 20, "cola");
		// method uses optional of fizzy
		Optional<FizzyDrink> optFizzy = Optional.of(new FizzyDrink(Id, null, null, 0, null));
		// updated version
		FizzyDrink updated = new FizzyDrink(Id, toUpdate.getBrand(), toUpdate.getName(), toUpdate.getSugarContent(),
				toUpdate.getFlavour());

		// When
		Mockito.when(this.repo.findById(Id)).thenReturn(optFizzy);
		Mockito.when(this.repo.save(updated)).thenReturn(updated);

		// Then
		assertThat(this.service.updateFizzyDrinkById(toUpdate, Id)).isEqualTo(updated);

		// Verify
		Mockito.verify(this.repo, Mockito.times(1)).save(updated);
		Mockito.verify(this.repo, Mockito.times(1)).findById(Id);
	}

	@Test
	void testRemove() {
		// GIVEN
		Long Id = 1L;
		// Optional
		Optional<FizzyDrink> optFizzy = Optional.of(new FizzyDrink(Id, null, null, 0, null));
		// deleted
		FizzyDrink deleted = optFizzy.get();

		// WHEN
		Mockito.when(this.repo.findById(Id)).thenReturn(optFizzy);

		// THEN
		assertThat(this.service.removeFizzyDrink(Id)).isEqualTo(deleted);

		// Verify
		Mockito.verify(this.repo, Mockito.times(1)).deleteById(Id);
		Mockito.verify(this.repo, Mockito.times(1)).findById(Id);

	}
}
