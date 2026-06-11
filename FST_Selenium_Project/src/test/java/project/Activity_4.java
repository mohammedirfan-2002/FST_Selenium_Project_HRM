package project;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity_4 {
	WebDriver driver;
	WebDriverWait wait;
	Actions builder;
	String emp_id;
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.get("http://hrm.local:3050/symfony/web/index.php/auth/login");
		builder = new Actions(driver);
		wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		}
	@Test(priority=1)
		public void sendData() {
			driver.findElement(By.id("txtUsername")).sendKeys("orange");
			driver.findElement(By.id("txtPassword")).sendKeys("5Nx#I6BK%r3$8vz0ch");
			driver.findElement(By.id("btnLogin")).click();
			wait.until(ExpectedConditions.urlMatches("http://hrm.local:3050/symfony/web/index.php/dashboard"));
			Assert.assertEquals(driver.getCurrentUrl(),"http://hrm.local:3050/symfony/web/index.php/dashboard");
	  }
	@Test(priority=2)
	public void newEmployee() {
		WebElement Pim=driver.findElement(By.id("menu_pim_viewPimModule"));
		builder.click(Pim).pause(2000).build().perform();
		wait.until(ExpectedConditions.urlMatches("http://hrm.local:3050/symfony/web/index.php/pim/viewEmployeeList"));
		driver.findElement(By.xpath("//input[@id='btnAdd']")).click();
		wait.until(ExpectedConditions.urlMatches("http://hrm.local:3050/symfony/web/index.php/pim/addEmployee"));
		Assert.assertEquals(driver.getCurrentUrl(),"http://hrm.local:3050/symfony/web/index.php/pim/addEmployee");
	}
	@Test(priority=3)
	public void addEmp() {
		driver.findElement(By.id("firstName")).sendKeys("Mohammed");
		driver.findElement(By.id("lastName")).sendKeys("Irfan");
		emp_id = driver.findElement(By.id("employeeId")).getAttribute("value");
		driver.findElement(By.id("btnSave")).click();
		Assert.assertEquals(driver.findElement(By.id("personal_txtEmployeeId")).getAttribute("value"), emp_id);
	}
	@Test(priority=4)
	public void verify() {
		WebElement Pim=driver.findElement(By.id("menu_pim_viewPimModule"));
		builder.click(Pim).pause(2000).build().perform();
		wait.until(ExpectedConditions.urlMatches("http://hrm.local:3050/symfony/web/index.php/pim/viewEmployeeList"));
		List<WebElement> emp = driver.findElements(By.xpath("//table/tbody/tr"));
		boolean isPresent = false;
		for(WebElement row: emp) {
			if(row.getText().contains(emp_id)) {
				isPresent = true;
			}
		}
		Assert.assertEquals(isPresent, true);
	}
	@AfterClass
	  public void afterClass() {
		  driver.quit();
	  }
}