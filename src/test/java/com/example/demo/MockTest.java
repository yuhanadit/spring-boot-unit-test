package com.example.demo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MockTest {

	@Test
	void testMock() {
		List<String> list = Mockito.mock(List.class);
		
		Mockito.when(list.get(0)).thenReturn("Yuhan");
		
		System.out.println(list.get(0));
		
		Mockito.verify(list,Mockito.times(1)).get(0);
	}

}
