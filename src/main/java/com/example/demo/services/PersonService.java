package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Person;
import com.example.demo.repositories.PersonRepository;

@Service
public class PersonService {
	
	PersonRepository personRepository;
	
	@Autowired
	public PersonService(PersonRepository personRepository) {
		super();
		this.personRepository = personRepository;
	}

	public List<Person> getAllPerson(){
		List<Person> persons = new ArrayList<>();
		
		personRepository.findAll().forEach(person -> persons.add(person));
		
		return persons;
	}
	
	public Person getPersonById(Long id) {
		Optional<Person> optPerson = personRepository.findById(id);
		if (optPerson == null || optPerson.isEmpty()){
			throw new IllegalArgumentException("Person with id " + id + " does not exist!");
		}
		return personRepository.findById(id).get();
	}
	
	public Person insertPerson(Person person) {
		return personRepository.save(person);
	}
	
	public Person updatePerson(Person person) {
		if(person == null || person.getId() == null) {
			throw new IllegalArgumentException("Person or id must not be null!");
		}
		
		Optional<Person> optPerson = personRepository.findById(person.getId());
		if (optPerson == null || optPerson.isEmpty()){
			throw new IllegalArgumentException("Person with id " + person.getId() + " does not exist!");
		}
		
		Person existingPerson = optPerson.get();
		existingPerson.setNama(person.getNama());
		
		return personRepository.save(existingPerson);
	}
	
	public void deletePerson(Long id) {
		Optional<Person> optPerson = personRepository.findById(id);
		if (optPerson == null || optPerson.isEmpty()){
			throw new IllegalArgumentException("Person with id " + id + " does not exist!");
		}
		personRepository.deleteById(id);
	}
}
