package com.training;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SauceDemoTest {
	WebDriver driver;
	String browsername = "edge";
	
	public SauceDemoTest() {
		
		if(browsername.equals("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless=new");
			driver = new ChromeDriver(options);
		}else if(browsername.equals("edge")) {
			EdgeOptions options = new EdgeOptions();
			options.addArguments("--headless=new");
			driver = new EdgeDriver(options);
			
		}else if(browsername.equals("firefox")) {
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--headless=new");
			driver = new FirefoxDriver(options);
		}
		System.out.println("Driver loaded");
	}
	
	@Test
	public void testLogin1() {
		System.out.println("Test Called");
		driver.get("https://saucedemo.com");
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		Assertions.assertEquals(driver.getCurrentUrl().toString(),"https://www.saucedemo.com/inventory.html");
	}
	
	@Test
	public void testGoogle() {
		System.out.println("Test Called");
		driver.get("https://google.com");
		driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/textarea")).sendKeys("Bengal news");
		//driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/textarea")).sendKeys(Keys.RETURN);
	}
	
	// facebook test with explicitlity wait
		@Test
		public void faceBookTest() {
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get("https://www.facebook.com/");
			WebElement firstname = driver.findElement(By.name("email"));
			WebElement lastname = driver.findElement(By.name("pass"));
			Duration duration = Duration.ofSeconds(10);
			sendKeys(driver, firstname, duration, "Neha");
			sendKeys(driver, lastname, duration, "Agrawal");
			WebElement forgotAccount = driver.findElement(By.linkText("Forgotten password?"));
			clickOn(driver, forgotAccount, duration);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}

		public static void sendKeys(WebDriver driver1, WebElement element, Duration timeout, String value) {
			new WebDriverWait(driver1, timeout).until(ExpectedConditions.visibilityOf(element));
			element.sendKeys(value);
		}

		public static void clickOn(WebDriver driver1, WebElement element, Duration timeout) {
			new WebDriverWait(driver1, timeout).until(ExpectedConditions.elementToBeClickable(element));
			element.click();
		}
		
		@Test
		public void amazonTest() {
			driver.get("https://www.amazon.in/");
			
			driver.findElement(By.xpath("/html/body/div[1]/header/div/div[1]/div[2]/div/form/div[2]/div[1]/input")).sendKeys("diamond studded name plate"+Keys.RETURN);         
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			WebElement amazonLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href, '/sspa/click') and contains(., 'DLCCREATION Name Plate')]")));
			System.out.println(amazonLink);
			Duration duration = Duration.ofSeconds(30);
			clickOn(driver, amazonLink, duration);
    }
	
}