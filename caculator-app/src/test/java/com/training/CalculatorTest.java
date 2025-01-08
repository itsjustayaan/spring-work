package com.training;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CalculatorTest {
	
	@Test
	@Order(value=2)
	public void testAdd1() {
		Calculator calculator = new Calculator();
		int actual = calculator.add(5, 5);
		int expected = 10;
		assertEquals(expected,actual);
	}
	@Test
	@Order(value=1)
	public void testAdd2() {
		Calculator calculator = new Calculator();
		int actual = calculator.add(6, 4);
		int expected = 10;
		assertEquals(expected,actual);
	}
	@Test
	@Order(value=4)
	public void testAdd3() {
		Calculator calculator = new Calculator();
		int actual = calculator.add(5, 6);
		int expected = 10;
		assertNotEquals(expected,actual);
	}
	@Test
	@Order(value=3)
	public void testAdd4() {
		Calculator calculator = new Calculator();
		int actual = calculator.add(-4, 14);
		int expected = 10;
		assertEquals(expected,actual);
	}
}
