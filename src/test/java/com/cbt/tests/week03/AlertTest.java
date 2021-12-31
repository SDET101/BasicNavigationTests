package com.cbt.tests.week03;

import com.cbt.utilities.BrowserFactory;
import com.cbt.utilities.Login;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

public class AlertTest {

    /*
      Task1:
    1. Go to https://chercher.tech/practice/explicit-wait-sample-selenium-webdriver
    2. Click on "Click me, to Open an alert after 5 seconds"
    3. Explicitly wait until alert is present
    4. Then handle the Javascript alert
    Task2:
    1. Go to https://chercher.tech/practice/explicit-wait-sample-selenium-webdriver
    2. Click on "Enable button after 10 seconds"
    3. Explicitly wait until the button is enabled
    4. Then verify the button is enabled
    Task3:
    1. Go to:  http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/login.aspx
    2. Login with username: Tester, password: test
    3. Click  Order button
    4. Verify under Product Information, selected option is “MyMoney”
    5. Then select FamilyAlbum, make quantity 2, and click Calculate,
    6. Then verify Total is equal to Quantity*PricePerUnit
     */

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = BrowserFactory.getDriver("chrome");
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test (priority = 1)
    public void alertButtonJS() throws InterruptedException {
        driver.get("https://chercher.tech/practice/explicit-wait-sample-selenium-webdriver");

        //click Click For JS Alert Button
        driver.findElement(By.cssSelector("#alert")).click();
        Thread.sleep(5000);
        //switch to JS alert pop up
        Alert alert = driver.switchTo().alert();
        Thread.sleep(2000);
        alert.accept();

    }

    @Test (priority = 2)
    public void enableButton() throws InterruptedException {
        driver.get("https://chercher.tech/practice/explicit-wait-sample-selenium-webdriver");
        driver.findElement(By.cssSelector("#enable-button")).click();
        Thread.sleep(10000);

        Assert.assertTrue(driver.findElement(By.cssSelector("#disable")).isEnabled(), "verify the button is enabled");

    }

    @Test (priority = 3)
    public void checkTotalPrice() throws InterruptedException {
        driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/login.aspx");
        Login.login("Tester", "test", "ctl00_MainContent_username", "ctl00_MainContent_password",
                "ctl00_MainContent_login_button", driver);

        driver.findElement(By.linkText("Order")).click();

        List<WebElement> elements = driver.findElements(By.tagName("option"));

        Assert.assertEquals(elements.size(), 3, "verify button size");

        WebElement dropdownButton = driver.findElement(By.id("ctl00_MainContent_fmwOrder_ddlProduct"));

        Select productDropdown = new Select(dropdownButton);

        String expectedOption = "MyMoney";
        String actualOption = productDropdown.getFirstSelectedOption().getText();
        Assert.assertEquals(actualOption,expectedOption,"verify first selection");

        productDropdown.selectByVisibleText("FamilyAlbum");
        expectedOption="FamilyAlbum";
        actualOption=productDropdown.getFirstSelectedOption().getText();
        Assert.assertEquals(actualOption,expectedOption,"verify first selection");

        driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity")).sendKeys("2");
        String price=driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtUnitPrice")).getAttribute("value");
        driver.findElement(By.xpath("//*[@value='Calculate']")).click();

        String expectedResult= String.valueOf(2*Integer.parseInt(price));
        String actualResult= driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtTotal")).getAttribute("value");

        Assert.assertEquals(actualResult, expectedResult, "verify total price");

    }

}
