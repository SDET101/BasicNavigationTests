package com.cbt.tests.week03;

import com.cbt.utilities.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Task04 {

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = BrowserFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    /*
    Test case #8
    Step 1. Go to “https://practice-cybertekschool.herokuapp.com”
    Step 2. And click on “Autocomplete”.
    Step 3. Enter “United States of America” into country input box.
    Step 4. Verify that following message is displayed:
    “You selected: United States of America”
     */

    @Test
    public void test(){
        driver.get("https://practice-cybertekschool.herokuapp.com");
        driver.findElement(By.linkText("Autocomplete")).click();
        driver.findElement(By.id("myCountry")).sendKeys("United States of America");
        driver.findElement(By.xpath("//input[@value='Submit']")).click();
        String expectedText = "You selected: United States of America";
        String actualText = driver.findElement(By.id("result")).getText();

        Assert.assertEquals(actualText, expectedText, "verify the text message");

    }


}
