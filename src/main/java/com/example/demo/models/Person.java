package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nama;

	public Person() {
		super();
	}
	
	public Person(String nama) {
		super();
		this.nama = nama;
	}

	public Person(Long id, String nama) {
		super();
		this.id = id;
		this.nama = nama;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.id == ((Person)obj).getId();
	}
}
