package com.qa.FizzyDrinks.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.FizzyDrinks.domain.FizzyDrink;

@SpringBootTest
@AutoConfigureMockMvc // acts like postman

@Sql(scripts = { "classpath:FizzyDrink-schema.sql",
		"classpath:FizzyDrink.sql" }, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)

@ActiveProfiles("test")
public class FizzyDrinkControllerTest {

	@Autowired
	private MockMvc mock; // TO MOCK REQUESTS

	@Autowired
	private ObjectMapper map; // INTERPRETS JSON

	@Test
	void createTest() throws Exception {
		/// ---request
		// CREATE A FizzyDrink
		FizzyDrink newF = new FizzyDrink("Fanta", "Orange", 10, "orange");
		// CONVERT INTO JSON
		String newFJSON = this.map.writeValueAsString(newF);
		// Build a mock request
		RequestBuilder mockRequest = post("/create").contentType(MediaType.APPLICATION_JSON).content(newFJSON);

		// ----response
		FizzyDrink savedF = new FizzyDrink(2L, "Fanta", "Orange", 10, "orange");
		// convert to Json
		String savedFJSON = this.map.writeValueAsString(savedF);

		// --test response (status+body)
		// test status = 201-created
		ResultMatcher matchStatus = status().isCreated();
		// test response body
		ResultMatcher matchBody = content().json(savedFJSON);

		// PERFORM THE TEST
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchBody);

	}

	@Test
	void readAllTest() throws Exception {
		FizzyDrink savedF = new FizzyDrink(1L, "Fanta", "Orange", 10, "orange");
		this.mock.perform(get("/readAll")).andExpect(status().isOk())
				.andExpect(content().json(this.map.writeValueAsString(List.of(savedF))));
	}

	@Test
	void readByIdTest() throws Exception {
		FizzyDrink savedF = new FizzyDrink(1L, "Fanta", "Orange", 10, "orange");
		this.mock.perform(get("/readById/1")).andExpect(status().isFound())
				.andExpect(content().json(this.map.writeValueAsString(savedF)));
	}

	@Test
	void updateTest() throws Exception {
		FizzyDrink updateFizzy = new FizzyDrink("Coca", "Cola", 20, "cola");
		String updateFizzyJSON = this.map.writeValueAsString(updateFizzy);
		Long IDupdate = 1L;

		RequestBuilder updateReq = put("/updateFizzy/" + IDupdate).contentType(MediaType.APPLICATION_JSON)
				.content(updateFizzyJSON);

		FizzyDrink retUpdatedFizzy = new FizzyDrink(1L, "Coca", "Cola", 20, "cola");
		String retUpdatedFizzyJSON = this.map.writeValueAsString(retUpdatedFizzy);

		ResultMatcher retStatus = status().isOk();
		ResultMatcher retBody = content().json(retUpdatedFizzyJSON);

		this.mock.perform(updateReq).andExpect(retStatus).andExpect(retBody);
	}

}
