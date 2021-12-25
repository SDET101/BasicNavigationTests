package com.cbt.tests;

import com.cbt.utilities.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EbaySearchResult {

    public static void main(String[] args) {

        /*
        Go to Ebay?
        enter search term
        click on search button
        print number of results
         */
        
        // Browser setup
        WebDriver driver = BrowserFactory.getDriver("chrome");

        // Getting the source page
        driver.get("https://www.ebay.com/");
        
        // Search term
        String term = "apple";

        // Find Searchbox and send values
        driver.findElement(By.id("gh-ac")).sendKeys(term);

        // Find search button and click on it
        driver.findElement(By.xpath("//input[@value='Search']")).click();

        String result = driver.findElement(By.className("srp-controls__count-heading")).getText();

        System.out.println(result.split(" result")[0]);

        driver.close();

    }
}
