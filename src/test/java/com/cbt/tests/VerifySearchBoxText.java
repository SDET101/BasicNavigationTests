package com.cbt.tests;

import com.cbt.utilities.BrowserFactory;
import com.cbt.utilities.StringUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class VerifySearchBoxText {

    public static void main(String[] args) throws InterruptedException {

        /*
        Go to Ebay
search Selenium
click on search button
verify title contains Selenium
         */

        WebDriver driver = BrowserFactory.getDriver("chrome");

        driver.get("https://www.ebay.com/");

        String term = "selenium";

        WebElement searchBox = driver.findElement(By.id("gh-ac"));

        searchBox.sendKeys(term);

        String actualResult = searchBox.getAttribute("value");

        System.out.println(StringUtility.verifyEquals(term, actualResult));

        Thread.sleep(2000);
        driver.close();

    }
}
