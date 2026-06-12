package project;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class Activity1_2 {
	// Declare the driver
	WebDriver driver;

	@Parameters({"browserName"})
	@BeforeClass
	public void setUp(String browserName) {
		// Initialize the driver
		switch (browserName) {
		case "firefox":
			driver = new FirefoxDriver();
			break;
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "edge":
			driver = new EdgeDriver();
			break;
		default:
			driver = null;
		}

		// Open a browser and navigate to
		// http://hrm.local:3050/symfony/web/index.php/auth/login
		driver.get("http://hrm.local:3050/symfony/web/index.php/auth/login");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	// G
	@Test(priority = 1)
	public void getTitle() {
		String title = driver.getTitle();
		Assert.assertEquals("OrangeHRM", title);
	}

	@Test(dependsOnMethods = "getTitle")
	public void getHeaderImageUrl() {
		WebElement image = driver.findElement(By.xpath("//img[contains(@src, 'symfony')]"));
		String src = image.getAttribute("src");
		System.out.println(src);
		Assert.assertEquals("http://hrm.local:3050/symfony/web/webres_618e67a57beec1.83834480/themes/default/images/login/logo.png", src);
	}

	@AfterClass
	public void tearDown() {
		// close the browser
		driver.quit();
	}
}
