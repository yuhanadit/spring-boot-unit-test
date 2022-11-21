package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
	
	@Test
	public void testDivideSuccess() {
		Integer result = calculator.divide(100, 10);
		assertEquals(10, result);
	}
	
	@Test
	public void testDivideFailed() {
		assertThrows(IllegalArgumentException.class, () -> {
			calculator.divide(100, 0);
		});
	}
	
}
