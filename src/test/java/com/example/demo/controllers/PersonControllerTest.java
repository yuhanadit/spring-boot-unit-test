package com.example.demo.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.models.Person;
import com.example.demo.services.PersonService;

@Extensions({ @ExtendWith(MockitoExtension.class) })
class PersonControllerTest {

	@Mock
	private PersonService personService;

	private PersonController personController;

	@BeforeEach
	void setUp() throws Exception {
		personController = new PersonController(personService);
	}

	@Test
	void testGetAllPerson() {
		List<Person> personListDummy = new ArrayList<>();
		personListDummy.add(new Person(1L, "Yuhan"));
		personListDummy.add(new Person(2L, "Aditama"));

		Mockito.when(personService.getAllPerson()).thenReturn(personListDummy);

		List<Person> personList = personController.getAllPerson();

		Assertions.assertNotNull(personList);
		Assertions.assertEquals(1L, personList.get(0).getId());
		Assertions.assertEquals("Yuhan", personList.get(0).getNama());

	}

	@Test
	void testGetPersonById() {
		Long id = 1L;
		String nama = "Yuhan";

		Person dummyPerson = new Person(id, nama);

		Mockito.when(personService.getPersonById(id)).thenReturn(dummyPerson);

		Person person = personController.getPersonById(id);

		Assertions.assertNotNull(person);
		Assertions.assertEquals(id, person.getId());
		Assertions.assertEquals("Yuhan", person.getNama());
	}

	@Test
	void testInsertPerson() {
		Long id = 1L;
		String nama = "Yuhan";

		Person dummyPerson = new Person(id, nama);
		Person insertPerson = new Person(nama);

		Mockito.when(personService.insertPerson(insertPerson)).thenReturn(dummyPerson);

		Person person = personController.insertPerson(insertPerson);

		Assertions.assertNotNull(person);
		Assertions.assertEquals(id, person.getId());
		Assertions.assertEquals("Yuhan", person.getNama());

		Mockito.verify(personService, Mockito.times(1)).insertPerson(insertPerson);
	}

	@Test
	void testUpdatePerson() {
		Long id = 1L;
		String nama = "Yuhan";

		Person dummyPerson = new Person(id, nama);
		Person updatePerson = new Person(id, nama);

		Mockito.when(personService.updatePerson(updatePerson)).thenReturn(dummyPerson);

		Person person = personController.updatePerson(updatePerson);

		Assertions.assertNotNull(person);
		Assertions.assertEquals(id, person.getId());
		Assertions.assertEquals("Yuhan", person.getNama());

		Mockito.verify(personService, Mockito.times(1)).updatePerson(updatePerson);
	}

	@Test
	void testDeletePerson() {
		Long id = 1L;
		String newNama = "Aditama";
		
		Person dummyPerson = new Person(id, newNama);
		
		personController.deletePerson(id);
		
		Mockito.verify(personService, times(1)).deletePerson(dummyPerson.getId());
	}

}
