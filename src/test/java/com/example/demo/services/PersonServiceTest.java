package com.example.demo.services;

import static org.mockito.ArgumentMatchers.eq;
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
import com.example.demo.repositories.PersonRepository;

@Extensions({ @ExtendWith(MockitoExtension.class) })
class PersonServiceTest {

	@Mock
	private PersonRepository personRepository;

	private PersonService personService;

	@BeforeEach
	void setUp() throws Exception {
		personService = new PersonService(personRepository);
	}

	@Test
	void testGetAllPerson() {
		List<Person> personListDummy = new ArrayList<>();
		personListDummy.add(new Person(1L, "Yuhan"));
		personListDummy.add(new Person(2L, "Aditama"));

		Mockito.when(personRepository.findAll()).thenReturn(personListDummy);

		List<Person> personList = personService.getAllPerson();

		Assertions.assertNotNull(personList);
		Assertions.assertEquals(1L, personList.get(0).getId());
		Assertions.assertEquals("Yuhan", personList.get(0).getNama());
	}

	@Test
	void testGetPersonByIdSuccess() {
		Long id = 1L;

		Optional<Person> optPerson = Optional.of(new Person(id, "Yuhan"));

		Mockito.when(personRepository.findById(id)).thenReturn(optPerson);

		Person person = personService.getPersonById(id);

		Assertions.assertNotNull(person);
		Assertions.assertEquals(id, person.getId());
		Assertions.assertEquals("Yuhan", person.getNama());
	}

	@Test
	void testGetPersonByIdFailed() {
		Long id = 1L;

		Optional<Person> optPerson = Optional.empty();

		Mockito.when(personRepository.findById(id)).thenReturn(optPerson);

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			personService.getPersonById(id);
		});
	}

	@Test
	void testInsertPerson() {
		Long id = 1L;
		String nama = "Yuhan";

		Person dummyPerson = new Person(id, nama);
		Person insertPerson = new Person(nama);

		Mockito.when(personRepository.save(insertPerson)).thenReturn(dummyPerson);

		Person person = personService.insertPerson(insertPerson);

		Assertions.assertNotNull(person);
		Assertions.assertEquals(id, person.getId());
		Assertions.assertEquals(nama, person.getNama());
	}

	@Test
	void testUpdatePersonSuccess() {
		Long id = 1L;
		String oldNama = "Yuhan";
		String newNama = "Aditama";

		Person dummyPerson = new Person(id, newNama);
		Person updatedPerson = new Person(id, newNama);
		Person existingPerson = new Person(id, oldNama);

		Optional<Person> optPerson = Optional.of(existingPerson);

		Mockito.when(personRepository.findById(dummyPerson.getId())).thenReturn(optPerson);
		Mockito.when(personRepository.save(eq(new Person(id, newNama)))).thenReturn(updatedPerson);

		Person person = personService.updatePerson(optPerson.get());

		Assertions.assertNotNull(person);
		Assertions.assertEquals(id, person.getId());
		Assertions.assertEquals(newNama, person.getNama());

		Mockito.verify(personRepository, times(1)).findById(dummyPerson.getId());
		Mockito.verify(personRepository, times(1)).save(eq(new Person(id, newNama)));
	}
	
	@Test
	void testUpdatePersonFailedNullId() {
		Long id = null;
		String newNama = "Aditama";
		
		Person dummyPerson = new Person(id, newNama);

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			personService.updatePerson(dummyPerson);
		});
	}
	
	@Test
	void testUpdatePersonFailedNotExist() {
		Long id = 1L;
		String newNama = "Aditama";
		
		Person dummyPerson = new Person(id, newNama);
		
		Optional<Person> optPerson = Optional.empty();

		Mockito.when(personRepository.findById(dummyPerson.getId())).thenReturn(optPerson);

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			personService.updatePerson(dummyPerson);
		});
	}

	@Test
	void testDeletePersonSuccess() {
		Long id = 1L;
		String newNama = "Aditama";
		
		Person dummyPerson = new Person(id, newNama);
		
		Optional<Person> optPerson = Optional.of(dummyPerson);

		Mockito.when(personRepository.findById(dummyPerson.getId())).thenReturn(optPerson);
		
		personService.deletePerson(id);
		
		Mockito.verify(personRepository, times(1)).deleteById(dummyPerson.getId());
	}
	
	@Test
	void testDeletePersonFailed() {
		Long id = 1L;

		Optional<Person> optPerson = Optional.empty();

		Mockito.when(personRepository.findById(id)).thenReturn(optPerson);

		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			personService.deletePerson(id);
		});
	}

}
