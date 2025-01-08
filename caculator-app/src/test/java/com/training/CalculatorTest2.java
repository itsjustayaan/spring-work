package com.training;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class CalculatorTest2 {
	
	Calculator calculator;
	int actual = 0;
	int expected = 0;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("------Beginning");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("------Ending");
	}

	@BeforeEach
	void setUp() throws Exception {
		System.out.println("------Before Each-----");
		calculator = new Calculator();
		expected = 10;
	}

	@AfterEach
	void tearDown() throws Exception {
		System.out.println("------After Each-----");
		calculator = null;
		actual = 0;
		expected = 0;
	}
	
	@Test
	public void testAdd2() {
		actual = calculator.add(6, 4);
		assertEquals(expected,actual);
	}
	
	@Test
	public void testAdd3() {
		actual = calculator.add(5, 6);
		assertNotEquals(expected,actual);
	}
	
	@Test
	public void testpalindrom2() {
		assertTrue(calculator.checkpalindrome("madam"));
	}
	
	@Test
	public void testpalindrom3() {
		assertFalse(calculator.checkpalindrome("phone"));
	}

	@ParameterizedTest(name = "{index} - {0} is a number - {1}")
//	@ValueSource(ints = { 10, 10, 20, 30, 40 })
	@CsvFileSource(resources="words.csv", numLinesToSkip=1)
	void testDemo(String word, Boolean val) {
		assertEquals(val,calculator.checkpalindrome(word));
	}
	
	@Test
	void testdivide1() {
		assertEquals(10, calculator.divide(100, 10));
	}
	
	@Test
	void testdivide2() {
		assertEquals(10, calculator.divide(100, 10));
	}
	
	//testing exception - NumberFormatException
	@Test
	public void testConvertStringToNumber2() {
		String data = "Ten";
		int expected = 10;
		assertThrows(NumberFormatException.class , () -> {
            int actual =   calculator.convertStringToNumber(data);
            assertEquals(expected,actual);
        });
    }

}
