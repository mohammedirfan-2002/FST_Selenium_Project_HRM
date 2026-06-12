package project;

import java.time.Duration;
import java.util.List;

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
import org.testng.annotations.Test;

public class Activity8 {

	WebDriver driver;
	WebDriverWait wait;
	Actions builder;

	@BeforeClass
	public void setup() {
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		driver.get("http://hrm.local:3050");
		builder = new Actions(driver);
	}

	@Test(priority = 1)
	public void loginVerify() {
		driver.findElement(By.id("txtUsername")).sendKeys("orange");
		driver.findElement(By.id("txtPassword")).sendKeys("5Nx#I6BK%r3$8vz0ch");
		driver.findElement(By.id("btnLogin")).click();

		// Assertion for succssful login
		Assert.assertEquals(driver.getCurrentUrl(), "http://hrm.local:3050/symfony/web/index.php/dashboard");

	}

	@Test(priority = 2)
	public void verifyPageTitle() {
		wait.until(ExpectedConditions.titleIs("OrangeHRM"));
		Assert.assertEquals(driver.getTitle(), "OrangeHRM");
	}

	@Test(priority = 3)
	public void leaveApplication() throws InterruptedException {
		List<WebElement> myLeave = driver.findElements(By.className("quickLaunge"));
		builder.click(myLeave.get(3)).build().perform();

//        driver.findElement(By.xpath("//span[text()='Apply Leave']")).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Apply Leave']")));
//        Thread.sleep(5000);
//        
		WebElement dropDownElement = wait
				.until(ExpectedConditions.elementToBeClickable(By.id("applyleave_txtLeaveType")));
		Select var = new Select(dropDownElement);
		var.selectByIndex(1);

		WebElement fromDate = driver.findElement(By.id("applyleave_txtFromDate"));
		fromDate.clear();
		fromDate.sendKeys("2026-08-24");

		WebElement toDate = driver.findElement(By.id("applyleave_txtToDate"));
		toDate.clear();
		toDate.sendKeys("2026-08-26");

//		WebElement applyBtn = driver.findElement(By.id("applyBtn"));
//		builder.click(applyBtn).build().perform();
		driver.findElement(By.id("applyBtn")).click();
		driver.findElement(By.id("applyBtn")).click();
		// Verify the success message
	}

	@Test(priority = 4)
	public void leaveStatus() {
		wait.until(ExpectedConditions.elementToBeClickable(By.id("menu_leave_viewMyLeaveList"))).click();
		String status = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table/tbody/tr[1]/td[6]")))
				.getText();

		System.out.println("Leave Status: " + status);
		// Verify the leave status
		Assert.assertEquals(status, "Pending Approval(3.00)");

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}