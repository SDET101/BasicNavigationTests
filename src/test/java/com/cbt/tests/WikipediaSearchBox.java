package com.cbt.tests;

import com.cbt.utilities.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WikipediaSearchBox {

    public static void main(String[] args) {

        /*
        Go to wikipedia.org (Links to an external site.)
enter search term `selenium webdriver`
click on search button
click on search result `Selenium (software)`
verify url ends with `Selenium_(software)'
         */

        WebDriver driver = BrowserFactory.getDriver("chrome");

        driver.get("https://en.wikipedia.org/");

        driver.findElement(By.id("searchInput")).sendKeys("selenium webdriver");
        driver.findElement(By.id("searchButton")).click();

        String urlInWiki = driver.findElement(By.xpath("//a[@title='Selenium (software)']")).getAttribute("href");

        if(urlInWiki.endsWith("Selenium_(software)")){
            System.out.println("PASS");
        }else {
            System.out.println("FAIL");
        }

        driver.close();
    }
}
