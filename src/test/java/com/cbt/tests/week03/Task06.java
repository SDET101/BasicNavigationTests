package com.cbt.tests.week03;

import com.cbt.utilities.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class Task06 {

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

    @DataProvider //(name = "data-provider")
    public Object[][] dpMethod(){
        return new Object[][] {{"200"}, {"301"}, {"404"}, {"500"}};
    }

    @Test  (dataProvider = "dpMethod")  //(name = "data-provider")
    public void test(String value){
        driver.findElement(By.linkText(value)).click();
        String text = driver.findElement(By.xpath("//h3/../p")).getText();
        String[] textLines = text.split("\n");
        String expectedText = "This page returned a " + value + " status code.";

        Assert.assertEquals(textLines[0], expectedText, "verify the text message");
    }
}
