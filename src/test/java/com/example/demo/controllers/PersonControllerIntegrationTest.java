package com.example.demo.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.models.Person;
import com.example.demo.services.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.times;

@WebMvcTest
class PersonControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper mapper;

	@MockBean
	private PersonService personService;

	private PersonController personController;

	@BeforeEach
	void setUp() throws Exception {
		personController = new PersonController(personService);
	}

	@Test
	void testGetAllPerson() throws Exception {
		List<Person> personListDummy = new ArrayList<>();
		personListDummy.add(new Person(1L, "Yuhan"));
		personListDummy.add(new Person(2L, "Aditama"));

		Mockito.when(personService.getAllPerson()).thenReturn(personListDummy);

		mockMvc.perform(get("/person")).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[1].nama", Matchers.is("Aditama")));

		Mockito.verify(personService).getAllPerson();
	}

	@Test
	void testGetPersonById() throws Exception {
		Long id = 1L;
		String nama = "Yuhan";

		Person dummyPerson = new Person(id, nama);

		Mockito.when(personService.getPersonById(id)).thenReturn(dummyPerson);

		mockMvc.perform(get("/person/1")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.id", Matchers.is(1)));

		Mockito.verify(personService).getPersonById(id);
	}

	@Test
	void testInsertPerson() throws Exception {
		Long id = 1L;
		String nama = "Yuhan";

		Person dummyPerson = new Person(id, nama);
		Person insertPerson = new Person(nama);

		Mockito.when(personService.insertPerson(insertPerson)).thenReturn(dummyPerson);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/person")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(insertPerson));

		mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$.nama", is("Yuhan")));

		Mockito.verify(personService, Mockito.times(1)).insertPerson(insertPerson);
	}

	@Test
	void testUpdatePerson() throws Exception {
		Long id = 1L;
		String nama = "Yuhan";

		Person dummyPerson = new Person(id, nama);
		Person updatePerson = new Person(id, nama);

		Mockito.when(personService.updatePerson(updatePerson)).thenReturn(dummyPerson);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/person")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(updatePerson));

		mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$.nama", is("Yuhan")));

		Mockito.verify(personService, Mockito.times(1)).updatePerson(updatePerson);
	}

	@Test
	void testDeletePerson() throws Exception {
		Long id = 1L;
		String newNama = "Aditama";

		Person dummyPerson = new Person(id, newNama);

		 mockMvc.perform(MockMvcRequestBuilders
		            .delete("/person/1")
		            .contentType(MediaType.APPLICATION_JSON))
		 			.andDo(print())
		            .andExpect(status().isOk());

		Mockito.verify(personService, times(1)).deletePerson(dummyPerson.getId());
	}

}
