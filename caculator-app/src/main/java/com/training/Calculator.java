package com.training;

public class Calculator {

	public int add(int num1, int num2) {
		return num1 + num2;
	}
	
   public boolean checkpalindrome(String word) {
		 StringBuilder reverseString = new StringBuilder(word);
		 reverseString.reverse();
		 return word.equals(reverseString.toString());
	}
	public int convertStringToNumber(String marks) { // "90"
		return Integer.parseInt(marks);		
	}
	
	public int divide(int num1,int num2) {
		return num1/num2;
	}
	// "90"  + 2	= 92			//"Ninety"	- NumberFormatException
}
