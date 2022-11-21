package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Person;
import com.example.demo.services.PersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

	
	PersonService personService;
	
	@Autowired
	public PersonController(PersonService personService) {
		super();
		this.personService = personService;
	}

	@GetMapping
	public List<Person> getAllPerson(){
		return personService.getAllPerson();
	}
	
	@GetMapping("{personId}")
	public Person getPersonById(@PathVariable("personId") Long personId) {
		return personService.getPersonById(personId);
	}
	
	@PostMapping
	public Person insertPerson(@RequestBody Person person) {
		return personService.insertPerson(person);
	}
	
	@PutMapping
	public Person updatePerson(@RequestBody Person person) {
		return personService.updatePerson(person);
	}
	
	@DeleteMapping("{personId}")
	public void deletePerson(@PathVariable("personId") Long personId) {
		personService.deletePerson(personId);
	}
	
}
