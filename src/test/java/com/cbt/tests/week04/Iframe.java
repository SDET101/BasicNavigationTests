package com.cbt.tests.week04;

import com.cbt.utilities.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Iframe {

    /*
    1. go to "https://www.w3schools.com/tags/tryit.asp?filename=tryhtml5_ev_ondblclick2"
    2. Switch to iframe.
    3. Double click on the text “Double-click me to change my text color.”
    4. Assert: Text’s “style” attribute value contains “red”
    */

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = BrowserFactory.getDriver("chrome");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml5_ev_ondblclick2");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void test(){
        driver.switchTo().frame("iframeResult");
        WebElement demo = driver.findElement(By.id("demo"));

        Actions actions=new Actions(driver);
        actions.doubleClick(demo).perform();

        String style = demo.getAttribute("style");

        Assert.assertTrue(style.contains("red"));
    }

}
