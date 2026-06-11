package project;

import java.awt.Desktop.Action;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

public class Activity7 {
	
	//Declare Webdriver
	WebDriver driver;
	//Declare Explicitwait
	WebDriverWait wait;
	//Declare Action
	Action builder;
	
	@BeforeClass
	public void setUp() {
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		builder = new Acti
	}

}
