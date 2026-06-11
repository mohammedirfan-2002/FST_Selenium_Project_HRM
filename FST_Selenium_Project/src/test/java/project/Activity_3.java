package project;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity_3 {
	WebDriver driver;
	WebDriverWait wait;
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.get("http://hrm.local:3050/symfony/web/index.php/auth/login");
		wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		}
	@Test(priority=1)
		public void sendData() {
			driver.findElement(By.id("txtUsername")).sendKeys("orange");
			driver.findElement(By.id("txtPassword")).sendKeys("5Nx#I6BK%r3$8vz0ch");
			driver.findElement(By.id("btnLogin")).click();
			wait.until(ExpectedConditions.titleContains("OrangeHRM"));
			Assert.assertEquals(driver.getCurrentUrl(),"http://hrm.local:3050/symfony/web/index.php/dashboard");
	  }
	@AfterClass
	  public void afterClass() {
		  driver.quit();
	  }
}