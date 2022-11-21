package com.example.demo;

public class Calculator {
	public Integer add(Integer first, Integer second) {
		return first + second;
	}
	
	public Integer divide(Integer first, Integer second) {
		if(second == 0) {
			throw new IllegalArgumentException("Can not divide by zero");
		} else {
			return first/second;
		}
	}
}
