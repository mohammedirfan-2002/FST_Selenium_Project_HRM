package project;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity9 {
	WebDriver driver;
	WebDriverWait wait;
	Actions builder;
	@BeforeClass
	public void setup() {
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		builder=new Actions(driver);
		driver.get("http://hrm.local:3050/symfony/web/index.php/auth/login");
		
	}
	
	@Test(priority=1)
	public void loginCredentials() {
		driver.findElement(By.id("txtUsername")).sendKeys("orange");
		driver.findElement(By.id("txtPassword")).sendKeys("5Nx#I6BK%r3$8vz0ch");
		driver.findElement(By.id("btnLogin")).click();
		String dashBoardMessage=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Dashboard']"))).getText();
		assertEquals(dashBoardMessage,"Dashboard");
		
	}
	
	
	@Test(priority = 2)
	public void myInfoPage() throws InterruptedException {
//		String myInfoUrl=driver.findElement(By.id("menu_pim_viewMyDetails")).getAttribute("href");
//		driver.get(myInfoUrl);
		WebElement myInfo = driver.findElement(By.id("menu_pim_viewMyDetails"));
		builder.click(myInfo).build().perform();
		driver.findElement(By.xpath("//li/a[@id='menu_pim_viewMyDetails']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Emergency Contacts"))).click();
		String contactHeading = driver.findElement(By.xpath("//h1[text()='Assigned Emergency Contacts']")).getText();
		assertEquals(contactHeading,"Assigned Emergency Contacts");
		
	}
	
	@Test(priority=3)
	public void getTableContent() {
		List<WebElement> rows=driver.findElements(By.xpath("//table/tbody/tr"));
		System.out.println("The Emergency Contact lists are as follows:");
		for(WebElement row:rows) {
			System.out.println(row.getText());
		}
		boolean isPresent = rows.size()>0;
		assertEquals(isPresent,true);
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	
	

}
