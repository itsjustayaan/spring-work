   package com.training;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

//import io.github.bonigarcia.wdm.WebDriverManager;

public class ExcelTest {

	WebDriver driver;
	String browsername = "chrome";

//	public ExcelTest() {
//		if (browsername.equals("chrome")) {
//			// 2nd way - using bonigraica WebDriverManager.chromedriver().setup();
//			// 1 way - manual download System.setProperty("webdriver.chrome.driver",
//			// "/path/to/chromedriver");
//			// 3rd way - directly supported in selenium 24
//			WebDriverManager.chromedriver().setup();
//			driver = new ChromeDriver();
//		} else if (browsername.equals("edge")) {
//			WebDriverManager.edgedriver().setup();
//			driver = new EdgeDriver();
//		} else if (browsername.equals("mozilla")) {
//			WebDriverManager.firefoxdriver().setup();
//			driver = new FirefoxDriver();
//		}
//		System.out.println("Driver loaded successfully");
//
//	}

	@Test
	@DisplayName("Checking with correct credentails")
	public void testLogin1() throws Exception {

		File file = new File("C:\\Users\\Ayaan\\Downloads\\user.xlsx");
		FileInputStream inputStream = new FileInputStream(file);

		XSSFWorkbook wb = new XSSFWorkbook(inputStream);

		XSSFSheet sheet = wb.getSheet("Sheet1");

		// get all rows in the sheet
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		System.out.println("Total number of rows :"+rowCount);
		

		// Navigate to the URL
		driver.get("https://www.saucedemo.com");

		// Identify the WebElements for the student registration form
		WebElement userName = driver.findElement(By.id("user-name"));
		WebElement password = driver.findElement(By.id("password"));
		WebElement submitBtn = driver.findElement(By.id("login-button"));

		// iterate over all the rows in Excel and put data in the form.
		for (int i = 1; i <= rowCount; i++) {
			System.out.println("testing with :"+userName+ " and "+password);
			String temp1 = sheet.getRow(i).getCell(0).getStringCellValue();
			String temp2 = sheet.getRow(i).getCell(1).getStringCellValue();
			
			System.out.println("Test :"+i +"    "+temp1+"    "+temp2);
			
			userName.sendKeys(temp1);
			password.sendKeys(temp2);

			// Click on submit button
			submitBtn.click();
			
		}

		// Close the workbook
		wb.close();

		// Quit the driver
		driver.quit();
	}
}