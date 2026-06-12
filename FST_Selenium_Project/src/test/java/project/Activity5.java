package project;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Activity5 {
	// Declare WebDriver
	WebDriver driver;
	// Declare WebDriverWait
	WebDriverWait wait;
	// Declare Actions
	Actions builder;

	@BeforeClass
	public void setUp() {
		// Initialize the driver object
		driver = new FirefoxDriver();

		// explicit wait
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));

		// Actions object
		builder = new Actions(driver);

		// open the page
		driver.get("http://hrm.local:3050/symfony/web/index.php/auth/login");
	}

	@Test(priority = 1)
	public void loginPage() {
		// enter the username and password
		driver.findElement(By.id("txtUsername")).sendKeys("orange");
		driver.findElement(By.id("txtPassword")).sendKeys("5Nx#I6BK%r3$8vz0ch");

		// find the login button and click that
		driver.findElement(By.id("btnLogin")).click();

		// confirm it is in the home/dashboard after login
		wait.until(ExpectedConditions.urlContains("dashboard"));
		Assert.assertEquals(driver.getCurrentUrl(), "http://hrm.local:3050/symfony/web/index.php/dashboard");

	}

	@Test(dependsOnMethods = "loginPage")
	public void myInfo() {
		// find the my info button and click it
		WebElement wb = driver.findElement(By.xpath("//b[text()='My Info']"));

		builder.click(wb).build().perform();

		// verify it is in the my info page
		wait.until(ExpectedConditions.urlContains("viewMyDetails"));
		Assert.assertEquals(driver.getCurrentUrl(), "http://hrm.local:3050/symfony/web/index.php/pim/viewMyDetails");

	}

	@DataProvider(name = "Credentials")
	public Object[][] creds() {
		return new Object[][] { { "nirup", "lokesh", "male", "Indian", "2003-04-04" },
				{ "sudeep", "varma", "male", "French", "2003-04-03" },

		};
	}

	@Test(dependsOnMethods = "myInfo", dataProvider = "Credentials")
	public void editPersonalDetails(String firstName, String lastName, String gender, String nationality, String date) {
		// find the edit button and click edit
		wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSave"))).click();

		// enter the first name
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("personal_txtEmpFirstName")));
		WebElement fs = driver.findElement(By.id("personal_txtEmpFirstName"));
		fs.sendKeys(firstName);
		fs.clear();

		// enter the last name
		WebElement ls = driver.findElement(By.id("personal_txtEmpLastName"));
		ls.sendKeys(lastName);
		ls.clear();

		// enter the gender
		if (gender == "male") {
			driver.findElement(By.xpath("//label[text()='Male']")).click();

		} else {
			driver.findElement(By.xpath("//label[text()='Female']")).click();
		}

		// enter the nationality
		WebElement dp = driver.findElement(By.id("personal_cmbNation"));
		Select slt = new Select(dp);
		slt.selectByVisibleText(nationality);

		// enter the date
		driver.findElement(By.id("personal_DOB")).clear();
		driver.findElement(By.id("personal_DOB")).sendKeys(date);

		// click the save button
		driver.findElement(By.id("btnSave")).click();

	}

	@AfterClass
	public void tearDown() {
		// close the browser
		driver.quit();
	}
}
