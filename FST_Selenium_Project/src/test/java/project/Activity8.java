package project;

import java.time.Duration;
import org.testng.annotations.DataProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
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

    @BeforeClass
    public void setup() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("http://hrm.local:3050");
    }

    @Test(priority = 1)
    public void loginVerify() {
        driver.findElement(By.id("txtUsername")).sendKeys("orange");
        driver.findElement(By.id("txtPassword")).sendKeys("5Nx#I6BK%r3$8vz0ch");
        driver.findElement(By.id("btnLogin")).click();
    }

    @Test(priority = 2)
    public void verifyPageTitle() {
        wait.until(ExpectedConditions.titleIs("OrangeHRM"));
        Assert.assertEquals(driver.getTitle(), "OrangeHRM");
    }

    @Test(priority = 3)
    public void leaveApplication() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Apply Leave']"))).click();

        WebElement dropDownElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("applyleave_txtLeaveType")));
        new Select(dropDownElement).selectByIndex(1);

        WebElement fromDate = driver.findElement(By.id("applyleave_txtFromDate"));
        fromDate.clear();
        fromDate.sendKeys("2026-06-13");

        WebElement toDate = driver.findElement(By.id("applyleave_txtToDate"));
        toDate.clear();
        toDate.sendKeys("2026-06-15");

        driver.findElement(By.id("applyBtn")).click();
    }


    @Test(priority = 4)
    public void leaveStatus() {
        wait.until(ExpectedConditions.elementToBeClickable(By.id("menu_leave_viewMyLeaveList"))).click();

        String status = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//table/tbody/tr[2]/td[6]"))).getText();

        System.out.println("Leave Status: " + status);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}