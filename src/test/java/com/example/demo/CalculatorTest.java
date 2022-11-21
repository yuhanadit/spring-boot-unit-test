package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CalculatorTest {
	
	private Calculator calculator = new Calculator();
	
	@Test
	public void testAddSuccess() {
		Integer result = calculator.add(10, 20);
		
		//Cara manual
//		if(result != 30) {
//			throw new RuntimeException("Hasil tidak sesuai");
//		}
		
		//Cara Assertion
		assertEquals(30, result);
	}
	
}
