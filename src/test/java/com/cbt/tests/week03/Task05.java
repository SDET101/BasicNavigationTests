package com.cbt.tests.week03;

import com.cbt.utilities.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Task05 {

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = BrowserFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://practice-cybertekschool.herokuapp.com");
        driver.findElement(By.linkText("Status Codes")).click();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    /*
    Test case #9
    Step 1. Go to “https://practice-cybertekschool.herokuapp.com”
    Step 2. And click on “Status Codes”.
    Step 3. Then click on “200”.
    Step 4. Verify that following message is displayed:
    “This page returned a 200 status code”
     */

    @Test
    public void test1(){
        driver.findElement(By.linkText("200")).click();
        String text = driver.findElement(By.xpath("//h3/../p")).getText();
        String[] textLines = text.split("\n");
        String expectedText = "This page returned a 200 status code.";

        Assert.assertEquals(textLines[0], expectedText, "verify the text message");
    }

    /*
    Test case #10
    Step 1. Go to “https://practice-cybertekschool.herokuapp.com”
    Step 2. And click on “Status Codes”.
    Step 3. Then click on “301”.
    Step 4. Verify that following message is displayed:
    “This page returned a 301 status code”
     */

    @Test
    public void test2(){
        driver.findElement(By.linkText("301")).click();
        String text = driver.findElement(By.xpath("//h3/../p")).getText();
        String[] textLines = text.split("\n");
        String expectedText = "This page returned a 301 status code.";

        Assert.assertEquals(textLines[0], expectedText, "verify the text message");
    }

    /*
    Test case #11
    Step 1. Go to “https://practice-cybertekschool.herokuapp.com"
    Step 3. And click on “Status Codes”.
    Step 4. Then click on “404”.
    Step 5. Verify that following message is displayed:
    “This page returned a 404 status code”
     */

    @Test
    public void test3(){
        driver.findElement(By.linkText("404")).click();
        String text = driver.findElement(By.xpath("//h3/../p")).getText();
        String[] textLines = text.split("\n");
        String expectedText = "This page returned a 404 status code.";

        Assert.assertEquals(textLines[0], expectedText, "verify the text message");
    }

    /*
    Test case #12
    Step 1. Go to “https://practice-cybertekschool.herokuapp.com”
    Step 3. And click on “Status Codes”.
    Step 4. Then click on “500”.
    Step 5. Verify that following message is displayed:
    “This page returned a 500 status code”
     */

    @Test
    public void test4(){
        driver.findElement(By.linkText("500")).click();
        String text = driver.findElement(By.xpath("//h3/../p")).getText();
        String[] textLines = text.split("\n");
        String expectedText = "This page returned a 500 status code.";

        Assert.assertEquals(textLines[0], expectedText, "verify the text message");
    }

}
