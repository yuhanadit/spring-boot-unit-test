package com.example.demo.controllers;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.models.Person;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
class PersonControllerAcceptanceTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper mapper;

	@Test
	@Order(3)
	void testGetAllPerson() throws Exception {
		mockMvc.perform(get("/person")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	@Order(4)
	void testGetPersonById() throws Exception {
		mockMvc.perform(get("/person/1")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	@Order(1)
	void testInsertPerson() throws Exception {
		String nama = "Yuhan";

		Person insertPerson = new Person(nama);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/person")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(insertPerson));

		mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$.nama", is("Yuhan")));
	}

	@Test
	@Order(2)
	void testUpdatePerson() throws Exception {
		Long id = 1L;
		String nama = "Aditama";

		Person updatePerson = new Person(id, nama);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/person")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(updatePerson));

		mockMvc.perform(mockRequest).andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$.nama", is("Aditama")));
	}

	@Test
	@Order(5)
	void testDeletePerson() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/person/1").contentType(MediaType.APPLICATION_JSON))
				.andDo(print()).andExpect(status().isOk());
	}

}
