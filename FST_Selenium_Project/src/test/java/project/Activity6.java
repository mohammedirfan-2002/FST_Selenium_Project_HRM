package Orange_HRM;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity6 {
	// declare WebDriver
	WebDriver driver;
	//explicit wait
	WebDriverWait wait;
	
	@BeforeClass
	public void setUp() {
		// initialize the driver object
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// open the page
		driver.get("http://hrm.local:3050/");
	}

	@Test(priority = 1)
	public void verifyPageTitle() {
		// assert
		assertEquals(driver.getTitle(), "OrangeHRM");
	}

	@Test(priority = 2)
	public void userLogin() {
		// enter login username
		driver.findElement(By.id("txtUsername")).sendKeys("orange");
		// enter login password
		driver.findElement(By.id("txtPassword")).sendKeys("5Nx#I6BK%r3$8vz0ch");
		// click submit button
		driver.findElement(By.id("btnLogin")).click();

	}

	@Test(priority = 3)
	public void verifyDirectoryMenu() {
		// locate directory menu
		WebElement directory = driver.findElement(By.linkText("Directory"));
		// Verify Directory menu is visible
		assertTrue(directory.isDisplayed());
		// Verify Directory menu is clickable
		assertTrue(directory.isEnabled());

		// Click Directory menu
		directory.click();
		directory.click();
		wait.until(ExpectedConditions.urlContains("directory"));
	
		// Verify heading
		String heading = driver.findElement(By.xpath("//div[@id='content']//h1")).getText();

		assertEquals(heading, "Search Directory");

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
